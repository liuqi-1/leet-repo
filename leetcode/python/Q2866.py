from typing import List


class Solution:
    def maximumSumOfHeights(self, maxHeights: List[int]) -> int:
        n = len(maxHeights)
        stack1 = []
        prefix = [0] * n
        suffix = [0] * n
        for i in range(n):
            while len(stack1) > 0 and maxHeights[i] < maxHeights[stack1[-1]]:
                stack1.pop()
            if len(stack1) == 0:
                prefix[i] = maxHeights[i] * i
            else:
                prefix[i] = prefix[stack1[-1]] + maxHeights[stack1[-1]] + maxHeights[i] * (i - stack1[-1] - 1)
            stack1.append(i)
        stack1.clear()
        ans = 0
        for i in range(n - 1, -1, -1):
            while len(stack1) > 0 and maxHeights[i] < maxHeights[stack1[-1]]:
                stack1.pop()
            if len(stack1) == 0:
                suffix[i] = maxHeights[i] * (n - 1 - i)
            else:
                suffix[i] = suffix[stack1[-1]] + maxHeights[stack1[-1]] + maxHeights[i] * (stack1[-1] - i - 1)
            ans = max(ans, prefix[i] + suffix[i] + maxHeights[i])
            stack1.append(i)
        return ans


Solution().maximumSumOfHeights([5, 3, 4, 1, 1])
