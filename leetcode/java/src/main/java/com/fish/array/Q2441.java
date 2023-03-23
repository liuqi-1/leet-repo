package com.fish.array;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liuqi
 * @date 2023/5/13
 */
public class Q2441 {
    class Solution {
        public int findMaxK(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                set.add(nums[i]);
            }
            int result = -1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0 && set.contains(nums[i]) && set.contains(-nums[i])) {
                    result = Math.max(result, nums[i]);
                }
            }
            return result;
        }
    }
}
