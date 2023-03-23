package com.fish.dp;

import org.junit.jupiter.api.Test;

/**
 * @author liuqi
 * @date 2023/6/27
 */
public class Q1186 {
    class Solution {
        /**
         * 自创拙略解法
         * a->删除1次，b->删除0次，c->删除0次情况下的最小值
         *
         * @param arr
         * @return
         */
        public int maximumSum(int[] arr) {
            int a = arr[0], b = arr[0], c = arr[0], result = arr[0];
            for (int i = 1; i < arr.length; i++) {
                int aa = Math.max(a + arr[i], b);
                a = Math.max(a + arr[i], b + arr[i] - Math.min(c, arr[i]));
                // b-c < a在绝大多数情况下成立，例外情况为b-c之后，得到一个空数组
                c = b < 0 ? arr[i] : Math.min(c, arr[i]);
                b = Math.max(arr[i], b + arr[i]);
                result = Math.max(result, Math.max(a, b));
            }
            return result;
        }

        /**
         * 官方解法：dp0->删除0次，ap1->删除1次
         *
         * @param arr
         * @return
         */
        public int maximumSum1(int[] arr) {
            int dp0 = arr[0], dp1 = 0, res = arr[0];
            for (int i = 1; i < arr.length; i++) {
                dp1 = Math.max(dp0, dp1 + arr[i]);
                dp0 = Math.max(dp0, 0) + arr[i];
                res = Math.max(res, Math.max(dp0, dp1));
            }
            return res;
        }
        //
        // 作者：力扣官方题解
        // 链接：https://leetcode.cn/problems/maximum-subarray-sum-with-one-deletion/solutions/2314975/shan-chu-yi-ci-de-dao-zi-shu-zu-de-zui-d-o1o9/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }

    @Test
    public void test() {
        new Solution().maximumSum(new int[]{-1, -1, -1, -1, -1, -1});
    }
}
