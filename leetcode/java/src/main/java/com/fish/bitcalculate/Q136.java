package com.fish.bitcalculate;

/**
 * @author liuqi
 * @date 2023/10/14
 *
 * https://leetcode.cn/problems/single-number/?envType=daily-question&envId=2023-10-14
 */
public class Q136 {
    /**
     * 异或，想不出来
     */
    class Solution {
        public int singleNumber(int[] nums) {
            int result = 0;
            for (int num : nums) {
                result = result ^ num;
            }
            return result;
        }
    }
}
