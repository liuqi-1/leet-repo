from typing import List
from queue import Queue


# 并查集
class UnionFind:
    def __init__(self, n):
        self.f = list(range(n))
        self.rank = [0] * n

    def merge(self, x, y):
        rx = self.find(x)
        ry = self.find(y)
        if rx != ry:
            if self.rank[rx] > self.rank[ry]:
                self.f[ry] = rx
            elif self.rank[rx] < self.rank[ry]:
                self.f[rx] = ry
            else:
                self.f[ry] = rx
                self.rank[rx] += 1

    def find(self, x):
        if x != self.f[x]:
            self.f[x] = self.find(self.f[x])
        return self.f[x]

    def count(self):
        cnt = 0
        rt = self.find(0)
        for i in range(len(self.f)):
            if rt == self.find(i):
                cnt += 1
        return cnt


class Solution:
    def reachableNodes(self, n: int, edges: List[List[int]], restricted: List[int]) -> int:
        is_restricted = [0] * n
        for x in restricted:
            is_restricted[x] = 1

        uf = UnionFind(n)
        for v in edges:
            if is_restricted[v[0]] or is_restricted[v[1]]:
                continue
            uf.merge(v[0], v[1])
        return uf.count()


# 用dfs优化
class Solution2:
    def reachableNodes(self, n: int, edges: List[List[int]], restricted: List[int]) -> int:
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        restrictedSet = set(restricted)

        def dfs(root):
            ans = 1
            restrictedSet.add(root)
            for node in g[root]:
                if node not in restrictedSet:
                    ans += dfs(node)
            return ans

        return dfs(0)


# 自己写的初始版本，时间上不好
class Solution1:
    def reachableNodes(self, n: int, edges: List[List[int]], restricted: List[int]) -> int:
        rSet = set(restricted)
        edgeSets = [set() for _ in range(n)]
        for a, b in edges:
            edgeSets[a].add(b)
            edgeSets[b].add(a)
        q = Queue()
        q.put(0)
        rSet.add(0)
        ans = 0
        while not q.empty():
            curr = q.get()
            ans += 1
            edges = edgeSets[curr]
            for a in edges:
                if a not in rSet:
                    q.put(a)
                    rSet.add(a)
        return ans
