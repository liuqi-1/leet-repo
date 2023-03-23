package com.fish.array;

/**
 * @author liuqi
 * @date 2023/6/23
 * <p>
 * https://leetcode.cn/problems/maximum-value-of-a-string-in-an-array/
 */
public class Q2496 {
    class Solution {
        public int maximumValue(String[] strs) {
            int maxValue = Integer.MIN_VALUE;
            for (String str : strs) {
                try {
                    maxValue = Math.max(maxValue, Integer.parseInt(str));
                } catch (Exception e) {
                    maxValue = Math.max(maxValue, str.length());
                }
            }
            return maxValue;
        }
    }
}
