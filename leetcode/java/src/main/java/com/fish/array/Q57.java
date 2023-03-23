package com.fish.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuqi
 * @date 2023/8/28
 * <p>
 * https://leetcode.cn/problems/insert-interval/
 */
public class Q57 {
    /**
     * 比较抽象，需要仔细一点~ 感觉好难考虑全面
     */
    class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            boolean inserted = false;
            boolean needCheck = false;
            List<int[]> list = new ArrayList<>();
            // 新插入的作为第一个区间
            if (intervals.length != 0 && newInterval[0] <= intervals[0][0]) {
                list.add(newInterval);
                inserted = true;
                if (newInterval[1] >= intervals[0][0]) {
                    needCheck = true;
                }
            }
            for (int[] interval : intervals) {
                if (needCheck) {
                    if (interval[0] <= list.get(list.size() - 1)[1]) {
                        int[] lastInterval = list.get(list.size() - 1);
                        int[] insertedInterval = new int[]{Math.min(interval[0], lastInterval[0]), Math.max(interval[1], lastInterval[1])};
                        list.set(list.size() - 1, insertedInterval);
                    } else {
                        list.add(interval);
                    }
                } else if (!inserted && newInterval[0] <= interval[1]) {
                    if (newInterval[1] >= interval[0]) {
                        int[] insertedInterval = new int[]{Math.min(interval[0], newInterval[0]), Math.max(interval[1], newInterval[1])};
                        list.add(insertedInterval);
                        needCheck = true;
                    } else {
                        list.add(newInterval);
                        list.add(interval);
                    }
                    inserted = true;
                } else {
                    list.add(interval);
                }
            }
            // 遍历完仍未插入
            if (!inserted) {
                list.add(newInterval);
            }
            int[][] result = new int[list.size()][2];
            for (int i = 0; i < list.size(); i++) {
                result[i] = list.get(i);
            }
            return result;
        }
    }
}
