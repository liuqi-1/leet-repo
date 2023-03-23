package com.fish.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuqi
 * @date 2023/8/27
 * <p>
 * https://leetcode.cn/problems/merge-intervals/
 */
public class Q56 {
    class Solution {
        public int[][] merge(int[][] intervals) {
            List<int[]> list = new ArrayList<>();
            Arrays.sort(intervals, (a, b) -> {
                if (a[0] != b[0]) {
                    return a[0] - b[0];
                } else {
                    return a[1] - b[1];
                }
            });
            int left = intervals[0][0];
            int right = intervals[0][1];
            for (int[] interval : intervals) {
                if (interval[0] <= right) {
                    right = Math.max(right, interval[1]);
                } else {
                    list.add(new int[]{left, right});
                    left = interval[0];
                    right = interval[1];
                }
            }
            list.add(new int[]{left, right});
            int[][] result = new int[list.size()][2];
            for (int i = 0; i < list.size(); i++) {
                result[i] = list.get(i);
            }
            return result;
        }
    }
}
