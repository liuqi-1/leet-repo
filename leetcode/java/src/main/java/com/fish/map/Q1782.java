package com.fish.map;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author liuqi
 * @date 2023/8/23
 * https://leetcode.cn/problems/count-pairs-of-nodes/
 */
public class Q1782 {
    @Test
    public void test() {
        new Solution1().countPairs(5,
                new int[][]{{1, 5}, {1, 5}, {3, 4}, {2, 5}, {1, 3}, {5, 1}, {2, 3}, {2, 5}},
                new int[]{1, 2, 3, 4, 5});
    }


    class Solution {
        public int[] countPairs(int n, int[][] edges, int[] queries) {
            int[] degree = new int[n];
            Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
            for (int[] edge : edges) {
                int x = edge[0] - 1, y = edge[1] - 1;
                if (x > y) {
                    int temp = x;
                    x = y;
                    y = temp;
                }
                degree[x]++;
                degree[y]++;
                cnt.put(x * n + y, cnt.getOrDefault(x * n + y, 0) + 1);
            }

            int[] arr = Arrays.copyOf(degree, n);
            int[] ans = new int[queries.length];
            Arrays.sort(arr);
            for (int k = 0; k < queries.length; k++) {
                int bound = queries[k], total = 0;
                for (int i = 0; i < n; i++) {
                    int j = binarySearch(arr, i + 1, n - 1, bound - arr[i]);
                    total += n - j;
                }
                for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                    int val = entry.getKey(), freq = entry.getValue();
                    int x = val / n, y = val % n;
                    if (degree[x] + degree[y] > bound && degree[x] + degree[y] - freq <= bound) {
                        total--;
                    }
                }
                ans[k] = total;
            }

            return ans;
        }

        public int binarySearch(int[] arr, int left, int right, int target) {
            int ans = right + 1;
            while (left <= right) {
                int mid = (left + right) >> 1;
                if (arr[mid] <= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                    ans = mid;
                }
            }
            return ans;
        }
    }
//
//    作者：力扣官方题解
//    链接：https://leetcode.cn/problems/count-pairs-of-nodes/solutions/2398562/tong-ji-dian-dui-de-shu-mu-by-leetcode-s-yxlv/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    class Solution1 {
        public int[] countPairs(int n, int[][] edges, int[] queries) {
            int[][] edgeCnt = new int[n + 1][n + 1]; // edgeCnt[a][b]，顶点a和b之间的边的数目
            Map<Integer, Integer> cntMap = new HashMap<>();// key:顶点id, value：边的数目
            for (int[] edge : edges) {
                edgeCnt[edge[0]][edge[1]]++;
                edgeCnt[edge[1]][edge[0]]++;
                cntMap.put(edge[0], cntMap.getOrDefault(edge[0], 0) + 1);
                cntMap.put(edge[1], cntMap.getOrDefault(edge[1], 0) + 1);
            }
            List<Integer> resultList = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    int cnt = cntMap.getOrDefault(i, 0) + cntMap.getOrDefault(j, 0) - edgeCnt[i][j];
                    if (cnt != 0) {
                        resultList.add(cnt);
                    }
                }
            }
            Collections.sort(resultList);
            for (int i = 0; i < queries.length; i++) {
                int left = 0;
                int right = resultList.size() - 1;
                int mid = 0;
                int query = queries[i];
                while (left <= right) {
                    mid = (left + right) / 2;
                    if (resultList.get(mid) > query) {
                        right = mid - 1;
                    } else if (resultList.get(mid) < query) {
                        left = mid + 1;
                    } else {
                        break;
                    }
                }
                queries[i] = resultList.size() - 1 - mid;
                if (query < resultList.get(mid)) {
                    left = mid - 1;
                    while (left >= 0 && resultList.get(left).equals(resultList.get(mid))) {
                        queries[i]++;
                        left--;
                    }
                    queries[i]++;
                } else {
                    int idx = mid + 1;
                    while (idx < resultList.size() && resultList.get(idx).equals(resultList.get(mid))) {
                        queries[i]--;
                        idx++;
                    }
                }
            }
            return queries;
        }
    }
}
