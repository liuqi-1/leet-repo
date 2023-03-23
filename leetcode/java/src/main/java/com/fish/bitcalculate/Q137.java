package com.fish.bitcalculate;

import org.junit.jupiter.api.Test;

/**
 * @author liuqi
 * @date 2023/10/15
 * <p>
 * https://leetcode.cn/problems/single-number-ii/description/?envType=daily-question&envId=2023-10-15
 */
public class Q137 {
    class Solution {
        public int singleNumber(int[] nums) {
            int ans = 0;
            for (int i = 0; i < 32; ++i) {
                int total = 0;
                for (int num : nums) {
                    total += ((num >> i) & 1);
                }
                if (total % 3 != 0) {
                    ans |= (1 << i);
                }
            }
            return ans;
        }
    }

    // 作者：力扣官方题解
    // 链接：https://leetcode.cn/problems/single-number-ii/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    @Test
    public void test() {
        new Solution().singleNumber(new int[]{2, 2, 3, 2});
    }
}
