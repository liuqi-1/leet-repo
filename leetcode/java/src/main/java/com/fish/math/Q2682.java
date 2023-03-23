package com.fish.math;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liuqi
 * @date 2023/8/16
 * <p>
 * https://leetcode.cn/problems/find-the-losers-of-the-circular-game/
 */
public class Q2682 {
    class Solution {
        public int[] circularGameLosers(int n, int k) {
            Set<Integer> set = new HashSet<>();
            int round = 1, now = 0;
            set.add(now);
            while (true) {
                now = (now + round * k) % n;
                if (set.contains(now)) {
                    break;
                }
                set.add(now);
                round++;
            }
            int[] result = new int[n - set.size()];
            int idx = 0;
            for (now = 0; now < n; now++) {
                if (!set.contains(now)) {
                    result[idx++] = now + 1;
                }
            }
            return result;
        }
    }
}
