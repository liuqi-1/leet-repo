package com.fish.array;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author liuqi
 * @date 2023/10/11
 * <p>
 * https://leetcode.cn/problems/reward-top-k-students/?envType=daily-question&envId=2023-10-11
 */
public class Q2512 {
    /**
     * 正确代码
     */
    class Solution {
        PriorityQueue<int[]> queue = new PriorityQueue<>((p1, p2) -> {
            if (p1[0] > p2[0]) {
                return 1;
            } else if (p1[0] == p2[0]) {
                return p2[1] - p1[1];
            } else {
                return -1;
            }
        });

        public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
            int n = report.length;
            HashSet<String> positive = new HashSet<>();
            for (String word : positive_feedback) {
                positive.add(word);
            }
            HashSet<String> negative = new HashSet<>();
            for (String word : negative_feedback) {
                negative.add(word);
            }
            for (int i = 0; i < n; i++) {
                int score = 0;
                String[] words = report[i].split(" ");
                for (String word : words) {
                    if (positive.contains(word)) {
                        score += 3;
                    } else if (negative.contains(word)) {
                        score -= 1;
                    }
                }
                System.out.println(student_id[i] + "-" + score);
                queue.add(new int[]{score, student_id[i]});
                if (queue.size() > k) {
                    queue.poll();
                }
            }
            LinkedList<Integer> result = new LinkedList<>();
            while (!queue.isEmpty()) {
                result.addFirst(queue.poll()[1]);
            }
            return result;
        }
    }


    class Solution1 {
        PriorityQueue<int[]> queue = new PriorityQueue<>((p1, p2) -> {
            if (p1[0] > p2[0]) {
                return 1;
            } else if (p1[0] == p2[0]) {
                return p2[1] - p1[1];
            } else {
                return -1;
            }
        });

        public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
            int n = report.length;
            for (int i = 0; i < n; i++) {
                int score = 0;
                for (String word : positive_feedback) {
                    // 同一个单词可能出现好几次
                    if (report[i].contains(word)) {
                        score += 3;
                    }
                }
                for (String word : negative_feedback) {
                    if (report[i].contains(word)) {
                        score -= 1;
                    }
                }
                queue.add(new int[]{score, student_id[i]});
                if (queue.size() > k) {
                    queue.poll();
                }
            }
            LinkedList<Integer> result = new LinkedList<>();
            while (!queue.isEmpty()) {
                result.addFirst(queue.poll()[1]);
            }
            return result;
        }
    }

    @Test
    public void test() {
        PriorityQueue<int[]> queue = new PriorityQueue<>((p1, p2) -> {
            if (p1[0] > p2[0]) {
                return 1;
            } else if (p1[0] == p2[0]) {
                return p2[1] - p1[1];
            } else {
                return -1;
            }
        });
        queue.add(new int[]{1, 12});
        queue.add(new int[]{3, 43});
        queue.add(new int[]{4, 124});
        queue.add(new int[]{5, 321});
        queue.add(new int[]{3, 1});
        while (!queue.isEmpty()) {
            System.out.println(Arrays.toString(queue.poll()));
        }
    }
}
