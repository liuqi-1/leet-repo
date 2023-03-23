package com.fish.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuqi
 * @date 2023/7/6
 * <p>
 * https://leetcode.cn/problems/maximum-split-of-positive-even-integers/
 */
public class Q2178 {
    class Solution {
        public List<Long> maximumEvenSplit(long finalSum) {
            List<Long> res = new ArrayList<>();
            if (finalSum % 2 == 1) {
                return res;
            }
            long cnt = finalSum / 2;
            long twoNeeded = 1;
            while (cnt >= twoNeeded) {
                cnt -= twoNeeded;
                res.add(2 * twoNeeded);
                twoNeeded++;
            }
            long finalNum = res.get(res.size() - 1);
            finalNum += cnt * 2;
            res.set(res.size() - 1, finalNum);
            return res;
        }
    }
}
