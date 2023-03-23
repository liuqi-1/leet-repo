package com.fish.dp;

import org.junit.jupiter.api.Test;

/**
 * @author liuqi
 * @date 2023/6/20
 * <p>
 * https://leetcode.cn/leetbook/read/tencent/x5w3sr/
 *
 * 优秀题解：https://leetcode.cn/problems/maximum-subarray/solutions/9058/dong-tai-gui-hua-fen-zhi-fa-python-dai-ma-java-dai/
 * （除了DP方法，还可以用分治法
 */
public class Qx5w3sr {
    /**
     * 解法1，分为当前数选或者不选两种情况
     */
    class Solution1 {
        public int maxSubArray(int[] nums) {
            int[][] dp = new int[nums.length][2];
            dp[0][1] = nums[0];
            dp[0][0] = Integer.MIN_VALUE;
            for (int i = 1; i < nums.length; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = Math.max(dp[i - 1][1], 0) + nums[i];
            }
            return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
        }
    }

    /**
     * 解法2：dp[i]定义为，以数字Nums[i]结尾的最大连续子数组和
     */
    class Solution {
        public int maxSubArray1(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            int result = dp[0];
            for (int i = 1; i < nums.length; i++) {
                if (dp[i - 1] <= 0) {
                    dp[i] = nums[i];
                } else {
                    dp[i] = dp[i - 1] + nums[i];
                }
                result = Math.max(result, dp[i]);
            }
            return result;
        }

        // 优化版本，降低空间复杂度
        public int maxSubArray(int[] nums) {
            int dp = nums[0];
            int result = dp;
            for (int i = 1; i < nums.length; i++) {
                dp = Math.max(nums[i], nums[i] + dp);
                result = Math.max(result, dp);
            }
            return result;
        }
    }

    @Test
    public void test() {
        int r = new Solution().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(r);
    }
}
