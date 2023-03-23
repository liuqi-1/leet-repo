package com.fish.math;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author liuqi
 * @date 2023/9/1
 * <p>
 * https://leetcode.cn/problems/number-of-ways-to-buy-pens-and-pencils/
 */
public class Q2440 {
    class Solution {
        public long waysToBuyPensPencils(int total, int cost1, int cost2) {
            int cnt1 = total / cost1;
            long result = 0;
            for (int i = 0; i <= cnt1; i++) {
                int rest = total - cost1 * i;
                int cnt2 = rest / cost2 + 1;
                result += cnt2;
            }
            return result;
        }
    }


    /**
     * 垃圾解法，会超时
     */
    class Solution1 {
        Set<String> set = new HashSet<>();
        int cost1, cost2;

        public void help(int total, int cnt1, int cnt2) {
            if (total < cost1 && total < cost2) {
                return;
            }
            if (total >= cost1) {
                set.add((cnt1 + 1) + "-" + cnt2);
                help(total - cost1, cnt1 + 1, cnt2);
            }
            if (total >= cost2) {
                set.add(cnt1 + "-" + (cnt2 + 1));
                help(total - cost2, cnt1, cnt2 + 1);
            }
        }

        public long waysToBuyPensPencils(int total, int cost1, int cost2) {
            this.cost1 = cost1;
            this.cost2 = cost2;
            help(total, 0, 0);
            return set.size() + 1;// 补上什么都不买的情况
        }
    }

    @Test
    public void test() {
        Integer[] ints1 = new Integer[]{1, 2};
        Integer[] ints2 = new Integer[]{1, 2};
        System.out.println(ints1.hashCode());
        System.out.println(ints2.hashCode());
        System.out.println(ints1 == ints2);
        System.out.println(ints1.equals(ints2));

        System.out.println(Arrays.hashCode(ints1));
        System.out.println(Arrays.hashCode(ints2));
    }
}
