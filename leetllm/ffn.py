import torch
import torch.nn.functional as F
import torch.nn as nn


class FFN(nn.Module):
    def __init__(self, dim, hidden_dim):
        super().__init__()
        self.linear1 = nn.Linear(dim, hidden_dim)  # 升维线性层
        self.linear2 = nn.Linear(hidden_dim, dim)  # 降维线性层
        self.activation = F.relu

    def forward(self, features):
        return self.linear2(self.activation(self.linear1(features)))
