package com.fish.math;

/**
 * @author liuqi
 * @date 2023/6/26
 * <p>
 * https://leetcode.cn/problems/find-the-pivot-integer/
 */
public class Q2485 {
    class Solution {
        public int pivotInteger(int n) {
            int sum = (1 + n) * n / 2;
            int st = 0;
            for (int i = 1; i <= n; i++) {
                st += i;
                if (st * 2 == sum + i) {
                    return i;
                }
            }
            return -1;
        }
    }
}
