from typing import List


class Solution:
    def averageValue(self, nums: List[int]) -> int:
        cnt = 0
        sum = 0
        for num in nums:
            if num % 6 == 0:
                cnt += 1
                sum += num
        return 0 if cnt==0 else sum//cnt