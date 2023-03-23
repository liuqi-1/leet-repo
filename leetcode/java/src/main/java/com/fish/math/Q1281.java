package com.fish.math;

/**
 * @author liuqi
 * @date 2023/8/9
 * <p>
 * https://leetcode.cn/problems/subtract-the-product-and-sum-of-digits-of-an-integer/description/
 */
public class Q1281 {
    class Solution {
        public int subtractProductAndSum(int n) {
            int sum = 0, multi = 1;
            while (n != 0) {
                int digit = n % 10;
                sum += digit;
                multi *= digit;
                n /= 10;
            }
            return multi - sum;
        }
    }
}
