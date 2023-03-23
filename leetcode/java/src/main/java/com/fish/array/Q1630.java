package com.fish.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuqi
 * @date 2023/3/23
 *
 * https://leetcode.cn/problems/arithmetic-subarrays/
 */
public class Q1630 {
    class Solution {
        public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
            int m = l.length;
            List<Boolean> ans = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                int left = l[i];
                int right = r[i];
                int[] temp = new int[right - left + 1];
                for (int j = left; j <= right; j++) {
                    temp[j - left] = nums[j];
                }
                ans.add(judge(temp));
            }
            return ans;
        }

        private boolean judge(int[] arr) {
            int m = arr.length;
            Arrays.sort(arr);
            for (int i = 0; i < m - 1; i++) {
                if (arr[i + 1] - arr[i] != arr[1] - arr[0]) {
                    return false;
                }
            }
            return true;
        }
    }
}
