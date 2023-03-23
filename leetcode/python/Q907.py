from typing import List


class Solution:
    def sumSubarrayMins(self, arr: List[int]) -> int:
        MOD = 10 ** 9 + 7
        n = len(arr)
        stack = []
        left = [0] * n
        right = [0] * n
        for i, x in enumerate(arr):
            while stack and arr[stack[-1]] > x:
                stack.pop()
            left[i] = (i - stack[-1]) if stack else i + 1
            stack.append(i)
        stack = []
        for i in range(len(arr) - 1, -1, -1):
            while stack and arr[stack[-1]] >= arr[i]:
                stack.pop()
            right[i] = (stack[-1] - i) if stack else len(arr) - i
            stack.append(i)
        ans = 0
        for i in range(0, len(arr)):
            ans += arr[i] * left[i] * right[i]
            ans %= MOD
        return ans


class Solution2:
    """
    动态规划 ： dp[i] = arr[i] * k + dp[i-k]
    """

    def sumSubarrayMins(self, arr: List[int]) -> int:
        MOD = 10 ** 9 + 7
        n = len(arr)
        stack = []
        dp = [0] * len(n)
        ans = 0
        for i, x in arr:
            while stack and arr[stack[-1]] > x:
                stack.pop()
            k = (i - stack[-1]) if stack else i + 1
            dp[i] = k * x + (dp[i - k] if stack else 0)
            ans = (ans + dp[i]) % MOD
            stack.append(i)
        return ans


print(Solution().sumSubarrayMins([3, 1, 2, 4]))


# print(2 + 1 >> 1)


class Solution1:
    def sumSubarrayMins(self, arr: List[int]) -> int:
        """
        超时了，时间复杂度本质上还是n^2
        :param arr:
        :return:
        """
        ans = 0
        mod = int(1e9 + 7)
        flag = [False for _ in range(0, len(arr))]
        for i in range(0, len(arr)):
            right = i + 1
            left = i - 1
            while right < len(arr) and arr[right] >= arr[i]:
                if arr[right] == arr[i]:
                    flag[right] = True
                right += 1
            if flag[i]:
                while left >= 0 and arr[left] > arr[i]:
                    left -= 1
            else:
                while left >= 0 and arr[left] >= arr[i]:
                    left -= 1
            right = right - i - 1
            left = i - left - 1
            ans = ans + (arr[i] * (right + 1) * (left + 1)) % mod
            ans = ans % mod
        return int(ans)

# a = 1
# b = 3
# print(b + (b - a) >> 1)
