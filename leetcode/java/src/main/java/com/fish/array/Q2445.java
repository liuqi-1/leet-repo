package com.fish.array;

/**
 * @author liuqi
 * @date 2023/5/29
 */
public class Q2445 {
    class Solution {
        public int averageValue(int[] nums) {
            int sum = 0;
            int cnt = 0;
            for (int num : nums) {
                if (num % 6 == 0) {
                    cnt++;
                    sum += num;
                }
            }
            return cnt == 0 ? 0 : sum / cnt;
        }
    }
}
