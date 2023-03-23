package com.fish.dp;

/**
 * @author liuqi
 * @date 2023/11/20
 */
public class Q53 {
    /**
     * 分治法
     */
    public class Solution {

        public int maxSubArray(int[] nums) {
            int len = nums.length;
            if (len == 0) {
                return 0;
            }
            return maxSubArraySum(nums, 0, len - 1);
        }

        private int maxCrossingSum(int[] nums, int left, int mid, int right) {
            // 一定会包含 nums[mid] 这个元素
            int sum = 0;
            int leftSum = Integer.MIN_VALUE;
            // 左半边包含 nums[mid] 元素，最多可以到什么地方
            // 走到最边界，看看最值是什么
            // 计算以 mid 结尾的最大的子数组的和
            for (int i = mid; i >= left; i--) {
                sum += nums[i];
                if (sum > leftSum) {
                    leftSum = sum;
                }
            }
            sum = 0;
            int rightSum = Integer.MIN_VALUE;
            // 右半边不包含 nums[mid] 元素，最多可以到什么地方
            // 计算以 mid+1 开始的最大的子数组的和
            for (int i = mid + 1; i <= right; i++) {
                sum += nums[i];
                if (sum > rightSum) {
                    rightSum = sum;
                }
            }
            return leftSum + rightSum;
        }

        private int maxSubArraySum(int[] nums, int left, int right) {
            if (left == right) {
                return nums[left];
            }
            int mid = left + (right - left) / 2;
            return max3(maxSubArraySum(nums, left, mid),
                    maxSubArraySum(nums, mid + 1, right),
                    maxCrossingSum(nums, left, mid, right));
        }

        private int max3(int num1, int num2, int num3) {
            return Math.max(num1, Math.max(num2, num3));
        }
    }


    /**
     * dp解法
     */
    class Solution1 {
        public int maxSubArray(int[] nums) {
            int dp = nums[0];
            int ans = dp;
            for (int i = 1; i < nums.length; i++) {
                if (dp < 0) {
                    dp = nums[i];
                } else {
                    dp += nums[i];
                }
                ans = Math.max(ans, dp);
            }
            return ans;
        }
    }
}
