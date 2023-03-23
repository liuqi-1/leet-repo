from typing import List


# 官方的差分数组
class Solution:
    def carPooling(self, trips: List[List[int]], capacity: int) -> bool:
        diff = [0] * 1001
        for cnt, fromC, toC in trips:
            diff[fromC] += cnt
            diff[toC] -= cnt
        count = 0
        for d in diff:
            count += d
            if count > capacity:
                return False
        return True


# 自己的不知名解法
class Solution1:
    def carPooling(self, trips: List[List[int]], capacity: int) -> bool:
        fromS = dict()
        toS = dict()
        for cnt, fromC, toC in trips:
            fromS[fromC] = cnt + fromS.get(fromC, 0)
            toS[toC] = cnt + toS.get(toC, 0)
        cnt = 0
        for i in range(0, 1001):
            if i in fromS:
                cnt += fromS.get(i)
            if i in toS:
                cnt -= toS.get(i)
            if cnt > capacity:
                return False
        return True
