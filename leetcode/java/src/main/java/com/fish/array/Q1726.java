package com.fish.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liuqi
 * @date 2023/10/19
 * <p>
 * https://leetcode.cn/problems/tuple-with-same-product/?envType=daily-question&envId=2023-10-19
 */
public class Q1726 {
    class Solution {
        public int tupleSameProduct(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int left = 0; left < nums.length; left++) {
                for (int right = left + 1; right < nums.length; right++) {
                    map.merge(nums[left] * nums[right], 1, (a, b) -> a + b);
                }
            }
            int result = 0;
            for (int num : map.values()) {
                int a = num * (num - 1) / 2;
                result += a * 8;
            }
            return result;
        }
    }
}
