package com.fish.dp;

/**
 * @author liuqi
 * @date 2023/6/21
 * <p>
 * https://leetcode.cn/leetbook/read/tencent/x5dugs/
 * <p>
 * 买股票的最佳时机
 */
public class Qx5dugs {
    class Solution {
        public int maxProfit(int[] prices) {
            int profit = 0;
            int minPrice = prices[0];
            for (int i = 1; i < prices.length; i++) {
                profit = Math.max(profit, prices[i] - minPrice);
                minPrice = Math.min(minPrice, prices[i]);
            }
            return profit;
        }
    }
}
