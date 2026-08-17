import torch
import torch.distributed as dist
from dataclasses import dataclass

from sglang.srt.speculative.dspark_components.dspark_planner import local_verify_tier_num_tokens


@dataclass
class MoEConfig:
    """MoE 相关配置（原文未给出，这里补一个最小定义）。"""
    num_experts: int  # 全局专家总数
    max_num_tokens: int  # 单卡单批最大 token 数
    hidden_dim: int  # 隐藏层维度
    top_k: int = 2  # 每个 token 选中的专家数
    in_dtype: torch.dtype = torch.float16
    out_dtype: torch.dtype = torch.float16


class AllToAll:
    """MoE 专家并行的 all-to-all 通信算子（教学版）。

    两阶段：
      dispatch : 把 token 发到「它选中的专家所在设备」
      combine  : 把专家输出回传到「原始 token 所在设备」并按门控权重聚合
    """

    # 元数据维度：[global_expert_id, src_rank, src_token_idx, src_k]
    #   global_expert_id : 选中的全局专家ID（dispatch 后据此落到本地专家）
    #   src_rank         : token 来源设备（combine 时据此回传）
    #   src_token_idx    : token 在源设备上的索引（combine时据此归位，attention阶段token进行一维展开，这个变量可以知道token原本的位置）
    #   src_k            : 该 token 选中的第 k 个专家（combine 时据此取门控权重）
    META_DIM = 4

    def __init__(self, cfg: MoEConfig, rank: int, world_size: int):
        self.cfg = cfg
        self.rank = rank
        self.world_size = world_size  # 伪代码中就等于 EP Size
        self.num_local_experts = cfg.num_experts // world_size  # 每个设备上的专家数
        self.max_recv = cfg.max_num_tokens * world_size  # 最大接收 token 数（为极限情况下准备的

    # =================== Dispatch 阶段 ===================
    def dispatch(self, local_tokens: torch.Tensor, indices: torch.Tensor):
        """
        参数：
            local_tokens    : [num_tokens, hidden_dim]   当前 rank 上的 token
            indices : [num_tokens, top_k]        每个 token 选中的全局专家ID
        返回：
            expert_x          : [num_local_experts, max_recv, hidden_dim] 各本地专家收到的 token
            expert_meta       : [num_local_experts, max_recv, META_DIM]   每个 token 的元数据
            expert_num_tokens : [num_local_experts]                       每个本地专家实际收到的 token 数
        """
        device = local_tokens.device
        cfg = self.cfg

        # ---- 步骤 1：统计每个目标设备需要接收的 token 数 ----
        send_counts = [0] * self.world_size
        send_token_map = [[] for _ in range(self.world_size)]  # 发送给每个其他rank的tokens
        send_meta_map = [[] for _ in range(self.world_size)]  # 每个目标 rank 对应的元数据
        for t, expert_list in enumerate(indices.tolist()):
            # t: token index（只是索引，不是具体的 token id 值
            # expert_list: 该token选中的专家列表
            for k, e in enumerate(expert_list):
                # k: 当前token所选中的expert中，该expert的排序
                # e: 专家的全局id
                dst_rank = e // self.num_local_experts  # 专家 e 所在的目标设备
                send_counts[dst_rank] += 1
                send_token_map[dst_rank].append(t)
                send_meta_map[dst_rank].extend([e, self.rank, t, k])

        # 交换计数，得到「我需要从每个 rank 接收多少 token」
        send_counts_t = torch.tensor(send_counts, dtype=torch.long, device=device)
        recv_counts_t = torch.empty(self.world_size, dtype=torch.long, device=device)
        dist.all_to_all_single(recv_counts_t, send_counts_t)

        # ---- 步骤 2：构建发送缓冲区（按目标设备顺序拼接成连续内存）----
        # 将需要发送的tokens，按照目标rank进行排序拼接
        send_buf = torch.cat(
            [local_tokens[idx_list] for idx_list in send_token_map],
            dim=0,
        ) if any(send_token_map) else torch.empty(0, cfg.hidden_dim, dtype=cfg.in_dtype, device=device)

        # 将meta数据，从一维展开
        send_meta = torch.tensor(
            [v for sub in send_meta_map for v in sub],
            dtype=torch.int32, device=device,
        ).view(-1, self.META_DIM) if any(send_meta_map) else torch.empty(
            (0, self.META_DIM), dtype=torch.int32, device=device)

        # ---- 步骤 3：All-to-All 数据交换（token + 元数据各一次）----
        total_recv = int(recv_counts_t.sum().item())
        recv_buf = torch.empty(total_recv, cfg.hidden_dim, dtype=cfg.in_dtype, device=device)
        recv_meta = torch.empty(total_recv, self.META_DIM, dtype=torch.int32, device=device)

        # 交换 token 数据
        # all_to_all_single: output_split_size和input_split_size参数用于变长的all_to_all，允许每个rank收发的数据量不相同
        #                    在之前传输send_counts的时候，默认是等长，可以不用这两个参数
        dist.all_to_all_single(
            recv_buf, send_buf,
            output_split_sizes=recv_counts_t.tolist(),  # 我从各 rank 接收的数量
            input_split_sizes=send_counts_t.tolist(),  # 我发往各 rank 的数量
        )
        # 交换元数据（长度 = token 数 × META_DIM）
        dist.all_to_all_single(
            recv_meta.view(-1), send_meta.view(-1),
            output_split_sizes=[c * self.META_DIM for c in recv_counts_t.tolist()],
            input_split_sizes=[c * self.META_DIM for c in send_counts_t.tolist()],
        )

        # ---- 步骤 4：把收到的 token 分发到本地专家缓冲区 ----
        expert_x = torch.empty(
            (self.num_local_experts, self.max_recv, cfg.hidden_dim),
            dtype=cfg.in_dtype, device=device,
        )
        expert_meta = torch.empty(
            (self.num_local_experts, self.max_recv, self.META_DIM),
            dtype=torch.int32, device=device,
        )
        expert_num_tokens = torch.zeros(self.num_local_experts, dtype=torch.int32, device=device)

        for i in range(total_recv):
            global_eid = int(recv_meta[i, 0].item())
            local_eid = global_eid % self.num_local_experts  # 本地专家 ID
            pos = int(expert_num_tokens[local_eid].item())
            expert_x[local_eid, pos] = recv_buf[i]
            expert_meta[local_eid, pos] = recv_meta[i]
            expert_num_tokens[local_eid] += 1

        return expert_x, expert_meta, expert_num_tokens

    # =================== Combine 阶段 ===================
    def combine(self, expert_y, expert_meta, expert_num_tokens, weights, num_tokens):
        """
        参数：
            expert_y          : [num_local_experts, max_recv, hidden_dim] 本地专家处理后的输出
            expert_meta       : 来自 dispatch
            expert_num_tokens : 来自 dispatch
            weights           : [num_tokens, top_k] 门控权重
            num_tokens        : 原始 token 数（dispatch 时的 dp_x 行数）
        返回：
            out_tokens : [num_tokens, hidden_dim] 加权聚合后的最终输出（FP32）
        """
        device = expert_y.device
        cfg = self.cfg
        ws = self.world_size

        # ---- 步骤 1：根据元数据里的 src_rank 统计回传计数 ----
        send_counts = [0] * ws
        y_map = [[] for _ in range(ws)]  # 每个目标 rank 要回传的专家输出
        meta_map = [[] for _ in range(ws)]  # 回传的元数据（原样带回）
        for local_eid in range(self.num_local_experts):
            cnt = int(expert_num_tokens[local_eid].item())
            for j in range(cnt):
                meta = expert_meta[local_eid, j]
                dst_rank = int(meta[1].item())  # ← 元数据里的 src_rank 就是回程目的 rank
                send_counts[dst_rank] += 1
                y_map[dst_rank].append(expert_y[local_eid, j].unsqueeze(0))
                meta_map[dst_rank].extend(meta.tolist())

        # 交换计数
        send_counts_t = torch.tensor(send_counts, dtype=torch.long, device=device)
        recv_counts_t = torch.empty(ws, dtype=torch.long, device=device)
        dist.all_to_all_single(recv_counts_t, send_counts_t)

        # ---- 步骤 2：All-to-All 回传（token + 元数据）----
        send_buf = torch.cat(
            [torch.cat(sub_list, dim=0) if sub_list
             else torch.empty(0, cfg.hidden_dim, dtype=cfg.out_dtype, device=device)
             for sub_list in y_map],
            dim=0,
        )
        send_meta = torch.tensor(
            [v for sub in meta_map for v in sub],
            dtype=torch.int32, device=device,
        ).view(-1, self.META_DIM)

        total_recv = int(recv_counts_t.sum().item())
        recv_buf = torch.empty(total_recv, cfg.hidden_dim, dtype=cfg.out_dtype, device=device)
        recv_meta = torch.empty(total_recv, self.META_DIM, dtype=torch.int32, device=device)

        dist.all_to_all_single(
            recv_buf, send_buf,
            output_split_sizes=recv_counts_t.tolist(),
            input_split_sizes=send_counts_t.tolist(),
        )
        dist.all_to_all_single(
            recv_meta.view(-1), send_meta.view(-1),
            output_split_sizes=[c * self.META_DIM for c in recv_counts_t.tolist()],
            input_split_sizes=[c * self.META_DIM for c in send_counts_t.tolist()],
        )

        # ---- 步骤 3：按门控权重加权聚合（FP32 累加，避免 FP16 精度损失）----
        out_tokens = torch.zeros((num_tokens, cfg.hidden_dim), dtype=torch.float32, device=device)
        for i in range(total_recv):
            src_token = int(recv_meta[i, 2].item())  # 原始 token 索引
            src_k = int(recv_meta[i, 3].item())  # 该 token 选中的第 k 个专家
            w = weights[src_token, src_k].to(torch.float32)
            out_tokens[src_token] += recv_buf[i].to(torch.float32) * w

        return out_tokens


