package com.fish.string;

/**
 * @author:liuqi
 * @date:2023/5/11
 */
public class Q1016 {
    class Solution {
        private void plusOne(StringBuilder sb) {
            int idx = sb.indexOf("0");
            if (idx == -1) {
                idx = sb.length();
                sb.append('1');
            } else {
                sb.setCharAt(idx, '1');
            }
            for (int i = 0; i < idx; i++) {
                sb.setCharAt(i, '0');
            }
        }

        public boolean queryString(String s, int n) {
            StringBuilder sb = new StringBuilder(s).reverse();
            StringBuilder test = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                plusOne(test);
                if (!sb.toString().contains(test.toString())) {
                    return false;
                }
            }
            return true;
        }
    }
}
