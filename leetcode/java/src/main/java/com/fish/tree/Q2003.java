package com.fish.tree;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liuqi
 * @date 2023/10/31
 */
public class Q2003 {
    class Solution {
        public Set<Integer>[] sets;

        public int[] result;
        public int[] nums;

        /**
         * @param idx 结点的下标
         */
        public Set<Integer> dfs(int idx) {
            Set<Integer> set = new HashSet<>();
            set.add(nums[idx]);
            int min = 1;
            for (int child : sets[idx]) {
                Set<Integer> setChild = dfs(child);
                if (setChild.size() > set.size()) {
                    Set temp = set;
                    set = setChild;
                    setChild = temp;
                }
                set.addAll(setChild);
                min = Math.max(min, result[child]);
            }
            while (set.contains(min)) {
                min++;
            }
            result[idx] = min;
            return set;
        }

        public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
            int n = parents.length;
            sets = new HashSet[n];
            for (int i = 0; i < n; i++) {
                sets[i] = new HashSet<>();
            }
            result = new int[n];
            this.nums = nums;
            // 构建树结构
            for (int i = 1; i < n; i++) {
                int parent = parents[i];
                sets[parent].add(i);
            }
            // 深度遍历
            dfs(0);
            return result;
        }
    }
}
