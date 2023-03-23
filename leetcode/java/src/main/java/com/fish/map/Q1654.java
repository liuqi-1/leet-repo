package com.fish.map;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author liuqi
 * @date 2023/8/30
 * <p>
 * https://leetcode.cn/problems/minimum-jumps-to-reach-home/description/
 */
public class Q1654 {
    class Solution {
        public int minimumJumps(int[] forbidden, int a, int b, int x) {
            Queue<int[]> queue = new ArrayDeque<int[]>();
            Set<Integer> visited = new HashSet<Integer>();
            queue.offer(new int[]{0, 1, 0});
            visited.add(0);
            int lower = 0, upper = Math.max(Arrays.stream(forbidden).max().getAsInt() + a, x) + b;
            Set<Integer> forbiddenSet = new HashSet<Integer>();
            for (int position : forbidden) {
                forbiddenSet.add(position);
            }
            while (!queue.isEmpty()) {
                int[] arr = queue.poll();
                int position = arr[0], direction = arr[1], step = arr[2];
                if (position == x) {
                    return step;
                }
                int nextPosition = position + a;
                int nextDirection = 1;
                if (lower <= nextPosition && nextPosition <= upper && !visited.contains(nextPosition * nextDirection) && !forbiddenSet.contains(nextPosition)) {
                    visited.add(nextPosition * nextDirection);
                    queue.offer(new int[]{nextPosition, nextDirection, step + 1});
                }
                if (direction == 1) {
                    nextPosition = position - b;
                    nextDirection = -1;
                    if (lower <= nextPosition && nextPosition <= upper && !visited.contains(nextPosition * nextDirection) && !forbiddenSet.contains(nextPosition)) {
                        visited.add(nextPosition * nextDirection);
                        queue.offer(new int[]{nextPosition, nextDirection, step + 1});
                    }
                }
            }
            return -1;
        }
    }

    // 作者：力扣官方题解
    // 链接：https://leetcode.cn/problems/minimum-jumps-to-reach-home/solutions/2414842/dao-jia-de-zui-shao-tiao-yue-ci-shu-by-l-sza1/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    // class Solution {
    //     int a, b, x;
    //     public Set<Integer> forbidden = new HashSet<>();
    //     public Set<Integer> visited = new HashSet<>();
    //
    //     public int help(int pos, int pop) {
    //         if (visited.contains(pos) || forbidden.contains(pos) || pos < 0 || pop > 5000) {
    //             return -1;
    //         }
    //         if (a - b > 0 && pos > x && pos - b != x) {
    //             return -1;
    //         }
    //         if (pos == x) {
    //             return pop;
    //         }
    //         visited.add(pos);
    //         int popCnt2 = -1;
    //         if (pos - b >= 0 && !visited.contains(pos - b) && !forbidden.contains(pos - b)) {
    //             if (pos - b == x) {
    //                 return pop + 1;
    //             }
    //             popCnt2 = help(pos - b + a, pop + 2);
    //         }
    //         int popCnt1 = help(pos + a, pop + 1);
    //         if (popCnt1 == -1) {
    //             return popCnt2;
    //         } else if (popCnt2 == -1) {
    //             return popCnt1;
    //         } else {
    //             return Math.min(popCnt1, popCnt2);
    //         }
    //     }
    //
    //     public int minimumJumps(int[] forbidden, int a, int b, int x) {
    //         this.a = a;
    //         this.b = b;
    //         this.x = x;
    //         for (int i : forbidden) {
    //             this.forbidden.add(i);
    //         }
    //         return help(0, 0);
    //     }
    // }

    @Test
    public void test() {
        int res = new Solution().minimumJumps(new int[]{1998}, 1999, 2000, 2000);
        System.out.println(res);
    }
}
