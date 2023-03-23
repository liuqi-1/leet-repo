"""
https://leetcode.cn/problems/smallest-number-in-infinite-set/description/?envType=daily-question&envId=2023-11-29
"""

import heapq


class SmallestInfiniteSet:
    def __init__(self):
        self.vis = [False] * 1010  # 减少heapq.heappush次数
        self.q = []
        self.idx = 1

    def popSmallest(self):
        ans = -1
        if self.q:
            ans = heapq.heappop(self.q)
            self.vis[ans] = False
        else:
            ans = self.idx
            self.idx += 1
        return ans

    def addBack(self, x):
        if x >= self.idx or self.vis[x]:
            return
        if x == self.idx - 1:
            self.idx -= 1
        else:
            heapq.heappush(self.q, x)
            self.vis[x] = True
#
# 作者：宫水三叶
# 链接：https://leetcode.cn/problems/smallest-number-in-infinite-set/
# 来源：力扣（LeetCode）
# 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


class SmallestInfiniteSet1:

    def __init__(self):
        self.set = set()
        self.min = 1
    def popSmallest(self) -> int:
        self.set.add(self.min)
        ans = self.min
        self.min += 1
        while self.min in self.set:
            self.min += 1
        return ans

    def addBack(self, num: int) -> None:
        if num in self.set:
            self.set.remove(num)
        if num < self.min:
            self.min = num


# Your SmallestInfiniteSet object will be instantiated and called as such:
obj = SmallestInfiniteSet()
obj.addBack(2)
param_1 = obj.popSmallest()
print(param_1)
