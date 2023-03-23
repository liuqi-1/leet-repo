package com.fish.bitcalculate;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liuqi
 * @date 2023/10/16
 *
 * https://leetcode.cn/problems/single-number-iii/description/?envType=daily-question&envId=2023-10-16
 */
public class Q260 {
    /**
     * 怎么又没想到
     */
    class Solution {
        public int[] singleNumber(int[] nums) {
            int xorsum = 0;
            for (int num : nums) {
                xorsum ^= num;
            }
            // 防止溢出
            // int lsb = (xorsum == Integer.MIN_VALUE ? xorsum : xorsum & (-xorsum));
            int lsb = xorsum & (-xorsum);// 这种情况就是正数分一堆，负数分一堆
            int type1 = 0, type2 = 0;
            for (int num : nums) {
                if ((num & lsb) != 0) {
                    type1 ^= num;
                } else {
                    type2 ^= num;
                }
            }
            return new int[]{type1, type2};
        }
    }

    // 作者：力扣官方题解
    // 链接：https://leetcode.cn/problems/single-number-iii/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    /**
     * 用集合，能过，但是时间复杂度很高
     */
    class Solution1 {
        public int[] singleNumber(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                if (set.contains(num)) {
                    set.remove(num);
                } else {
                    set.add(num);
                }
            }
            return set.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}
