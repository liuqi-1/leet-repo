# Definition for a binary tree node.
from typing import Optional
from TreeNode import TreeNode


class Solution:
    def __init__(self):
        self.ans = 0
        self.cnt = [0 for i in range(0, 10)]

    def help(self, root: TreeNode):
        if not root:
            return
        self.cnt[root.val] += 1
        if root.left is None and root.right is None:
            oddCnt = 0
            for i in self.cnt:
                if i % 2 == 1:
                    oddCnt += 1
            if oddCnt <= 1:
                self.ans += 1
                self.cnt[root.val] -= 1
                return
        self.help(root.left)
        self.help(root.right)
        self.cnt[root.val] -= 1

    def pseudoPalindromicPaths(self, root: Optional[TreeNode]) -> int:
        self.help(root)
        return self.ans


Solution().pseudoPalindromicPaths(TreeNode())
