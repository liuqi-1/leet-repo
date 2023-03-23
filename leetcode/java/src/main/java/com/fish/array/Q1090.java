package com.fish.array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author liuqi
 * @date 2023/5/23
 * <p>
 * https://leetcode.cn/problems/largest-values-from-labels/
 * <p>
 * 我们有一个 n 项的集合。给出两个整数数组 values 和 labels ，第 i 个元素的值和标签分别是 values[i] 和 labels[i]。
 * 还会给出两个整数 numWanted 和 useLimit 。
 * 从 n 个元素中选择一个子集 s :
 *      - 子集 s 的大小 小于或等于 numWanted 。
 *      - s 中 最多 有相同标签的 useLimit 项。
 * 一个子集的 分数 是该子集的值之和。
 * 返回子集 s 的最大 分数 。
 */
public class Q1090 {
    class Solution {
        public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
            int[][] arr = new int[values.length][2];
            for (int i = 0; i < values.length; i++) {
                arr[i][0] = values[i];
                arr[i][1] = labels[i];
            }
            Arrays.sort(arr, (int[] a1, int[] a2) -> a2[0] - a1[0]);
            int result = 0;
            int numCnt = 0;
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int[] ints : arr) {
                if (numCnt >= numWanted) {
                    break;
                }
                int cnt = map.getOrDefault(ints[1], 0);
                if (cnt < useLimit) {
                    result += ints[0];
                    map.put(ints[1], ++cnt);
                    numCnt++;
                }
            }
            return result;
        }
    }
}
