package com.fish.map;

/**
 * @author liuqi
 * @date 2023/8/31
 * <p>
 * https://leetcode.cn/problems/minimum-degree-of-a-connected-trio-in-a-graph/
 */
public class Q1761 {
    /**
     * 暴力解法，能过是吧，哈哈哈哈哈
     */
    class Solution1 {
        public int minTrioDegree(int n, int[][] edges) {
            int[][] graph = new int[n + 1][n + 1];
            int[] cnt = new int[n + 1];
            for (int i = 0; i < edges.length; i++) {
                int a = edges[i][0];
                int b = edges[i][1];
                graph[a][b] = 1;
                graph[b][a] = 1;
                cnt[a]++;
                cnt[b]++;
            }
            int result = Integer.MAX_VALUE;
            for (int a = 1; a <= n; a++) {
                if (cnt[a] < 2) {
                    continue;
                }
                for (int b = a + 1; b <= n; b++) {
                    if (cnt[b] < 2 || graph[a][b] != 1) {
                        continue;
                    }
                    for (int c = b + 1; c <= n; c++) {
                        if (cnt[c] < 2 || graph[a][c] != 1 || graph[b][c] != 1) {
                            continue;
                        }
                        int edgeCount = cnt[a] + cnt[b] + cnt[c] - 6;
                        result = Math.min(edgeCount, result);
                    }
                }
            }
            return result == Integer.MAX_VALUE ? -1 : result;
        }
    }
}
