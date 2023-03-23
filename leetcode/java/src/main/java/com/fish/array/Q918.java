package com.fish.array;

/**
 * @author liuqi
 * @date 2023/7/20
 *
 * https://leetcode.cn/problems/maximum-sum-circular-subarray/
 */
public class Q918 {
    /**
     * 分为两种情况：
     * 情况1：不跨越环形数组，采用传统的前缀和解法
     * 情况2：跨越环形数组，分为[0,j-1]，[j,length-1]两部分
     */
    class Solution2 {
        public int maxSubarraySumCircular(int[] nums) {
            int res = nums[0];
            int pre = nums[0];
            int minPre = Math.min(nums[0], 0);
            int[] leftMax = new int[nums.length];
            leftMax[0] = nums[0] < 0 ? 0 : nums[0];
            for (int i = 1; i < nums.length; i++) {
                pre += nums[i];
                res = Math.max(res, pre - minPre);
                minPre = Math.min(minPre, pre);
                leftMax[i] = Math.max(leftMax[i - 1], pre);
            }
            int sum = 0;
            for (int i = nums.length - 1; i >= 1; i--) {
                sum += nums[i];
                res = Math.max(res, sum + leftMax[i - 1]);
            }
            return res;
        }
    }

    /**
     * 分为两种情况：
     * 情况1：不跨越环形数组，采用传统的前缀和解法
     * 情况2：在solution2情况2的基础上，转化为求最小和子数组
     */
    class Solution {
        public int maxSubarraySumCircular(int[] nums) {
            int res = nums[0];// 最大和
            int minSum = nums[0];// 最小和
            int pre = nums[0];
            int minPre = Math.min(nums[0], 0);// 最小前缀和
            int maxPre = Math.max(nums[0], 0);// 最大前缀和
            for (int i = 1; i < nums.length; i++) {
                pre += nums[i];
                res = Math.max(res, pre - minPre);
                minSum = Math.min(minSum, pre - maxPre);
                minPre = Math.min(pre, minPre);
                maxPre = Math.max(pre, maxPre);
            }
            if (res < 0) {
                // 数组中没有大于等于0的元素，则minSum将会选取数组中的所有元素，导致数组为空
                return res;
            }
            return Math.max(res, pre - minSum);
        }
    }


    /**
     * 前缀和解法，寻找长度小于等于n的子数组的最大和
     */
    class Solution1 {
        public int maxSubarraySumCircular(int[] nums) {
            int length = nums.length;
            int[] preSum = new int[length * 2];
            preSum[0] = nums[0];
            for (int i = 1; i < length; i++) {
                preSum[i] = nums[i] + preSum[i - 1];
            }
            for (int i = 0; i < length; i++) {
                preSum[i + length] = preSum[i] + preSum[length - 1];
            }
            int min = preSum[0];
            int minIdx = 0;
            int result = Integer.MIN_VALUE;
            for (int i = 1; i < length * 2; i++) {
                if (i - minIdx > length) {
                    min = Integer.MAX_VALUE;
                    for (int k = i - length; k <= i - 1; k++) {
                        if (min >= preSum[k]) {
                            min = preSum[k];
                            minIdx = k;
                        }
                    }
                }
                result = Math.max(result, preSum[i] - min);
                if (min >= preSum[i]) {
                    min = preSum[i];
                    minIdx = i;
                }
            }
            return result;
        }
    }
}
