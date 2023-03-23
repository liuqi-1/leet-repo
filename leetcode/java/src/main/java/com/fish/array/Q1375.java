package com.fish.array;

/**
 * @author liuqi
 * @date 2023/6/14
 */
public class Q1375 {
    class Solution {
        public int numTimesAllBlue(int[] flips) {
            int result = 0, max = -1;
            for (int i = 0; i < flips.length; i++) {
                max = Math.max(max, flips[i]);
                if (max == i + 1) {
                    result++;
                }
            }
            return result;
        }
    }

}
