package com.fish.search;

import java.util.Arrays;

/**
 * @author liuqi
 * @date 2023/6/1
 * <p>
 * 给你一个正整数数组 price ，其中 price[i] 表示第 i 类糖果的价格，另给你一个正整数 k 。
 * <p>
 * 商店组合 k 类 不同 糖果打包成礼盒出售。礼盒的 甜蜜度 是礼盒中任意两种糖果 价格 绝对差的最小值。
 * <p>
 * 返回礼盒的 最大 甜蜜度。
 */
public class Q2517 {
    /**
     * 不会做，看的题解。
     * todo 求最小值最大或者求最大值最小，首先考虑是否可以二分
     */
    class Solution {
        public int maximumTastiness(int[] price, int k) {
            Arrays.sort(price);
            int left = 0, right = price[price.length - 1] - price[0];
            while (left < right) {
                int mid = (left + right + 1) / 2;
                if (check(price, k, mid)) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            return left;
        }

        public boolean check(int[] price, int k, int tastiness) {
            int prev = Integer.MIN_VALUE / 2;
            int cnt = 0;
            for (int p : price) {
                if (p - prev >= tastiness) {
                    cnt++;
                    prev = p;
                }
            }
            return cnt >= k;
        }
    }

//    作者：力扣官方题解
//    链接：https://leetcode.cn/problems/maximum-tastiness-of-candy-basket/solutions/2290689/li-he-de-zui-da-tian-mi-du-by-leetcode-s-sq44/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
