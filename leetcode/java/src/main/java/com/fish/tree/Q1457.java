package com.fish.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * @author liuqi
 * @date 2023/11/25
 */
public class Q1457 {
    class Solution {
        int ans = 0;

        public void help(TreeNode root, int[] cnt) {
            if (root == null) {
                return;
            }
            cnt[root.val]++;
            if (root.left == null && root.right == null) {
                int oddCnt = 0;
                for (int i = 0; i < 10; i++) {
                    if (cnt[i] % 2 == 1) {
                        oddCnt++;
                    }
                }
                ans += (oddCnt <= 1 ? 1 : 0);
                cnt[root.val]--;
                return;
            }
            help(root.left, cnt);
            help(root.right, cnt);
            cnt[root.val]--;
        }

        public int pseudoPalindromicPaths(TreeNode root) {
            help(root, new int[10]);
            return ans;
        }
    }
}
