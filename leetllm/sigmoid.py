import numpy as np


def sigmoid(x):
    if x <= 0:
        return np.exp(x) / (1 + np.exp(x))
    else:
        return 1 / (1 + np.exp(-1 * x))


xs = [1, 2, 3, 4, 1e9, -1 - 2 - 3, -1e9]
for x in xs:
    print(f"x={x}, sigmoid(x)={sigmoid(x)}")
