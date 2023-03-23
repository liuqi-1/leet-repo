package com.fish.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuqi
 * @date 2023/6/6
 * <p>
 * 给你一个下标从 0 开始、大小为 n x n 的整数矩阵 grid ，返回满足 Ri 行和 Cj 列相等的行列对 (Ri, Cj) 的数目。
 * <p>
 * 如果行和列以相同的顺序包含相同的元素（即相等的数组），则认为二者是相等的。
 */
public class Q2352 {
    class Solution {
        public int equalPairs(int[][] grid) {
            int n = grid.length;
            Map<String, Integer> map = new HashMap<>(n);
            for (int i = 0; i < n; i++) {
                StringBuilder s = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    s.append(grid[i][j]);
                    s.append(';');
                }
                map.put(s.toString(), 1 + map.getOrDefault(s.toString(), 0));
            }
            int result = 0;
            for (int i = 0; i < n; i++) {
                StringBuilder s = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    s.append(grid[j][i]);
                    s.append(";");
                }
                result += map.getOrDefault(s.toString(), 0);
            }
            return result;
        }
    }
}
