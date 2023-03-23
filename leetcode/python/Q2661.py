from typing import List


# 宫水三叶大佬
class Solution2:
    def firstCompleteIndex(self, arr: List[int], mat: List[List[int]]) -> int:
        n, m = len(mat), len(mat[0])
        mapping = {mat[i][j]: (i, j) for i in range(n) for j in range(m)}
        c1, c2 = [0] * n, [0] * m
        for i in range(n * m):
            x, y = mapping[arr[i]]
            c1[x], c2[y] = c1[x] + 1, c2[y] + 1
            if c1[x] == m or c2[y] == n: return i
        return -1  # never


# 官方题解
class Solution:
    def firstCompleteIndex(self, arr: List[int], mat: List[List[int]]) -> int:
        n, m = len(mat), len(mat[0])
        mp = {}
        for i in range(n):
            for j in range(m):
                mp[mat[i][j]] = [i, j]
        rowCnt, colCnt = [0] * n, [0] * m
        for i in range(len(arr)):
            v = mp[arr[i]]
            rowCnt[v[0]] += 1
            if rowCnt[v[0]] == m:
                return i
            colCnt[v[1]] += 1
            if colCnt[v[1]] == n:
                return i
        return -1


# 作者：力扣官方题解
# 链接：https://leetcode.cn/problems/first-completely-painted-row-or-column/
# 来源：力扣（LeetCode）
# 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

# 思路对了，但是代码不太好
import numpy as np


class Solution1:
    def firstCompleteIndex(self, arr: List[int], mat: List[List[int]]) -> int:
        m = len(mat)
        n = len(mat[0])
        idxMap = np.zeros((m * n + 1, 2), dtype=int)
        for i in range(0, m):
            for j in range(0, n):
                idxMap[mat[i][j]] = [i, j]
        rowCnt = [n] * m
        colCnt = [m] * n
        for i, x in enumerate(arr):
            rowIdx = idxMap[x][0]
            colIdx = idxMap[x][1]
            rowCnt[rowIdx] -= 1
            colCnt[colIdx] -= 1
            if rowCnt[rowIdx] == 0 or colCnt[colIdx] == 0:
                return i
        return -1


mat = [[3, 2, 5], [1, 4, 6], [8, 7, 9]]
arr = [2, 8, 7, 4, 1, 3, 5, 6, 9]
Solution().firstCompleteIndex(arr, mat)
