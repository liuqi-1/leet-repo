# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution(object):
    def helper(self, root, depth):
        if not root:
            return
        self.maxDepth = max(self.maxDepth, depth)
        self.helper(root.left, depth + 1)
        self.helper(root.right, depth + 1)

    def maxDepth(self, root):
        """
        :type root: Optional[TreeNode]
        :rtype: int
        """
        self.maxDepth = 0
        self.helper(root, 1)
        return self.maxDepth
