package com.fish.map;

import java.util.*;

/**
 * @author liuqi
 * @date 2023/9/12
 */
public class Q1462 {
    class Solution1 {
        public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
            Set<Integer>[] postSets = new Set[numCourses];
            for (int i = 0; i < numCourses; i++) {
                postSets[i] = new HashSet<>();
            }
            int[] degree = new int[numCourses];
            for (int[] prerequisite : prerequisites) {
                postSets[prerequisite[0]].add(prerequisite[1]);
                degree[prerequisite[1]]++;
            }
            Queue<Integer> q = new LinkedList();
            for (int i = 0; i < numCourses; i++) {
                if (degree[i] == 0) {
                    q.add(i);
                }
            }
            boolean[][] isPre = new boolean[numCourses][numCourses];
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int next : postSets[cur]) {
                    isPre[cur][next] = true;
                    for (int i = 0; i < numCourses; i++) {
                        isPre[i][next] = isPre[i][next] || isPre[i][cur];
                    }
                    degree[next]--;
                    if (degree[next] == 0) {
                        q.add(next);
                    }
                }
            }
            List<Boolean> result = new ArrayList<>();
            for (int[] query : queries) {
                result.add(isPre[query[0]][query[1]]);
            }
            return result;
        }
    }

    class Solution {
        public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
            int[][] postResult = new int[numCourses][numCourses];
            List<Set<Integer>> postList = new ArrayList<>(numCourses);
            for (int i = 0; i < numCourses; i++) {
                postList.add(new HashSet<>());
            }
            for (int[] prerequisite : prerequisites) {
                int post = prerequisite[1];
                int pre = prerequisite[0];
                postList.get(pre).add(post);
            }
            Queue<Integer> q = new LinkedList<>();
            for (int pre = 0; pre < numCourses; pre++) {
                q.addAll(postList.get(pre));
                while (!q.isEmpty()) {
                    int post = q.poll();
                    postResult[pre][post] = 1;
                    q.addAll(postList.get(post));
                }
            }
            List<Boolean> result = new ArrayList<>(queries.length);
            for (int i = 0; i < queries.length; i++) {
                result.add(postResult[queries[i][0]][queries[i][1]] == 1);
            }
            return result;
        }
    }
}
