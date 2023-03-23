package com.fish.array;

/**
 * @author liuqi
 * @date 2023/9/2
 * <p>
 * https://leetcode.cn/problems/maximum-enemy-forts-that-can-be-captured/?envType=daily-question&envId=2023-09-02
 */
public class Q2511 {
    class Solution {
        public int captureForts(int[] forts) {
            int maxValue = 0;
            for (int i = 0; i < forts.length; ) {
                if (forts[i] == 0) {
                    i++;
                    continue;
                }
                int now = forts[i], cnt = 0;
                i++;
                while (i < forts.length && forts[i] == 0) {
                    cnt++;
                    i++;
                }
                if (i >= forts.length || forts[i] * now != -1) {
                    continue;
                }
                maxValue = Math.max(cnt, maxValue);
            }
            return maxValue;
        }
    }
}
