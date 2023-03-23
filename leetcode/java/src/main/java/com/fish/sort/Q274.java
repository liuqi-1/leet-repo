package com.fish.sort;

import java.util.Arrays;

/**
 * @author liuqi
 * @date 2023/10/29
 */
public class Q274 {
    /**
     * 思路对了，但是没完全对，写对花了很多时间
     */
    class Solution {
        public int hIndex(int[] citations) {
            Arrays.sort(citations);
            for (int i = 0; i < citations.length; i++) {
                int h = citations.length - i;
                if (citations[i] >= h) {
                    return h;
                }
            }
            return 0;
        }
    }
}
