from TreeNode import TreeNode


class Solution:
    def help(self, root: TreeNode, bgS: int) -> int:
        if not root:
            return 0
        v = root.val
        rightSum = self.help(root.right, bgS)
        root.val = root.val + rightSum + bgS
        leftSum = self.help(root.left, root.val)
        return v + rightSum + leftSum

    def bstToGst(self, root: TreeNode) -> TreeNode:
        self.help(root, 0)
        return root
