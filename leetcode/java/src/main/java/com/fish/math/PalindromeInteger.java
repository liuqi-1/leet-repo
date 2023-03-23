package com.fish.math;

import org.junit.jupiter.api.Test;

/**
 * @author liuqi
 * @date 2023/5/30
 * <p>
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * <p>
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 */
public class PalindromeInteger {
        public class Solution {
            public boolean isPalindrome(int x) {
                if (x < 0) {
                    return false;
                }
                int temp = x;
                int cnt = 0;
                while (x != 0) {
                    cnt = cnt * 10 + x % 10;
                    x /= 10;
                }
                return cnt == temp;
            }
        }

    @Test
    public void test() {
        new Solution().isPalindrome(121);
    }
}
