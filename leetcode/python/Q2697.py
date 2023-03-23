class Solution:
    def makeSmallestPalindrome(self, s: str) -> str:
        char_arr = list(s)
        left = 0
        right = len(char_arr) - 1
        while left < right:
            if char_arr[left] != char_arr[right]:
                ans = min(char_arr[left], char_arr[right])
                char_arr[left] = ans
                char_arr[right] = ans
            left += 1
            right -= 1
        return ''.join(char_arr)
