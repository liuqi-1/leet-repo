from heapq import *
from typing import List


# 顶级
class Solution:
    def secondGreaterElement(self, nums: List[int]) -> List[int]:
        res = [-1] * len(nums)
        stack = []
        q = []

        for i in range(len(nums)):
            while len(q) and q[0][0] < nums[i]:
                res[q[0][1]] = nums[i]
                heappop(q)
            while len(stack) and nums[stack[-1]] < nums[i]:
                heappush(q, (nums[stack[-1]], stack[-1]))
                stack.pop()
            stack.append(i)

        return res

#
# 作者：力扣官方题解
# 链接：https: // leetcode.cn / problems / next - greater - element - iv /
# 来源：力扣（LeetCode）
# 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
