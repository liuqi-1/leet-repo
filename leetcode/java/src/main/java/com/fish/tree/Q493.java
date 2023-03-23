package com.fish.tree;

import org.junit.jupiter.api.Test;

/**
 * @author liuqi
 * @date 2023/11/14
 */
public class Q493 {
    class Solution {
        int[] nums;

        public int bsearch(int l, int r, int val) {
            int i = l;
            for (; i <= r; i++) {
                if (val <= nums[i]) {
                    return i - l;
                }
            }
            return i - l;
        }

        public void swap(int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        public void merge(int l1, int r1, int l2, int r2) {
            int[] tempNums = new int[r2 - l1 + 1];
            int i = 0;
            while (i < tempNums.length) {
                if (l2 > r2 || (l1 <= r1 && nums[l1] <= nums[l2])) {
                    tempNums[i] = nums[l1];
                    l1++;
                } else {
                    tempNums[i] = nums[l2];
                    l2++;
                }
                i++;
            }
        }


        public int mergeSort(int left, int right) {
            if (left == right) {
                return 0;
            }
            int ans = 0;
            int mid = left + ((right - left) >> 1);
            // 计算子区间的翻转对
            ans += mergeSort(left, mid);
            ans += mergeSort(mid + 1, right);
            // 计算当前区间的翻转对
            for (int i = left; i <= mid; i++) {
                ans += bsearch(mid + 1, right, 2 * nums[i]);
            }
            // 排序
            merge(left, mid, mid + 1, right);
            return ans;
        }

        public int reversePairs(int[] nums) {
            this.nums = nums;
            return mergeSort(0, nums.length - 1);
        }
    }

    @Test
    public void test() {
        int ans = new Solution().reversePairs(new int[]{1, 3, 2, 3, 1});
        System.out.println(ans);
    }
}
