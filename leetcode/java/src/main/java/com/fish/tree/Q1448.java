package com.fish.tree;

/**
 * @author liuqi
 * @date 2023/8/25
 * <p>
 * https://leetcode.cn/problems/count-good-nodes-in-binary-tree/
 */
public class Q1448 {

    class Solution {
        int result = 0;

        public void help(TreeNode root, int maxValue) {
            if (root == null) {
                return;
            }
            if (maxValue <= root.val) {
                result++;
                maxValue = root.val;
            }
            help(root.left, maxValue);
            help(root.right, maxValue);
        }

        public int goodNodes(TreeNode root) {
            help(root, Integer.MIN_VALUE);
            return result;
        }
    }
}
