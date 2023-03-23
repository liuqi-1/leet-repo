package com.fish.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author liuqi
 * @date 2023/5/30
 *
 * 给出二叉树的根节点 root，树上每个节点都有一个不同的值。
 *
 * 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 *
 * 返回森林中的每棵树。你可以按任意顺序组织答案。
 */
public class Q1110 {
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
        private List<TreeNode> result = new ArrayList<>();
        Set<Integer> deleteSet = null;

        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            if (root == null) {
                return result;
            }
            deleteSet = new HashSet<>(to_delete.length);
            for (int i = 0; i < to_delete.length; i++) {
                deleteSet.add(to_delete[i]);
            }
            dfs(root, true);
            return result;
        }

        private TreeNode dfs(TreeNode node, boolean isRoot) {
            if (node == null) {
                return null;
            }
            if (!deleteSet.contains(node.val) && isRoot) {
                result.add(node);
            }
            boolean isDeleted = deleteSet.contains(node.val);
            node.left = dfs(node.left, isDeleted);
            node.right = dfs(node.right, isDeleted);
            if (isDeleted) {
                return null;
            }
            return node;
        }
    }
}
