package com.fish.array;

import java.util.Arrays;

/**
 * @author liuqi
 * @date 2023/6/13
 */
public class Q2475 {
    //    暴力解法
    class Solution1 {
        public int unequalTriplets(int[] nums) {
            int result = 0;
            int len = nums.length;
            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    for (int k = j + 1; k < len; k++) {
                        if (nums[i] != nums[j] && nums[j] != nums[k] && nums[k] != nums[i]) {
                            result++;
                        }
                    }
                }
            }
            return result;
        }
    }

    // hashmap
    class Solution {
        public int unequalTriplets(int[] nums) {
            Arrays.sort(nums);
            int res = 0, n = nums.length;
            for (int i = 0, j = 0; i < n; i = j) {
                while (j < n && nums[j] == nums[i]) {
                    j++;
                }
                res += i * (j - i) * (n - j);
            }
            return res;
        }
    }
}
