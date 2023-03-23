package com.fish.array;

import java.util.List;

/**
 * @author liuqi
 * @date 2023/11/24
 * <p>
 * https://leetcode.cn/problems/count-pairs-whose-sum-is-less-than-target/?envType=daily-question&envId=2023-11-24
 */
public class Q2924 {
    class Solution {
        public int countPairs(List<Integer> nums, int target) {
            int len = nums.size();
            int cnt = 0;
            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    if (nums.get(i) + nums.get(j) < target) {
                        cnt++;
                    }
                }
            }
            return cnt;
        }
    }
}
