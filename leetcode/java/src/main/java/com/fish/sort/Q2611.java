package com.fish.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author liuqi
 * @date 2023/6/7
 */
public class Q2611 {

    class Solution {
        public int miceAndCheese(int[] reward1, int[] reward2, int k) {
            int ans = 0;
            int n = reward1.length;
            int[] diffs = new int[n];
            for (int i = 0; i < n; i++) {
                ans += reward2[i];
                diffs[i] = reward1[i] - reward2[i];
            }
            Arrays.sort(diffs);
            for (int i = 1; i <= k; i++) {
                ans += diffs[n - i];
            }
            return ans;
        }
    }
//    作者：力扣官方题解
//    链接：https://leetcode.cn/problems/mice-and-cheese/solutions/2292688/lao-shu-he-nai-luo-by-leetcode-solution-6ia1/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    /**
     * shit一样的代码，贪心贪的什么呀，我是菜鸡
     */
    class Solution1 {
        public int miceAndCheese(int[] reward1, int[] reward2, int k) {
            int n = reward1.length;
            int cnt1 = 0, cnt2 = 0;
            int result = 0;
            PriorityQueue<int[]> queue = new PriorityQueue<>(n, (o1, o2) -> {
                int min1 = Math.min(o1[0], o1[1]);
                int min2 = Math.min(o2[0], o2[1]);
                if (min2 != min1) {
                    return Math.min(o1[0], o1[1]) - Math.min(o2[0], o2[1]);
                } else {
                    return Math.max(o2[0], o2[1]) - Math.max(o1[0], o1[1]);
                }
            });
            for (int i = 0; i < n; i++) {
                queue.add(new int[]{reward1[i], reward2[i]});
            }
            while (cnt1 + cnt2 < n) {
                int[] p = queue.poll();
                if (cnt1 < k && (p[0] >= p[1] || cnt2 >= n - k)) {
                    result += p[0];
                    cnt1++;
                } else {
                    result += p[1];
                    cnt2++;
                }
            }
            return result;
        }
    }

    @Test
    public void test() {
        int result = new Solution().miceAndCheese(new int[]{1}, new int[]{6}, 1);
        System.out.println(result);
    }
}
