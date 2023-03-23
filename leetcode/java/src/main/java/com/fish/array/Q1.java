package com.fish.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuqi
 * @date 2023/7/1
 */
public class Q1 {
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(target - nums[i])) {
                    return new int[]{map.get(target - nums[i]), i};
                }
                map.put(nums[i], i);
            }
            return null;
        }
    }
}
