package com.fish.math;

/**
 * @author liuqi
 * @date 2023/7/17
 */
public class Q415 {
    class Solution {
        public String addStrings(String num1, String num2) {
            StringBuilder sb = new StringBuilder();
            int idx1 = num1.length() - 1;
            int idx2 = num2.length() - 1;
            int r = 0, sum = 0;
            while (idx1 >= 0 || idx2 >= 0 || r != 0) {
                sum = r;
                if (idx1 >= 0) {
                    sum += num1.charAt(idx1--) - '0';
                }
                if (idx2 >= 0) {
                    sum += num2.charAt(idx2--) - '0';
                }
                sb.append(sum % 10);
                r = sum / 10;
            }
            return sb.reverse().toString();
        }
    }
}
