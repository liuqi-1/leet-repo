import numpy as np
import torch
import torch.nn.functional as F


def cross_entropy(logits, target):
    # sum(-ylogy^)
    # 计算log_softmax
    logits = logits - np.max(logits)
    log_probs = logits - np.log(np.sum(np.exp(logits)))
    # 计算cross entropy loss
    return np.sum(-1 * target * log_probs)


if __name__ == "__main__":
    logits_np = np.array([1, 2, 3, 4, 5])
    target_np = np.array([1, 0, 0, 0, 0])
    print(cross_entropy(logits_np, target_np))

    logtis_torch = torch.tensor([1, 2, 3, 4, 5], dtype=torch.float)
    target_torch = torch.tensor([1, 0, 0, 0, 0], dtype=torch.float)
    print(F.cross_entropy(logtis_torch, target_torch))
