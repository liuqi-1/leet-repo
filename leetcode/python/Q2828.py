from typing import List


# 官方解法
class Solution:
    def isAcronym(self, words: List[str], s: str) -> bool:
        return len(words) == len(s) and all(words[i][0] == s[i] for i in range(len(s)))


# 作者：力扣官方题解
# 链接：https://leetcode.cn/problems/check-if-a-string-is-an-acronym-of-words/
# 来源：力扣（LeetCode）
# 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

# 我的解法
class Solution:
    def isAcronym(self, words: List[str], s: str) -> bool:
        target = ""
        for word in words:
            target += word[0]
        return s == target
