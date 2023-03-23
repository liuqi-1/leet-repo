package com.fish.math;

/**
 * @author liuqi
 * @date 2023/5/30
 * <p>
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * <p>
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 */
public class ReverseInteger {
    class Solution {
        public int reverse(int x) {
            int result = 0;
            while (x != 0) {
                int d = x % 10;
                x /= 10;
                int last = result;
                result = result * 10 + d;
                if (last != result / 10) {
                    return 0;
                }
            }
            return result;
        }
    }
}
