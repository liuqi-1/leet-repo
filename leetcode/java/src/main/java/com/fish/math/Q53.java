package com.fish.math;

/**
 * @author: liuqi
 * @date: 2024/3/7
 */
public class Q53 {
    /**
     * dp算法，dp[i]为以nums[i]结尾的最大子数组和
     */
    class Solution1 {
        public int maxSubArray(int[] nums) {
            int len = nums.length;
            int ans = Integer.MIN_VALUE;
            int last = Integer.MIN_VALUE;
            for (int i = 0; i < len; i++) {
                int now = nums[i];
                if (last > 0) {
                    now += last;
                }
                last = now;
                ans = Math.max(last, ans);
            }
            return ans;
        }
    }
}
