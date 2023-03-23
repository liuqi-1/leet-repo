package com.fish.dp;

import java.util.Arrays;

/**
 * @author liuqi
 * @date 2023/11/22
 */
public class Q2304 {
    class Solution {
        public int minPathCost(int[][] grid, int[][] moveCost) {
            int m = grid.length;
            int n = grid[0].length;
            int[] dp = new int[n];
            int[] newDp = new int[n];
            for (int i = 0; i < m - 1; i++) {
                Arrays.fill(newDp, Integer.MAX_VALUE);
                for (int j = 0; j < n; j++) {
                    int[] cost = moveCost[grid[i][j]];
                    int val = grid[i][j];
                    for (int k = 0; k < n; k++) {
                        newDp[k] = Math.min(newDp[k], val + cost[k] + dp[j]);
                    }
                }
                int[] temp = dp;
                dp = newDp;
                newDp = temp;
            }
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                dp[i] += grid[m - 1][i];
                min = Math.min(min, dp[i]);
            }
            return min;
        }
    }
}
