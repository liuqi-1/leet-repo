package com.fish.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author liuqi
 * @date 2023/11/19
 * https://leetcode.cn/problems/maximum-sum-of-3-non-overlapping-subarrays/description/?envType=daily-question&envId=2023-11-19
 */
public class Q689 {
    /**
     * 不愧是官方题解
     */
    class Solution {
        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            int[] ans = new int[3];
            int sum1 = 0, maxSum1 = 0, maxSum1Idx = 0;
            int sum2 = 0, maxSum12 = 0, maxSum12Idx1 = 0, maxSum12Idx2 = 0;
            int sum3 = 0, maxTotal = 0;
            for (int i = k * 2; i < nums.length; ++i) {
                sum1 += nums[i - k * 2];
                sum2 += nums[i - k];
                sum3 += nums[i];
                if (i >= k * 3 - 1) {
                    if (sum1 > maxSum1) {
                        maxSum1 = sum1;
                        maxSum1Idx = i - k * 3 + 1;
                    }
                    if (maxSum1 + sum2 > maxSum12) {
                        maxSum12 = maxSum1 + sum2;
                        maxSum12Idx1 = maxSum1Idx;
                        maxSum12Idx2 = i - k * 2 + 1;
                    }
                    if (maxSum12 + sum3 > maxTotal) {
                        maxTotal = maxSum12 + sum3;
                        ans[0] = maxSum12Idx1;
                        ans[1] = maxSum12Idx2;
                        ans[2] = i - k + 1;
                    }
                    sum1 -= nums[i - k * 3 + 1];
                    sum2 -= nums[i - k * 2 + 1];
                    sum3 -= nums[i - k + 1];
                }
            }
            return ans;
        }
    }

    // 作者：力扣官方题解
    // 链接：https://leetcode.cn/problems/maximum-sum-of-3-non-overlapping-subarrays/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


    /**
     * 超时了 baka todo
     */
    class Solution1 {
        int[] subSum;

        public int[] findMax(int[] nums, int sPos, int k, int cnt) {
            if (cnt == 1) {
                if (sPos >= nums.length || subSum[sPos] == 0) {
                    return null;
                }
                int max = 0;
                int maxIdx = -1;
                while (sPos < nums.length && subSum[sPos] != 0) {
                    if (subSum[sPos] > max) {
                        max = subSum[sPos];
                        maxIdx = sPos;
                    }
                    sPos++;
                    while (sPos < nums.length && subSum[sPos] == subSum[sPos - 1]) {
                        sPos++;
                    }
                }
                return new int[]{maxIdx};
            } else {
                int[] ans = new int[cnt];
                int maxSum = 0;
                while (sPos < nums.length && subSum[sPos] != 0) {
                    int[] aa = findMax(nums, sPos + k, k, cnt - 1);
                    if (aa == null) {
                        break;
                    }
                    int sum = subSum[sPos];
                    for (int idx : aa) {
                        sum += subSum[idx];
                    }
                    if (sum > maxSum) {
                        maxSum = sum;
                        ans[0] = sPos;
                        for (int i = 0; i < aa.length; i++) {
                            ans[i + 1] = aa[i];
                        }
                    }
                    sPos++;
                    while (sPos < nums.length && subSum[sPos] == subSum[sPos - 1]) {
                        sPos++;
                    }
                }
                return maxSum == 0 ? null : ans;
            }
        }

        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            int[] preSum = new int[nums.length];
            int sum = 0;
            subSum = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                preSum[i] = sum;
                int preIdx = i - k + 1;
                if (preIdx >= 0) {
                    subSum[preIdx] = sum - preSum[preIdx] + nums[preIdx];
                }
            }
            System.out.println(Arrays.toString(subSum));
            return findMax(nums, 0, k, 3);
        }
    }

    @Test
    public void test() {
        int[] ans = new Solution().maxSumOfThreeSubarrays(new int[]{1, 2, 1, 2, 6, 7, 5, 1}, 2);
        System.out.println(Arrays.toString(ans));
    }
}
