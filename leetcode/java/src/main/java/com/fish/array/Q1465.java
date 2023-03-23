package com.fish.array;

import java.util.Arrays;

/**
 * @author liuqi
 * @date 2023/10/27
 * <p>
 * https://leetcode.cn/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/?envType=daily-question&envId=2023-10-27
 */
public class Q1465 {
    class Solution {
        private int MOD = (int) (1e9 + 7);

        public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
            Arrays.sort(horizontalCuts);
            Arrays.sort(verticalCuts);
            int lastH = 0;
            int lastV = 0;
            int maxH = 0;
            int maxV = 0;
            for (int i = 0; i < horizontalCuts.length; i++) {
                maxH = Math.max(maxH, horizontalCuts[i] - lastH);
                lastH = horizontalCuts[i];
            }
            maxH = Math.max(maxH, h - lastH);
            for (int i = 0; i < verticalCuts.length; i++) {
                maxV = Math.max(maxV, verticalCuts[i] - lastV);
                lastV = verticalCuts[i];
            }
            maxV = Math.max(maxV, w - lastV);
            return (int) ((maxH * (long) maxV) % MOD);
        }
    }
}
