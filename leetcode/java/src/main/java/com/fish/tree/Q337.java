package com.fish.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuqi
 * @date 2023/9/18
 */
public class Q337 {

    /**
     * 行行行，dp的基本优化方法，竟然没有想到~
     */
    class Solution {

        /**
         * @param root
         * @return [notRob, rob]
         */
        public int[] help(TreeNode root) {
            if (root == null) {
                return new int[]{0, 0};
            }
            int[] arrLeft = help(root.left);
            int[] arrRight = help(root.right);
            int[] result = new int[2];
            result[0] = Math.max(arrLeft[0], arrLeft[1]) + Math.max(arrRight[0], arrRight[1]);
            result[1] = root.val + arrLeft[0] + arrRight[0];
            return result;
        }

        public int rob(TreeNode root) {
            int[] arr = help(root);
            return Math.max(arr[0], arr[1]);
        }
    }


    /**
     * 正确版本，但是还有优化空间，不再需要哈希表，直接用滚动变量就行
     */
    class Solution1 {
        Map<TreeNode, Integer> notRob = new HashMap();
        Map<TreeNode, Integer> rob = new HashMap();

        public void help(TreeNode root) {
            if (root == null) {
                return;
            }
            help(root.left);
            help(root.right);
            int leftMax = Math.max(rob.get(root.left), notRob.get(root.left));
            int rightMax = Math.max(rob.get(root.right), notRob.get(root.right));
            notRob.put(root, leftMax + rightMax);
            rob.put(root, root.val + notRob.get(root.left) + notRob.get(root.right));
        }

        public int rob(TreeNode root) {
            notRob.put(null, 0);
            rob.put(null, 0);
            help(root);
            return Math.max(notRob.get(root), rob.get(root));
        }
    }
}
