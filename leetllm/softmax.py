import numpy as np
import torch
import torch.nn.functional as F


# 普通版本的Softmax
def softmax(scores):
    return np.exp(scores) / np.sum(np.exp(scores))


# 利用平移不变性缓解Softmax的数值溢出问题
def safe_softmax(scores):
    scores = scores - np.max(scores)
    return np.exp(scores) / np.sum(np.exp(scores))


# log_softmax
def log_softmax(scores):
    scores = scores - np.max(scores)
    return scores - np.log(np.sum(np.exp(scores)))


# pytorch的库接口
def torch_softmax(scores):
    return F.log_softmax(scores, dim=0)


# pytorch结合交叉熵算损失值
def cross_entropy(logits, targets):
    return F.cross_entropy(logits, targets)  # 自带log_softmax


if __name__ == "__main__":
    # scores = np.array([123, 456, 789])
    # print(softmax(scores))
    # print(safe_softmax(scores))
    # print(log_softmax(scores))

    # logits = torch.rand(10)
    # target = torch.tensor([1])
    # print(torch_softmax(logits))
    # print(cross_entropy(logits, target))
    
    scores = np.array([123,456,789])
    logits = torch.tensor([123,456,789],dtype=torch.float)
    print(log_softmax(scores))
    print(torch_softmax(logits))
