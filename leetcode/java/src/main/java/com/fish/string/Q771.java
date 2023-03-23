package com.fish.string;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liuqi
 * @date 2023/7/24
 */
public class Q771 {
    class Solution {
        public int numJewelsInStones(String jewels, String stones) {
            Set<Character> set = new HashSet<>();
            int result = 0;
            for (int i = 0; i < jewels.length(); i++) {
                set.add(jewels.charAt(i));
            }
            for (int i = 0; i < stones.length(); i++) {
                if (set.contains(stones.charAt(i))) {
                    result++;
                }
            }
            return result;
        }
    }
}
