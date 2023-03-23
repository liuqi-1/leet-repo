package com.fish.dp;

/**
 * @author liuqi
 * @date 2023/6/20
 * <p>
 * https://leetcode.cn/leetbook/read/tencent/x5vk4t/
 */
public class Qx5vk4t {
    class Solution {
        public int climbStairs(int n) {
            int[] dp = new int[n + 1];
            if (n < 3) {
                return n == 1 ? 1 : 2;
            }
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }
    }
}
