package com.fish.math;

/**
 * @author liuqi
 * @date 2023/11/2
 */
public class Q2103 {
    class Solution {
        public int countPoints(String rings) {
            int[][] cnt = new int[10][3];
            int i = 0;
            while (i < rings.length()) {
                char color = rings.charAt(i++);
                int index = rings.charAt(i++) - '0';
                if (color == 'R') {
                    cnt[index][0] = 1;
                } else if (color == 'G') {
                    cnt[index][1] = 1;
                } else {
                    cnt[index][2] = 1;
                }
            }
            int result = 0;
            for (int[] arr : cnt) {
                if (arr[0] + arr[1] + arr[2] == 3) {
                    result++;
                }
            }
            return result;
        }
    }
}
