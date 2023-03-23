package com.fish.map;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author liuqi
 * @date 2023/12/5
 */
public class Q2477 {
    class Solution {
        /**
         * @param root
         * @param map
         * @param seats
         * @return ret[0] -> 到该节点一共需要花费多少油，ret[1] -> 一共有多少人到了该节点
         */
        public long[] dfs(int root, List<List<Integer>> map, double seats, int from) {
            long ans = 0;
            long cnt = 1;
            for (int next : map.get(root)) {
                if (next != from) {
                    long[] ret = dfs(next, map, seats, root);
                    ans += ret[0] + Math.ceil((ret[1] / seats));
                    cnt += ret[1];
                }
            }
            return new long[]{ans, cnt};
        }

        public long minimumFuelCost(int[][] roads, int seats) {
            List<List<Integer>> map = new ArrayList<>();
            for (int i = 0; i <= roads.length; i++) {
                map.add(new ArrayList<>());
            }
            for (int i = 0; i < roads.length; i++) {
                map.get(roads[i][0]).add(roads[i][1]);
                map.get(roads[i][1]).add(roads[i][0]);
            }
            return dfs(0, map, seats, -1)[0];
        }
    }

    @Test
    public void test() {
        int[][] roads = new int[][]{{3, 1}, {3, 2}, {1, 0}, {0, 4}, {0, 5}, {4, 6}};
        long ans = new Solution().minimumFuelCost(roads, 2);
        System.out.println(ans);

    }
}
