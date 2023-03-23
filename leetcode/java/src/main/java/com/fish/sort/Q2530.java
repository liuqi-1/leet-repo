package com.fish.sort;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author liuqi
 * @date 2023/10/18
 */
public class Q2530 {
    class Solution {
        private int ceil(int num) {
            if (num % 3 == 0) {
                return num / 3;
            }
            return num / 3 + 1;
        }

        public long maxKelements(int[] nums, int k) {
            Queue<Integer> q = new PriorityQueue<Integer>((a, b) -> b - a);
            long result = 0;
            for (int num : nums) {
                q.add(num);
            }
            for (int i = 0; i < k; i++) {
                int max = q.poll();
                q.add(ceil(max));
                result += max;
            }
            return result;
        }
    }
}
