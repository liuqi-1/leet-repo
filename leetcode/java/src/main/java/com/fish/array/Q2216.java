package com.fish.array;

import org.junit.jupiter.api.Test;

/**
 * @author liuqi
 * @date 2023/11/21
 * <p>
 * https://leetcode.cn/problems/minimum-deletions-to-make-array-beautiful/?envType=daily-question&envId=2023-11-21
 */
public class Q2216 {
    class Solution {
        public int minDeletion(int[] nums) {
            int deleteCnt = 0;
            int idx = 0;
            while (idx < nums.length) {
                int val = nums[idx];
                idx++;
                while (idx < nums.length && nums[idx] == val) {
                    idx++;
                    deleteCnt++;
                }
                idx++;
            }
            if ((nums.length - deleteCnt) % 2 == 1) {
                deleteCnt++;
            }
            return deleteCnt;
        }
    }

    @Test
    public void test() {
        new Solution().minDeletion(new int[]{1, 2, 2, 3, 1, 1, 4, 1, 1, 4, 1});
    }
}
