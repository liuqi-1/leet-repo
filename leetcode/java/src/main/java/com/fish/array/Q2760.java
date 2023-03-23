package com.fish.array;

/**
 * @author liuqi
 * @date 2023/11/16
 * <p>
 * https://leetcode.cn/problems/longest-even-odd-subarray-with-threshold/?envType=daily-question&envId=2023-11-16
 */
public class Q2760 {
    class Solution {
        public int longestAlternatingSubarray(int[] nums, int threshold) {
            int maxLen = 0;
            int l = 0;
            int r = 0;
            while (true) {
                while (l < nums.length && (nums[l] > threshold || nums[l] % 2 == 1)) {
                    l++;
                }
                if (l >= nums.length) {
                    break;
                }
                r = l + 1;
                while (r < nums.length && nums[r] <= threshold && (nums[r] + nums[r - 1]) % 2 == 1) {
                    r++;
                }
                maxLen = Math.max(r - l, maxLen);
                l = r;
            }
            return maxLen;
        }
    }
}
