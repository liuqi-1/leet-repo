package com.fish.tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author liuqi
 * @date 2023/6/21
 *
 * https://leetcode.cn/problems/fHi6rV/
 */
public class LCP41 {
    class Solution {
        private final int[][] direction = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        private int m = 0, n = 0;

        public int flipChess(String[] chessboard) {
            int result = 0;
            m = chessboard.length;
            n = chessboard[0].length();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 挨个遍历放黑子，找到最大翻转值
                    int maxNum = computeMaxFlipNum(i, j, chessboard);
                    result = Math.max(result, maxNum);
                }
            }
            return result;
        }

        private int computeMaxFlipNum(int x, int y, String[] chessboard) {
            if (chessboard[x].charAt(y) != '.') {
                return 0;
            }
            char[][] board = new char[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = chessboard[i].charAt(j);
                }
            }
            return dfs(x, y, board);
        }

        private int dfs(int x, int y, char[][] board) {
            int cnt = 0;
            board[x][y] = 'X';
            // 朝不同方向扩散
            for (int i = 0; i < 8; i++) {
                int endX = x, endY = y;
                int idxX = x + direction[i][0], idxY = y + direction[i][1];
                while (idxX >= 0 && idxX < m && idxY >= 0 && idxY < n && board[idxX][idxY] != '.') {
                    if (board[idxX][idxY] == 'X') {
                        endX = idxX;
                        endY = idxY;
                    }
                    idxX += direction[i][0];
                    idxY += direction[i][1];
                }
                ArrayList<int[]> points = new ArrayList<>();
                for (idxX = x, idxY = y; idxX != endX || idxY != endY; ) {
                    if (board[idxX][idxY] == 'O') {
                        points.add(new int[]{idxX, idxY});
                        board[idxX][idxY] = 'X';
                        cnt++;
                    }
                    idxX += direction[i][0];
                    idxY += direction[i][1];
                }
                for (int[] p : points) {
                    cnt += dfs(p[0], p[1], board);
                }
            }
            return cnt;
        }
    }

    @Test
    public void testR() {
        int result = new Solution().flipChess(new String[]{".O.....", ".O.....", "XOO.OOX", ".OO.OO.", ".XO.OX.", "..X.X.."});
        System.out.println(result);
    }

    /**
     * Arrays.copyOf()对二维数组进行浅拷贝，修改新数组的元素会影响旧数组的值
     */
    @Test
    public void test() {
        int[][] nums = new int[][]{{1, 2}, {3, 4}};
        int[][] nums1 = Arrays.copyOf(nums, nums.length);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(nums1));
        System.out.println("---------------");
        nums1[0][0] = 1000;
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(nums1));
    }
}