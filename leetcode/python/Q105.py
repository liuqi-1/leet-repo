from typing import List, Optional
from TreeNode import TreeNode


# https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/?envType=daily-question&envId=2024-02-20

# 参考的官方题解，主要优化点在于，不需要重复的提取子数组，以及inorder索引方面的优化
class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        def myBuildTree(pre_left, pre_right, in_left, in_right):
            if pre_right < pre_left:
                return None
            root = TreeNode(preorder[pre_left])
            idx = index[preorder[pre_left]]
            size_left = idx - in_left
            size_right = in_right - idx
            root.left = myBuildTree(pre_left + 1, pre_left + size_left, in_left, idx - 1)
            root.right = myBuildTree(pre_right - size_right + 1, pre_right, idx + 1, in_right)
            return root

        index = {element: i for i, element in enumerate(inorder)}
        n = len(preorder)
        return myBuildTree(0, n - 1, 0, n - 1)


Solution().buildTree([3, 9, 20, 15, 7], [9, 3, 15, 20, 7])


# 自己的解法
class Solution1:

    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        if len(preorder) == 0:
            return None
        root = TreeNode(preorder[0])
        idx = inorder.index(preorder[0])
        root.left = self.buildTree(preorder[1:idx + 1], inorder[0:idx])
        root.right = self.buildTree(preorder[idx + 1: len(preorder) + 1], inorder[idx + 1: len(preorder) + 1])
        return root


l1 = [1, 2, 3]
print(type(l1))
print(l1[0:-1])
# 输出[1,2]，-1表示倒数第一个数字，等价于 len(l1)-1
