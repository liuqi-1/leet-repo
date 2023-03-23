package com.fish.dp;

/**
 * @author liuqi
 * @date 2023/6/21
 * https://leetcode.cn/leetbook/read/tencent/x507kd/
 * <p>
 * todo DP算法的优化思路可以参考这个题
 */
public class Qx507kd {
    class Solution {
        /**
         * 初始版本（自己写的）
         *
         * @param prices
         * @return
         */
        public int maxProfit0(int[] prices) {
            int[][] dp = new int[prices.length][3];
            dp[0][2] = prices[0];
            for (int i = 1; i < prices.length; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - dp[i - 1][2]);
                if (dp[i - 1][1] - dp[i - 1][2] >= dp[i - 1][0] - prices[i]) {
                    dp[i][1] = dp[i - 1][1];
                    dp[i][2] = dp[i - 1][2];
                } else if (dp[i - 1][1] - dp[i - 1][2] < dp[i - 1][0] - prices[i]) {
                    dp[i][1] = dp[i - 1][0];
                    dp[i][2] = prices[i];
                }
            }
            return dp[prices.length - 1][0];
        }

        /**
         * 优化版本1，降低了空间复杂度（常规优化，看了一眼官方题解之后想起来的）
         *
         * @param prices
         * @return
         */
        public int maxProfit1(int[] prices) {
            int a = 0, b = 0, c = prices[0];
            for (int i = 1; i < prices.length; i++) {
                int newA = 0, newB = 0, newC = 0;
                newA = Math.max(a, b + prices[i] - c);
                if (b - c >= a - prices[i]) {
                    newB = b;
                    newC = c;
                } else if (b - c < a - prices[i]) {
                    newB = a;
                    newC = prices[i];
                }
                a = newA;
                b = newB;
                c = newC;
            }
            return a;
        }

        /**
         * 最优解，优化：去掉c变量，a表示当前不持有股票的最大纯利润，b表示当前持有股票的最大纯利润
         * 纯看官方题解
         *
         * @param prices
         * @return
         */
        public int maxProfit2(int[] prices) {
            int a = 0, b = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                int newA = Math.max(a, b + prices[i]);
                int newB = Math.max(a - prices[i], b);
                a = newA;
                b = newB;
            }
            return a;
        }


        /**
         * 神奇的贪心算法，由于可以当天同时执行买和卖两种操作
         * 因此贪心策略为：只要今天的股价比昨天高，则执行昨天买今天卖
         *
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {
            int profit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }
    }
}
