package com.fish.fall2023.bilibili;

import org.junit.jupiter.api.Test;

/**
 * @author liuqi
 * @date 2023/8/29
 */
public class Q1 {
    public class Solution {
        public int DoubleNumber(long n) {
            int result = 0;
            long i = 1;
            while (true) {
                long value = Long.valueOf(i + "" + i);
                if (value <= n) {
                    result++;
                } else {
                    break;
                }
                i++;
            }
            return result;
        }
    }

    @Test
    public void test() {
        System.out.println(new Solution().DoubleNumber(25));
    }
}
