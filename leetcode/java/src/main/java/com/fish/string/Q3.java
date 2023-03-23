package com.fish.string;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liuqi
 * @date 2023/8/28
 * <p>
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
public class Q3 {
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int left = 0, right = 0;
            int maxLen = 0;
            Set<Character> set = new HashSet<>();
            while (right < s.length()) {
                if (!set.contains(s.charAt(right))) {
                    set.add(s.charAt(right));
                    maxLen = Math.max(maxLen, right - left + 1);
                } else {
                    while (s.charAt(left) != s.charAt(right)) {
                        set.remove(s.charAt(left));
                        left++;
                    }
                    left++;
                }
                right++;
            }
            return maxLen;
        }
    }
}
