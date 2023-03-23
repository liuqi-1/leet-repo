package com.fish.tree;

import java.util.*;

/**
 * @author liuqi
 * @date 2023/10/8
 * https://leetcode.cn/problems/stock-price-fluctuation/?envType=daily-question&envId=2023-10-08
 */
public class Q2034 {
    class StockPrice {
        private Map<Integer, Integer> d = new HashMap<>();
        private TreeMap<Integer, Integer> ls = new TreeMap<>();
        private int last;

        public StockPrice() {

        }

        public void update(int timestamp, int price) {
            if (d.containsKey(timestamp)) {
                int old = d.get(timestamp);
                if (ls.merge(old, -1, Integer::sum) == 0) {
                    ls.remove(old);
                }
            }
            d.put(timestamp, price);
            ls.merge(price, 1, Integer::sum);
            last = Math.max(last, timestamp);
        }

        public int current() {
            return d.get(last);
        }

        public int maximum() {
            return ls.lastKey();
        }

        public int minimum() {
            return ls.firstKey();
        }
    }

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */
}
