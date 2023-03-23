package com.fish.math;

import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class Q2917 {


    class Solution {
        public int findKOr(int[] nums, int k) {
            int ans = 0;
            for (int i = 0; i < 31; ++i) {
                int cnt = 0;
                for (int num : nums) {
                    if (((num >> i) & 1) != 0) {
                        ++cnt;
                    }
                }
                if (cnt >= k) {
                    ans |= 1 << i;
                }
            }
            return ans;
        }
    }


    class Solution1 {
        public int findKOr(int[] nums, int k) {
            // 处理k等于1的特殊情况
            if (k == 1) {
                int result = nums[0];
                for (int num : nums) {
                    result = result | num;
                }
                return result;
            }
            // 处理k=nums.length的特殊情况
            if (k == nums.length) {
                int result = nums[0];
                for (int num : nums) {
                    result = result & num;
                }
                return result;
            }
            // 处理其他情况
            int[] pows = new int[31];
            for (int i = 0; i < 31; i++) {
                pows[i] = (int) Math.pow(2, i);
            }
            int result = 0;
            int max = Arrays.stream(nums).max().getAsInt();
            int i = 0;
            while (Math.pow(2, i) <= max) {
                int cnt = 0;
                for (int num : nums) {
                    if ((pows[i] & num) == pows[i]) {
                        cnt++;
                    }
                }
                if (cnt >= k) {
                    result += pows[i];
                }
                i++;
            }
            return result;
        }
    }

//    @Test
//    public void test() {
//        new Solution().findKOr(new int[]{7, 12, 9, 8, 9, 15}, 4);
//    }
}
