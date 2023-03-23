package com.fish.math;

/**
 * @author liuqi
 * @date 2023/10/26
 */
public class Q2520 {
    class Solution {
        public int countDigits(int num) {
            int cnt = 0;
            int num1 = num;
            while (num != 0) {
                int bit = num % 10;
                num /= 10;
                if (num1 % bit == 0) {
                    cnt++;
                }
            }
            return cnt;
        }
    }
}
