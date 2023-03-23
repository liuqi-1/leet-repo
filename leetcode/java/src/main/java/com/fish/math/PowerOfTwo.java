package com.fish.math;

import org.junit.jupiter.api.Test;

/**
 * @author liuqi
 * @date 2023/5/31
 * <p>
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 如果存在一个整数 x 使得n == 2x ，则认为 n 是 2 的幂次方。
 * <p>
 * 作者：LeetCode
 * 链接：https://leetcode.cn/leetbook/read/tencent/x5yjhd/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class PowerOfTwo {
    class Solution {
        public boolean isPowerOfTwo(int n) {
            if (n == 0) {
                return false;
            }
            while (n != 1) {
                if (n % 2 == 0) {
                    n /= 2;
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    @Test
    public void test() {
        System.out.println(new Solution().isPowerOfTwo(16));
    }
}
