"""
https://leetcode.cn/problems/determine-if-two-strings-are-close/?envType=daily-question&envId=2023-11-30
"""
from collections import Counter


class Solution:
    def closeStrings(self, word1: str, word2: str) -> bool:
        cnt1 = Counter(word1)
        cnt2 = Counter(word2)
        return cnt1.keys() == cnt2.keys() and sorted(cnt1.values()) == sorted(cnt2.values())


ans = Solution().closeStrings("uau", 'ssx')
print(ans)


# 作者：力扣官方题解
# 链接：https://leetcode.cn/problems/determine-if-two-strings-are-close/
# 来源：力扣（LeetCode）
# 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


class Solution1:
    """
    思路对了，但是代码很奇怪，还是对Python的api不太熟悉
    """

    def __init__(self):
        self.cnt1 = [0] * 26
        self.cnt2 = [0] * 26

    def closeStrings(self, word1: str, word2: str) -> bool:
        for c in word1:
            self.cnt1[ord(c) - ord('a')] += 1
        for c in word2:
            self.cnt2[ord(c) - ord('a')] += 1
        for i in range(0, 26):
            if self.cnt1[i] == 0 and self.cnt2[i] != 0:
                return False
            if self.cnt2[i] == 0 and self.cnt1[i] != 0:
                return False
        self.cnt1.sort()
        self.cnt2.sort()
        for i in range(0, 26):
            if self.cnt1[i] != self.cnt2[i]:
                return False
        return True
