# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
from typing import List, Optional

from tree.TreeNode import TreeNode


class Solution:
    def buildTree(self, inorder: List[int], postorder: List[int]) -> Optional[TreeNode]:
        def myBuildTree(in_left, in_right, po_left, po_right):
            if po_right < po_left or in_right < in_left:
                return None
            root = TreeNode(postorder[po_right])
            idx = index[postorder[po_right]]
            size_left = idx - in_left
            size_right = in_right - idx
            root.left = myBuildTree(in_left, idx - 1, po_left, po_left + size_left - 1)
            root.right = myBuildTree(idx + 1, in_right, po_right - size_right, po_right - 1)  # po_right要减1
            return root

        # 建立inorder的索引
        index = {element: i for i, element in enumerate(inorder)}
        # 构造二叉树并返回
        n = len(inorder)
        return myBuildTree(0, n - 1, 0, n - 1)


Solution().buildTree([1, 2, 3], [1, 3, 2])
