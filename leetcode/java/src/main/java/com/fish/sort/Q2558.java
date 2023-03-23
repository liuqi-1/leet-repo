package com.fish.sort;

import java.util.PriorityQueue;

/**
 * @author liuqi
 * @date 2023/10/28
 * <p>
 * https://leetcode.cn/problems/take-gifts-from-the-richest-pile/?envType=daily-question&envId=2023-10-28
 * <p>
 * 优先队列
 */
public class Q2558 {
    class Solution {
        public long pickGifts(int[] gifts, int k) {
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> b - a);
            for (int gift : gifts) {
                priorityQueue.add(gift);
            }
            while (k-- > 0) {
                int max = priorityQueue.poll();
                priorityQueue.add((int) Math.pow(max, 0.5));
            }
            long sum = 0;
            while (!priorityQueue.isEmpty()) {
                sum += priorityQueue.poll();
            }
            return sum;
        }
    }
}
