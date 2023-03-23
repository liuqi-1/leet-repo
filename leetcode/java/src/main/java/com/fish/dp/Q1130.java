package com.fish.dp;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author liuqi
 * @date 2023/5/31
 * <p>
 * 给你一个正整数数组 arr，考虑所有满足以下条件的二叉树：
 * <p>
 * 每个节点都有 0 个或是 2 个子节点。
 * 数组 arr 中的值与树的中序遍历中每个叶节点的值一一对应。
 * 每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。
 * 在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个 32 位整数。
 * <p>
 * 如果一个节点有 0 个子节点，那么该节点为叶节点。
 * <p>
 * https://leetcode.cn/problems/minimum-cost-tree-from-leaf-values
 */
public class Q1130 {
    /**
     * 方法2，单调栈
     */
    class Solution {
        public int mctFromLeafValues(int[] arr) {
            int res = 0;
            Deque<Integer> stk = new ArrayDeque<Integer>();
            for (int x : arr) {
                while (!stk.isEmpty() && stk.peek() <= x) {
                    int y = stk.pop();
                    if (stk.isEmpty() || stk.peek() > x) {
                        res += y * x;
                    } else {
                        res += stk.peek() * y;
                    }
                }
                stk.push(x);
            }
            while (stk.size() >= 2) {
                int x = stk.pop();
                res += stk.peek() * x;
            }
            return res;
        }
    }

    /**
     * 方法1，参考官方题解，dp[i,j] = dp[i,k]+dp[k+1,j]+max[i,k]*max[k+1,j]，k->[i,j-1]
     */
    class Solution1 {
        public int mctFromLeafValues(int[] arr) {
            int len = arr.length;
            int[][] dp = new int[len][len];
            int[][] max = new int[len][len];
            for (int i = 0; i < len; i++) {
                dp[i][i] = 0;
                max[i][i] = arr[i];
            }
            for (int i = 1; i < len; i++) {
                int startPos = 0;
                int endPos = startPos + i;
                while (endPos < len) {
                    max[startPos][endPos] = Math.max(max[startPos + 1][endPos], arr[startPos]);
                    startPos++;
                    endPos++;
                }
            }
            for (int i = 1; i < len; i++) {
                int startPos = 0;
                int endPos = startPos + i;
                while (endPos < len) {
                    dp[startPos][endPos] = Integer.MAX_VALUE;
                    for (int idx = startPos; idx < endPos; idx++) {
                        int value = dp[startPos][idx] + dp[idx + 1][endPos] + max[startPos][idx] * max[idx + 1][endPos];
                        dp[startPos][endPos] = Math.min(dp[startPos][endPos], value);
                    }
                    startPos++;
                    endPos++;
                }
            }
            return dp[0][len - 1];
        }
    }

    @Test
    public void test() {
        int result = new Solution().mctFromLeafValues(new int[]{3, 5, 6, 4, 2, 2});
        System.out.println(result);
    }
}
