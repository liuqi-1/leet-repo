package com.fish.array;

/**
 * @author liuqi
 * @date 2023/10/17
 */
public class Q2652 {
    class Solution {
        public int sumOfMultiples(int n) {
            int result = 0;
            int idx = 1;
            while (3 * idx <= n) {
                result += 3 * idx;
                idx++;
            }
            idx = 1;
            while (5 * idx <= n) {
                if (idx % 3 != 0) {
                    result += 5 * idx;
                }
                idx++;
            }
            idx = 1;
            while (7 * idx <= n) {
                if (idx % 3 != 0 && idx % 5 != 0) {
                    result += 7 * idx;
                }
                idx++;
            }
            return result;
        }
    }
}
