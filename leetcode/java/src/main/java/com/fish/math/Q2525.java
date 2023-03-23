package com.fish.math;

/**
 * @author liuqi
 * @date 2023/10/20
 * https://leetcode.cn/problems/categorize-box-according-to-criteria/?envType=daily-question&envId=2023-10-20
 */
public class Q2525 {
    class Solution {
        public int max = 10000;

        public String categorizeBox(int length, int width, int height, int mass) {
            int result = 0;
            if (length >= max || width >= max || height >= max || length * width * (long) height >= 1e9) {
                result += 1; // Bulky
            }
            if (mass >= 100) {
                result += 2; // heavy
            }
            if (result == 0) {
                return "Neither";
            }
            if (result == 1) {
                return "Bulky";
            }
            if (result == 2) {
                return "Heavy";
            }
            return "Both";
        }
    }
}
