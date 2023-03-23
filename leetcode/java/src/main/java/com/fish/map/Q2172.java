package com.fish.map;

import java.util.*;

/**
 * @author liuqi
 * @date 2023/11/1
 * <p>
 * https://leetcode.cn/problems/maximum-employees-to-be-invited-to-a-meeting/description/?envType=daily-question&envId=2023-11-01
 */
public class Q2172 {
    class Solution {
        public int maximumInvitations(int[] favorite) {
            int n = favorite.length;
            // 统计入度，便于进行拓扑排序
            int[] indeg = new int[n];
            for (int i = 0; i < n; ++i) {
                ++indeg[favorite[i]];
            }
            boolean[] used = new boolean[n];
            int[] f = new int[n];
            Arrays.fill(f, 1);
            Queue<Integer> queue = new ArrayDeque<Integer>();
            for (int i = 0; i < n; ++i) {
                if (indeg[i] == 0) {
                    queue.offer(i);
                }
            }
            while (!queue.isEmpty()) {
                int u = queue.poll();
                used[u] = true;
                int v = favorite[u];
                // 状态转移
                f[v] = Math.max(f[v], f[u] + 1);
                --indeg[v];
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
            // ring 表示最大的环的大小
            // total 表示所有环大小为 2 的「基环内向树」上的最长的「双向游走」路径之和
            int ring = 0, total = 0;
            for (int i = 0; i < n; ++i) {
                if (!used[i]) {
                    int j = favorite[i];
                    // favorite[favorite[i]] = i 说明环的大小为 2
                    if (favorite[j] == i) {
                        total += f[i] + f[j];
                        used[i] = used[j] = true;
                    }
                    // 否则环的大小至少为 3，我们需要找出环
                    else {
                        int u = i, cnt = 0;
                        while (true) {
                            ++cnt;
                            u = favorite[u];
                            used[u] = true;
                            if (u == i) {
                                break;
                            }
                        }
                        ring = Math.max(ring, cnt);
                    }
                }
            }
            return Math.max(ring, total);
        }
    }

// 作者：力扣官方题解
// 链接：https://leetcode.cn/problems/maximum-employees-to-be-invited-to-a-meeting/
// 来源：力扣（LeetCode）
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    /**
     * 思路是对的，但是写不出来
     */
    class Solution1 {
        int favorite[];
        boolean[] visited;
        Set<Integer> set = new HashSet<>();
        PriorityQueue<Integer> q = new PriorityQueue<>();

        public void dfs(int idx, int last, int cnt) {
            if (visited[idx]) {
                set.add(idx);
                return;
            }
            visited[idx] = true;
            if (favorite[idx] == last) {
                cnt++;
                q.add(cnt);
                if (q.size() > 2) {
                    q.poll();
                }
                return;
            } else {
                dfs(favorite[idx], idx, cnt + 1);
            }
            visited[idx] = false;
        }

        public int cnt(int origin, int now, int len) {
            if (favorite[now] == origin) {
                return len;
            }
            return cnt(origin, favorite[now], len + 1);
        }

        public int maximumInvitations(int[] favorite) {
            this.favorite = favorite;
            visited = new boolean[favorite.length];
            for (int i = 0; i < favorite.length; i++) {
                dfs(i, 0, 1);
                Arrays.fill(visited, false);
            }
            int max = 0;
            while (!q.isEmpty()) {
                System.out.println(max);
                max += q.poll();
            }
            for (int i : set) {
                System.out.println(i);
                max = Math.max(max, cnt(i, i, 1));
            }
            return max;
        }
    }
}
