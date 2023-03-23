package com.fish.string;

/**
 * @author liuqi
 * @date 2023/6/3
 * <p>
 * 如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。
 * <p>
 * 给你一个字符串 text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。
 */


public class Q1156 {
    class Solution {
        public int maxRepOpt1(String text) {
            int length = text.length();
            int[] cnt = new int[length];
            for (int i = 0; i < length; i++) {
                cnt[text.charAt(i) - 'a']++;
            }
            int left = 0, right = 0;
            int result = 1;
            while (left < length) {
                char c = text.charAt(left);
                while (right + 1 < length && text.charAt(right + 1) == c) {
                    right++;
                }
                if (right >= length) {
                    int len = right - left;
                    if (cnt[c - 'a'] > len) {
                        len++;
                    }
                    result = Math.max(result, len);
                    break;
                }
                int right1 = right + 1;
                while (right1 + 1 < length && text.charAt(right1 + 1) == c) {
                    right1++;
                }
                int len = right1 - left;
                if (cnt[c - 'a'] > len) {
                    len++;
                }
                result = Math.max(result, len);
                if (right1 - right <= 2) {
                    left = right + 1;
                } else {
                    left = right1 + 1;
                }
            }
            return result;
        }
    }
}
