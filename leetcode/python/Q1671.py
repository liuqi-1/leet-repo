from typing import List


class Solution:
    def minimumMountainRemovals(self, nums: List[int]) -> int:
        pre = self.getLISArray(nums)
        suf = self.getLISArray(nums[::-1])[::-1]
        ans = 0

        for pre_i, suf_i in zip(pre, suf):
            if pre_i > 1 and suf_i > 1:
                ans = max(ans, pre_i + suf_i - 1)

        return len(nums) - ans

    def getLISArray(self, nums: List[int]) -> List[int]:
        n = len(nums)
        dp = [1] * n

        for i in range(n):
            for j in range(i):
                if nums[j] < nums[i]:
                    dp[i] = max(dp[i], dp[j] + 1)

        return dp


# 作者：力扣官方题解
# 链接：https: // leetcode.cn / problems / minimum - number - of - removals - to - make - mountain - array /
# 来源：力扣（LeetCode）
# 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


class Solution1:
    def minimumMountainRemovals(self, nums: List[int]) -> int:
        n = len(nums)
        prefix = [0] * n
        stack = []
        for i in range(n):
            while stack and stack[-1] >= nums[i]:
                stack.pop()
            prefix[i] = i - len(stack)
            stack.append(nums[i])
        suffix = [0] * n
        stack.clear()
        ans = n
        for i in range(n - 1, -1, -1):
            while stack and stack[-1] >= nums[i]:
                stack.pop()
            suffix[i] = n - 1 - i - len(stack)
            stack.append(nums[i])
            ans = min(ans, suffix[i] + prefix[i])
        return ans


Solution().minimumMountainRemovals([23, 47, 63, 72, 81, 99, 88, 55, 21, 33, 32])
