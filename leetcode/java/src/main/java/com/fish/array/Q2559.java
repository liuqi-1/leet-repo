package com.fish.array;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author liuqi
 * @date 2023/6/2
 * <p>
 * 给你一个下标从 0 开始的字符串数组 words 以及一个二维整数数组 queries 。
 * <p>
 * 每个查询 queries[i] = [li, ri] 会要求我们统计在 words 中下标在 li 到 ri 范围内（包含 这两个值）并且以元音开头和结尾的字符串的数目。
 * <p>
 * 返回一个整数数组，其中数组的第 i 个元素对应第 i 个查询的答案。
 * <p>
 * 注意：元音字母是 'a'、'e'、'i'、'o' 和 'u' 。
 */
public class Q2559 {
    /**
     * 暴力解法，会超时
     */
    class Solution1 {
        public int[] vowelStrings(String[] words, int[][] queries) {
            boolean[] flag = new boolean[words.length];
            Set<Character> set = Arrays.asList('a', 'e', 'i', 'o', 'u').stream().collect(Collectors.toSet());
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (set.contains(word.charAt(0)) && set.contains(word.charAt(word.length() - 1))) {
                    flag[i] = true;
                }
            }
            int[] result = new int[queries.length];
            for (int idx = 0; idx < queries.length; idx++) {
                for (int i = queries[idx][0]; i <= queries[idx][1]; i++) {
                    if (flag[i]) {
                        result[idx]++;
                    }
                }
            }
            return result;
        }
    }

    /**
     * 解法二：前缀和 todo 求区间段的值，考虑前缀和
     */
    class Solution {
        Set<Character> set = Arrays.asList('a', 'e', 'i', 'o', 'u').stream().collect(Collectors.toSet());

        private boolean check(String word) {
            if (set.contains(word.charAt(0)) && set.contains(word.charAt(word.length() - 1))) {
                return true;
            }
            return false;
        }

        public int[] vowelStrings(String[] words, int[][] queries) {
            int[] cnt = new int[words.length + 1];
            for (int i = 0; i < words.length; i++) {
                cnt[i + 1] = cnt[i];
                if (check(words[i])) {
                    cnt[i + 1]++;
                }
            }
            int[] result = new int[queries.length];
            for (int i = 0; i < queries.length; i++) {
                result[i] = cnt[queries[i][1] + 1] - cnt[queries[i][0]];
            }
            return result;
        }
    }
}
