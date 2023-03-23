package com.fish.string;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liuqi
 * @date 2023/10/25
 */
public class Q2698 {
    class Solution {
        public boolean check(String num, int i) {
            if (help(num, 0, i)) {
                return true;
            }
            return false;
        }

        public boolean help(String num, int startPos, int target) {
            int sum = 0;
            for (int i = startPos; i < num.length(); i++) {
                sum = sum * 10 + num.charAt(i) - '0';
                if (sum > target) {
                    break;
                }
                if (help(num, i + 1, target - sum)) {
                    return true;
                }
            }
            if (sum == target) {
                return true;
            }
            return false;
        }

        public int punishmentNumber(int n) {
            int result = 0;
            for (int i = 1; i <= n; i++) {
                String num = i * i + "";
                if (check(num, i)) {
                    result += i * i;
                }
            }
            return result;
        }
    }

    @Test
    public void test() {
        new Solution().punishmentNumber(10);
    }

    class Solution1 {
        public boolean check(String num, int i) {
            if (help(num).contains(i)) {
                return true;
            }
            return false;
        }

        public Set help(String num) {
            Set<Integer> set = new HashSet<>();
            if (num.length() == 1) {
                set.add(Integer.parseInt(num));
                return set;
            }
            for (int i = 1; i < num.length(); i++) {
                Set<Integer> set1 = help(num.substring(0, i));
                Set<Integer> set2 = help(num.substring(i, num.length()));
                for (int num1 : set1) {
                    for (int num2 : set2) {
                        set.add(num1 + num2);
                    }
                }
            }
            set.add(Integer.parseInt(num));
            return set;
        }

        public int punishmentNumber(int n) {
            int result = 0;
            for (int i = 1; i <= n; i++) {
                String num = i * i + "";
                if (check(num, i)) {
                    result += i * i;
                }
            }
            return result;
        }
    }
}
