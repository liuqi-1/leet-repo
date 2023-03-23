package com.fish.math;

import java.util.ArrayList;

/**
 * @author liuqi
 * @date 2023/10/9
 */
public class Q2578 {
    class Solution {
        public int splitNum(int num) {
            ArrayList<Integer> list = new ArrayList<>();
            while (num != 0) {
                list.add(num % 10);
                num /= 10;
            }
            list.sort((a, b) -> b - a);
            int d = 1;
            int result = 0;
            int r = 0;
            int idx = 0;
            while (idx < list.size()) {
                int a = list.get(idx++);
                int b = 0;
                if (idx < list.size()) {
                    b = list.get(idx++);
                }
                int sum = a + b + r;
                result += (sum % 10) * d;
                d *= 10;
                r = sum / 10;
            }
            result += r * d;
            return result;
        }
    }
}
