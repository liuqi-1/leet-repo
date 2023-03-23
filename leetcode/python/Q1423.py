"""
https://leetcode.cn/problems/maximum-points-you-can-obtain-from-cards/?envType=daily-question&envId=2023-12-03
"""
from typing import List


class Solution:
    def maxScore(self, cardPoints: List[int], k: int) -> int:
        sum = 0
        for i in range(0, k):
            sum += cardPoints[i]
        ans = sum
        for i in range(k - 1, -1, -1):
            sum -= cardPoints[i]
            sum += cardPoints[len(cardPoints) + i - k]
            ans = max(ans, sum)
        return ans


Solution().maxScore([1, 2, 3, 4, 5, 6, 1], 3)
