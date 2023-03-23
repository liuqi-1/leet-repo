package com.fish.tree;

/**
 * @author liuqi
 * @date 2023/8/14
 * <p>
 * https://leetcode.cn/problems/merge-two-binary-trees/
 */
public class Q617 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        public TreeNode merge(TreeNode left, TreeNode right) {
            if (left != null && right != null) {
                left.val += right.val;
                left.left = mergeTrees(left.left, right.left);
                left.right = mergeTrees(left.right, right.right);
                return left;
            }
            if (left == null) {
                return right;
            }
            return left;
        }

        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            return merge(root1, root2);
        }
    }
}
