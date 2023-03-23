package com.fish.dp;

/**
 * @author liuqi
 * @date 2023/10/24
 */
public class Q1155 {
    class Solution {
        private final int MOD = (int) (Math.pow(10, 9) + 7);

        public int numRollsToTarget(int n, int k, int target) {
            int[] cnt = new int[target + 1];
            for (int top = 1; top <= k; top++) {
                if (target == top) {
                    cnt[target]++;
                } else if (top < target) {
                    cnt[top]++;
                }
            }
            for (int i = 1; i < n; i++) {
                int[] newCnt = new int[target + 1];
                for (int total = 1; total < target; total++) {
                    if (cnt[total] == 0) {
                        continue;
                    }
                    for (int top = 1; top <= k; top++) {
                        int newTotal = total + top;
                        if (newTotal <= target) {
                            newCnt[newTotal] += cnt[total];
                            newCnt[newTotal] %= MOD;
                        }
                    }
                }
                cnt = newCnt;
            }
            return cnt[target];
        }
    }
}
