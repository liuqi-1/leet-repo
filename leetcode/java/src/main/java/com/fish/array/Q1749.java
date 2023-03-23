package com.fish.array;

/**
 * @author liuqi
 * @date 2023/8/8
 * https://leetcode.cn/problems/maximum-absolute-sum-of-any-subarray/description/
 */
public class Q1749 {
    /**
     * dp解法
     */
    class Solution {
        public int maxAbsoluteSum(int[] nums) {
            int minOrderSum = 0;
            int maxOrderSum = 0;
            int minSum = Integer.MAX_VALUE, maxSum = Integer.MIN_VALUE;
            for (int num : nums) {
                minOrderSum += num;
                if (minOrderSum > num) {
                    minOrderSum = num;
                }
                minSum = Math.min(minSum, minOrderSum);
                maxOrderSum += num;
                if (maxOrderSum < num) {
                    maxOrderSum = num;
                }
                maxSum = Math.max(maxSum, maxOrderSum);
            }
            return Math.max(Math.abs(minSum), Math.abs(maxSum));
        }
    }

    /**
     * 前缀和解法
     */
    class Solution1 {
        public int maxAbsoluteSum(int[] nums) {
            int[] preSum = new int[nums.length];
            preSum[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i];
            }
            int min = Math.min(preSum[0], 0), max = Math.max(0, preSum[0]), result = Math.abs(preSum[0]);
            for (int i = 1; i < nums.length; i++) {
                if (preSum[i] < min) {
                    result = Math.max(result, max - preSum[i]);
                    min = preSum[i];
                } else if (preSum[i] > max) {
                    result = Math.max(result, preSum[i] - min);
                    max = preSum[i];
                } else {
                    result = Math.max(result, Math.max(Math.abs(preSum[i] - min), Math.abs(preSum[i] - max)));
                }

            }
            return result;
        }
    }
}
