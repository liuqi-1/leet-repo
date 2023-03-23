package com.fish.array;

import java.util.Arrays;

/**
 * 给你 n 个二维平面上的点 points ，其中 points[i] = [xi, yi] ，请你返回两点之间内部不包含任何点的 最宽垂直区域 的宽度。
 * 垂直区域 的定义是固定宽度，而 y 轴上无限延伸的一块区域（也就是高度为无穷大）。 最宽垂直区域 为宽度最大的一个垂直区域。
 * 请注意，垂直区域 边上 的点 不在 区域内。
 *
 * @author liuqi
 * @date 2023/3/30
 */
public class Q1637 {
    class Solution {
        public int maxWidthOfVerticalArea(int[][] points) {
            Arrays.sort(points, (p1, p2) -> {
                return p1[0] - p2[0];
            });
            int maxLen = Integer.MIN_VALUE;
            for (int i = 0; i < points.length - 1; i++) {
                int len = points[i + 1][0] - points[i][0];
                maxLen = Math.max(maxLen, len);
            }
            return maxLen;
        }
    }
}
