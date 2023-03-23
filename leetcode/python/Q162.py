from typing import List


# Log(n)的解法
class Solution:
    def findPeakElement(self, nums: List[int]) -> int:
        n = len(nums)

        # 辅助函数，输入下标 i，返回 nums[i] 的值
        # 方便处理 nums[-1] 以及 nums[n] 的边界情况
        def get(i: int) -> int:
            if i == -1 or i == n:
                return float('-inf')
            return nums[i]

        left, right, ans = 0, n - 1, -1
        while left <= right:
            mid = (left + right) // 2
            if get(mid - 1) < get(mid) > get(mid + 1):
                ans = mid
                break
            if get(mid) < get(mid + 1):
                left = mid + 1
            else:
                right = mid - 1

        return ans


# 作者：力扣官方题解
# 链接：https: // leetcode.cn / problems / find - peak - element /
# 来源：力扣（LeetCode）
# 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


# 常规解法，时间复杂度O(n)
class Solution:
    def findPeakElement(self, nums: List[int]) -> int:
        minV = int(-2 ** 31 - 1)
        nums.insert(0, minV)
        nums.append(minV)
        for i in range(1, len(nums) + 1):
            if nums[i] > nums[i - 1] and nums[i] > nums[i + 1]:
                return i - 1
        return -1
