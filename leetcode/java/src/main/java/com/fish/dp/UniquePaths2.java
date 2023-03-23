package com.fish.dp;

/**
 * @author liuqi
 * @date 2023/8/4
 * <p>
 * https://leetcode.cn/problems/unique-paths-ii/
 */
public class UniquePaths2 {
    class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;
            int[][] dp = new int[m][n];
            dp[0][0] = 1;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (obstacleGrid[i][j] == 1) {
                        dp[i][j] = 0;
                        continue;
                    }
                    if (i - 1 >= 0) {
                        dp[i][j] += dp[i - 1][j];
                    }
                    if (j - 1 >= 0) {
                        dp[i][j] += dp[i][j - 1];
                    }
                }
            }
            return dp[m - 1][n - 1];
        }
    }
}
