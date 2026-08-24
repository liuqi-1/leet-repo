import torch.nn as nn
import torch.nn.functional as F
from torch import Tensor


class MHA(nn.Module):
    def __init__(self, head, head_dim, hidden_dim):
        super(MHA, self).__init__()
        self.head = head  # 注意力头数
        self.head_dim = head_dim  # 注意力维度
        self.hidden_dim = hidden_dim  # 特征维度

        self.qkv_proj = nn.Linear(hidden_dim, 3 * head * head_dim)
        self.o_proj = nn.Linear(head * head_dim, hidden_dim)

    def forward(self, x: Tensor, mask: Tensor = None):
        # 投影映射计算出来QKV
        b, n, _ = x.shape
        qkv = self.qkv_proj(x)
        q, k, v = qkv.view(b, n, 3, self.head, -1).permute(2, 0, 3, 1, 4)

        # 开始计算Scores
        scores = q @ k.transpose(-2, -1)
        scores = scores / self.head_dim ** 0.5
        if mask is not None:
            scores = scores + mask

        # 计算softmax
        probs = F.softmax(scores, dim=-1)

        # 计算输出
        out = probs @ v
        out = out.permute(0, 2, 1, 3).contiguous().view(b, n, self.head * self.head_dim)  # TODO 不加continuous会报错，需要搞清楚原因
        return self.o_proj(out)
