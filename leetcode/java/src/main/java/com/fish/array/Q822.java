package com.fish.array;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liuqi
 * @date 2023/8/2
 * https://leetcode.cn/problems/card-flipping-game/solutions/2365854/fan-zhuan-qia-pian-you-xi-by-leetcode-so-acbj/
 */
public class Q822 {

    /**
     * 官方题解
     */
    class Solution{
        public int flipgame(int[] fronts, int[] backs) {
            Set<Integer> same = new HashSet<>();
            int len = fronts.length;
            for (int i = 0; i < len; i++) {
                if (backs[i] == fronts[i]) {
                    same.add(fronts[i]);
                }
            }
            int result = 3000;
            for (int i = 0; i < len; i++) {
                if (!same.contains(backs[i])) {
                    result = Math.min(result, backs[i]);
                }
                if (!same.contains(fronts[i])) {
                    result = Math.min(result, fronts[i]);
                }
            }
            return result < 3000 ? result : 0;
        }
    }


    /**
     * 很蠢的暴力方法
     */
    class Solution1 {
        public int flipgame(int[] fronts, int[] backs) {
            int len = fronts.length;
            int result = 3000;
            for (int i = 0; i < len; i++) {
                int numBack = backs[i];
                int numFront = fronts[i];
                if (numFront == numBack || (numFront >= result && numBack >= result)) {
                    continue;
                }
                int cntBack = 0;
                int cntFront = 0;
                for (int j = 0; j < len; j++) {
                    if (j == i) {
                        continue;
                    }
                    if (fronts[j] != numBack || backs[j] != numBack) {
                        cntBack++;
                    }
                    if (fronts[j] != numFront || backs[j] != numFront) {
                        cntFront++;
                    }
                }
                if (cntBack == len - 1) {
                    result = Math.min(result, numBack);
                }
                if (cntFront == len - 1) {
                    result = Math.min(result, numFront);
                }
            }
            return result < 3000 ? result : 0;
        }
    }
}
