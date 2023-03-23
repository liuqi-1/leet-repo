package com.fish.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuqi
 * @date 2023/6/22
 * <p>
 * https://leetcode.cn/problems/pond-sizes-lcci/
 */
public class QPondSizesLcci {
    class Solution {
        int m = 0;
        int n = 0;
        private final int[][] direction = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        private int dfs(int x, int y, int[][] land) {
            if (x >= m || y >= n || x < 0 || y < 0 || land[x][y] != 0) {
                return 0;
            }
            int cnt = 1;
            land[x][y] = 1;
            for (int i = 0; i < 8; i++) {
                int x_ = x + direction[i][0];
                int y_ = y + direction[i][1];
                cnt += dfs(x_, y_, land);
            }
            return cnt;
        }

        public int[] pondSizes(int[][] land) {
            List<Integer> result = new ArrayList<>();
            m = land.length;
            n = land[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (land[i][j] == 0) {
                        int cnt = dfs(i, j, land);
                        if (cnt != 0) {
                            result.add(cnt);
                        }
                    }
                }
            }
            int[] ret = new int[result.size()];
            for (int i = 0; i < result.size(); i++) {
                ret[i] = result.get(i);
            }
            Arrays.sort(ret);
            return ret;
        }
    }
}
