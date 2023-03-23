package com.fish.string;

/**
 * @author liuqi
 * @date 2023/10/23
 */
public class Q2678 {
    class Solution {
        public int countSeniors(String[] details) {
            int count = 0;
            for (String info : details) {
                String ageString = info.substring(11, 13);
                Integer age = Integer.parseInt(ageString);
                if (age > 60) {
                    count++;
                }
            }
            return count;
        }
    }
}