class MoELayer(torch.nn.Module):
    def __init__(self, cfg: MoEConfig, rank: int, world_size: int):
        super().__init__()
        self.cfg = cfg
        self.a2a = AllToAll(cfg, rank, world_size)
        # 本地专家：每个是一个简单的两层 MLP
        self.local_experts = torch.nn.ModuleList([
            torch.nn.Sequential(
                torch.nn.Linear(cfg.hidden_dim, cfg.hidden_dim * 4),
                torch.nn.GELU(),
                torch.nn.Linear(cfg.hidden_dim * 4, cfg.hidden_dim),
            )
            for _ in range(cfg.num_experts // world_size)
        ])

    def forward(self, dp_x, indices, weights):
        # 1) Dispatch：token 跨卡送到各自选中的专家
        expert_x, expert_meta, expert_num_tokens = self.a2a.dispatch(dp_x, indices)

        # 2) MoE Function：各本地专家处理收到的 token
        expert_y = torch.empty_like(expert_x)
        for local_eid, expert in enumerate(self.local_experts):
            n = int(expert_num_tokens[local_eid].item())
            if n > 0:
                expert_y[local_eid, :n] = expert(expert_x[local_eid, :n])

        # 3) Combine：回传 + 加权聚合
        return self.a2a.combine(expert_y, expert_meta, expert_num_tokens,
                                weights, num_tokens=dp_x.shape[0])
