package com.fish.dp;

/**
 * @author: liuqi
 * @date: 2024/3/6
 */
public class Q70 {
    class Solution {
        public int climbStairs(int n) {
            if (n <= 2) {
                return n == 1 ? 1 : 2;
            }
            int dp2 = 1;
            int dp1 = 2;
            for (int i = 3; i <= n; i++) {
                int dp = dp1 + dp2;
                dp2 = dp1;
                dp1 = dp;
            }
            return dp1;
        }
    }
}
