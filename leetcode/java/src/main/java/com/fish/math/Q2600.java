package com.fish.math;

/**
 * @author liuqi
 * @date 2023/7/5
 * https://leetcode.cn/problems/k-items-with-the-maximum-sum/
 */
public class Q2600 {
    class Solution {
        public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
            if (k <= numOnes) {
                return k;
            } else if (k <= numOnes + numZeros) {
                return numOnes;
            } else {
                return numOnes - (k - numOnes - numZeros);
            }
        }
    }
}
