package com.fish.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuqi
 * @date 2023/8/4
 * https://leetcode.cn/problems/unique-paths-iii/description/
 */
public class UniquePaths3 {
    // 暴力解法，深搜，从起点开始，如果搜到终点，判断是否经过所有的节点
    class Solution {
        /**
         * 1表示开始方格，2表示结束方格，0表示空格，-1表示障碍
         */
        int startX, startY;// 起点
        int endX, endY;// 终点
        int pathNodeCnt;// 空节点数
        int m, n;// 矩阵大小
        int result; // 结果
        int[][] grid;

        /**
         * 从节点(x,y)开始深搜
         *
         * @param x
         * @param y
         */
        private void dfs(int x, int y, int cnt) {
            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == -1 || grid[x][y] == -1) {
                return;
            }
            if (grid[x][y] == 2) {
                result += (cnt == pathNodeCnt + 1) ? 1 : 0;
                return;
            }
            grid[x][y] = -1;
            cnt++;
            dfs(x - 1, y, cnt);
            dfs(x + 1, y, cnt);
            dfs(x, y + 1, cnt);
            dfs(x, y - 1, cnt);
            grid[x][y] = 0;
        }

        public int uniquePathsIII(int[][] grid) {
            this.grid = grid;
            m = grid.length;
            n = grid[0].length;
            // 遍历矩阵，找到开始节点，终点，所有空节点数
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        startX = i;
                        startY = j;
                    } else if (grid[i][j] == 2) {
                        endX = i;
                        endY = j;
                    } else if (grid[i][j] == 0) {
                        pathNodeCnt++;
                    }
                }
            }
            // 开始深搜
            dfs(startX, startY, 0);
            return result;
        }
    }

    /**
     * 位运算真行啊
     */
    class Solution1 {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

        public int uniquePathsIII(int[][] grid) {
            int r = grid.length, c = grid[0].length;
            int si = 0, sj = 0, st = 0;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (grid[i][j] == 0 || grid[i][j] == 2) {
                        st |= 1 << (i * c + j);
                    } else if (grid[i][j] == 1) {
                        si = i;
                        sj = j;
                    }
                }
            }
            return dp(grid, si, sj, st);
        }

        public int dp(int[][] grid, int i, int j, int st) {
            if (grid[i][j] == 2) {
                return st == 0 ? 1 : 0;
            }
            int r = grid.length, c = grid[0].length;
            int key = ((i * c + j) << (r * c)) + st;
            if (!memo.containsKey(key)) {
                int res = 0;
                for (int[] dir : dirs) {
                    int ni = i + dir[0], nj = j + dir[1];
                    if (ni >= 0 && ni < r && nj >= 0 && nj < c && (st & (1 << (ni * c + nj))) > 0) {
                        res += dp(grid, ni, nj, st ^ (1 << (ni * c + nj)));
                    }
                }
                memo.put(key, res);
            }
            return memo.get(key);
        }
    }

//    作者：力扣官方题解
//    链接：https://leetcode.cn/problems/unique-paths-iii/solutions/2365866/bu-tong-lu-jing-iii-by-leetcode-solution-cndw/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
