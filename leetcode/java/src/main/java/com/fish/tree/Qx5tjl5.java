package com.fish.tree;

/**
 * @author liuqi
 * @date 2023/7/24
 * <p>
 * https://leetcode.cn/leetbook/read/tencent/x5tjl5/
 */
public class Qx5tjl5 {

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
        private int result = 0;

        public int dfs(TreeNode node, int cnt, int k) {
            if (node == null || cnt >= k) {
                return cnt;
            }
            cnt = dfs(node.left, cnt, k);
            cnt++;
            if (cnt == k) {
                result = node.val;
                return cnt;
            }
            return dfs(node.right, cnt, k);
        }

        public int kthSmallest(TreeNode root, int k) {
            dfs(root, 0, k);
            return result;
        }
    }
}
