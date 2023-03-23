package com.fish.dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuqi
 * @date 2023/8/29
 * <p>
 * https://leetcode.cn/problems/binary-trees-with-factors/
 */
public class Q823 {
    /**
     * 双指针，时间复杂度虽然也是O(n^2)，但相比于Solution1，会好很多
     */
    class Solution {
        public static final int MOD = 1000000007;

        public int numFactoredBinaryTrees(int[] arr) {
            Arrays.sort(arr);
            long[] dp = new long[arr.length];
            dp[0] = 1L;
            int res = 1;
            for (int i = 1; i < arr.length; i++) {
                dp[i] = 1;
                int left = 0, right = i - 1;
                while (left <= right) {
                    long temp = (long) arr[left] * arr[right];
                    if (temp < arr[i]) {
                        left++;
                    } else if (temp > arr[i]) {
                        right--;
                    } else {
                        if (left == right) {
                            dp[i] = (dp[i] + dp[left] * dp[right]) % MOD;
                        } else {
                            dp[i] = (dp[i] + (dp[left] * dp[right] * 2) % MOD) % MOD;
                        }
                        left++;
                        right--;
                    }
                }
                res = (int) ((res + dp[i]) % MOD);
            }
            return res;
        }
    }

    /**
     * 时间复杂度应该是O(N^2)级别，需要遍历已经遍历过的所有节点
     */
    class Solution1 {
        public static final int MOD = 1000000007;

        public int numFactoredBinaryTrees(int[] arr) {
            long[] dp = new long[arr.length];
            Arrays.sort(arr);
            dp[0] = 1;
            Map<Integer, Long> rootMap = new HashMap<>();// map最大值应该是两千
            rootMap.put(arr[0], 1L);
            for (int i = 1; i < arr.length; i++) {
                long rootCnt = 1;
                for (int root : rootMap.keySet()) {
                    if (arr[i] % root != 0) {
                        continue;
                    }
                    long left = Long.valueOf(rootMap.get(root));
                    long right = Long.valueOf(rootMap.getOrDefault(arr[i] / root, 0L));
                    rootCnt = (rootCnt + (left * right) % MOD) % MOD;
                }
                rootMap.put(arr[i], rootCnt);
                dp[i] = (dp[i - 1] + rootCnt) % MOD;
            }
            return (int) dp[arr.length - 1];
        }
    }

    @Test
    public void test() {
        new Solution().numFactoredBinaryTrees(new int[]{18865777, 36451879, 36878647});
    }
}
