package com.fish.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author liuqi
 * @date 2023/10/22
 */
public class Q1402 {
    class Solution {
        public int maxSatisfaction(int[] satisfaction) {
            Arrays.sort(satisfaction);
            int len = satisfaction.length;
            if (satisfaction[len - 1] <= 0) {
                return 0;
            }
            int sum = satisfaction[len - 1];
            int idx = len - 2;
            while (idx >= 0 && sum + satisfaction[idx] >= 0) {
                sum += satisfaction[idx];
                idx--;
            }
            int result = 0;
            idx++;
            for (int i = idx; i < len; i++) {
                result += satisfaction[i] * (i - idx + 1);
            }
            return result;
        }
    }

    @Test
    public void test() {
        new Solution().maxSatisfaction(new int[]{-1, -8, 0, 5, -7});
    }
}
