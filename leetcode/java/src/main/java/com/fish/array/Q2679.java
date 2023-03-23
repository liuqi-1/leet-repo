package com.fish.array;

import java.util.Arrays;

/**
 * @author liuqi
 * @date 2023/7/4
 *
 * https://leetcode.cn/problems/sum-in-a-matrix/submissions/444059988/
 */
public class Q2679 {
    class Solution {
        public int matrixSum(int[][] nums) {
            for (int[] num : nums) {
                Arrays.sort(num);
            }
            int result = 0;
            for (int i = nums[0].length - 1; i >= 0; i--) {
                int maxNum = Integer.MIN_VALUE;
                for (int j = 0; j < nums.length; j++) {
                    maxNum = Math.max(maxNum, nums[j][i]);
                }
                result += maxNum;
            }
            return result;
        }
    }
}
