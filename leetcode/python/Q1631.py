'''

https://leetcode.cn/problems/path-with-minimum-effort/solutions/581109/zui-xiao-ti-li-xiao-hao-lu-jing-by-leetc-3q2j/?envType=daily-question&envId=2023-12-11
解决方法：并查集、二分查找（求最大值的最小值通用方法）
'''

import collections
import sys
from typing import List


# 并查集模板
class UnionFind:
    def __init__(self, n: int):
        self.parent = list(range(n))
        self.size = [1] * n
        self.n = n
        # 当前连通分量数目
        self.setCount = n

    def findset(self, x: int) -> int:
        if self.parent[x] == x:
            return x
        self.parent[x] = self.findset(self.parent[x])
        return self.parent[x]

    def unite(self, x: int, y: int) -> bool:
        x, y = self.findset(x), self.findset(y)
        if x == y:
            return False
        if self.size[x] < self.size[y]:
            x, y = y, x
        self.parent[y] = x
        self.size[x] += self.size[y]
        self.setCount -= 1
        return True

    def connected(self, x: int, y: int) -> bool:
        x, y = self.findset(x), self.findset(y)
        return x == y


class Solution:
    def minimumEffortPath(self, heights: List[List[int]]) -> int:
        m, n = len(heights), len(heights[0])
        edges = list()
        for i in range(m):
            for j in range(n):
                iden = i * n + j
                if i > 0:
                    edges.append((iden - n, iden, abs(heights[i][j] - heights[i - 1][j])))
                if j > 0:
                    edges.append((iden - 1, iden, abs(heights[i][j] - heights[i][j - 1])))

        edges.sort(key=lambda e: e[2])

        uf = UnionFind(m * n)
        ans = 0
        for x, y, v in edges:
            uf.unite(x, y)
            if uf.connected(0, m * n - 1):
                ans = v
                break

        return ans


class Solution123:
    def minimumEffortPath(self, heights: List[List[int]]) -> int:
        m, n = len(heights), len(heights[0])
        left, right, ans = 0, 10 ** 6 - 1, 0

        while left <= right:
            mid = (left + right) // 2
            q = collections.deque([(0, 0)])
            seen = {(0, 0)}

            while q:
                x, y = q.popleft()
                for nx, ny in [(x - 1, y), (x + 1, y), (x, y - 1), (x, y + 1)]:
                    if 0 <= nx < m and 0 <= ny < n and (nx, ny) not in seen and abs(
                            heights[x][y] - heights[nx][ny]) <= mid:
                        q.append((nx, ny))
                        seen.add((nx, ny))

            if (m - 1, n - 1) in seen:
                ans = mid
                right = mid - 1
            else:
                left = mid + 1

        return ans


# 作者：力扣官方题解
# 链接：https: // leetcode.cn / problems / path -
# with-minimum - effort /
#     来源：力扣（LeetCode）
#     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


# 超时
class Solution11:
    def __init__(self):
        self.ans = sys.maxsize
        self.visited = None

    def dfs(self, heights, x, y, maxValue, fromValue):
        # 数组越界
        if x < 0 or x >= len(heights) or y < 0 or y >= len(heights[0]):
            return
        # 已经访问过的位置
        if self.visited[x][y]:
            return
        # 标志当前位置已经访问过
        self.visited[x][y] = True
        # 计算最大高度差
        maxValue = max(maxValue, abs(fromValue - heights[x][y]))
        # 到达终点
        if x == (len(heights) - 1) and (y == len(heights[0]) - 1):
            self.ans = min(self.ans, maxValue)
        # 没有达到终点，向四个方向扩散
        self.dfs(heights, x - 1, y, maxValue, heights[x][y])
        self.dfs(heights, x + 1, y, maxValue, heights[x][y])
        self.dfs(heights, x, y + 1, maxValue, heights[x][y])
        self.dfs(heights, x, y - 1, maxValue, heights[x][y])
        # 标记当前节点未被访问
        self.visited[x][y] = False

    def minimumEffortPath(self, heights: List[List[int]]) -> int:
        self.visited = [[False for j in range(len(heights[0]))] for i in range(len(heights))]
        self.dfs(heights, 0, 0, 0, heights[0][0])
        return self.ans


print(Solution().minimumEffortPath([[1, 2, 2], [3, 8, 2], [5, 3, 5]]))
