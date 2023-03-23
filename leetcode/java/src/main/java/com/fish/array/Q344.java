package com.fish.array;

/**
 * @author liuqi
 * @date 2023/8/7
 */
public class Q344 {
    class Solution {
        public void reverseString(char[] s) {
            int left = 0, right = s.length - 1;
            while (left < right) {
                char temp = s[left];
                s[left] = s[right];
                s[right] = temp;
                left++;
                right--;
            }
        }
    }
}
