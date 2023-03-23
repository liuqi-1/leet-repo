package com.fish.dp;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: liuqi
 * @date: 2024/3/6
 */
public class Q983 {
    @Test
    public void test() {
//        new Solution().mincostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15});
    }


    /**
     * 官方题解
     */
    class Solution {
        int[] days, costs;
        Integer[] memo;
        int[] durations = new int[]{1, 7, 30};

        public int mincostTickets(int[] days, int[] costs) {
            this.days = days;
            this.costs = costs;
            memo = new Integer[days.length]; // 优化了空间复杂度
            return dp(0); // 从dp(0)开始计算，避免了不必要的计算
        }

        public int dp(int i) {
            if (i >= days.length) {
                return 0;
            }
            if (memo[i] != null) {
                return memo[i];
            }
            memo[i] = Integer.MAX_VALUE;
            int j = i;
            for (int k = 0; k < 3; ++k) {
                while (j < days.length && days[j] < days[i] + durations[k]) {
                    j++;
                }
                memo[i] = Math.min(memo[i], dp(j) + costs[k]);
            }
            return memo[i];
        }
    }


    /**
     * 优化了一部分，只遍历了days数组
     */
    class Solution2 {
        private int getM(int[] memo, int[] days, int i, int diff) {
            int day = days[i];
            while (i + 1 < days.length) {
                if (days[i + 1] - day >= diff) {
                    return memo[days[i + 1]];
                }
                i++;
            }
            return 0;
        }

        public int mincostTickets(int[] days, int[] costs) {
            int[] memo = new int[366];
            for (int i = days.length - 1; i >= 0; i--) {
                int day = days[i];
                memo[day] = costs[0] + getM(memo, days, i, 1);
                memo[day] = Math.min(memo[day], costs[1] + getM(memo, days, i, 7));
                memo[day] = Math.min(memo[day], costs[2] + getM(memo, days, i, 30));
            }
            return memo[days[0]];
        }
    }

    class Solution1 {
        private int getM(int[] dp, int day) {
            return day <= 365 ? dp[day] : 0;
        }

        public int mincostTickets(int[] days, int[] costs) {
            Set<Integer> set = new HashSet<>();
            for (int day : days) {
                set.add(day);
            }
            int[] dp = new int[366];
            for (int i = 365; i >= 1; i--) {
                if (!set.contains(i)) {
                    dp[i] = getM(dp, i + 1);
                    continue;
                }
                dp[i] = costs[0] + getM(dp, i + 1);
                dp[i] = Math.min(dp[i], costs[1] + getM(dp, i + 7));
                dp[i] = Math.min(dp[i], costs[2] + getM(dp, i + 30));
            }
            return dp[1];
        }
    }
}
