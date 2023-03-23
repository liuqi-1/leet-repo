package com.fish.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liuqi
 * @date 2023/7/15
 */
public class Q18 {
    /**
     * 官方解法
     */
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
            if (nums == null || nums.length < 4) {
                return quadruplets;
            }
            Arrays.sort(nums);
            int length = nums.length;
            for (int i = 0; i < length - 3; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                    break;
                }
                if ((long) nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                for (int j = i + 1; j < length - 2; j++) {
                    if (j > i + 1 && nums[j] == nums[j - 1]) {
                        continue;
                    }
                    if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                        break;
                    }
                    if ((long) nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                        continue;
                    }
                    int left = j + 1, right = length - 1;
                    while (left < right) {
                        long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                        if (sum == target) {
                            quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                            while (left < right && nums[left] == nums[left + 1]) {
                                left++;
                            }
                            left++;
                            while (left < right && nums[right] == nums[right - 1]) {
                                right--;
                            }
                            right--;
                        } else if (sum < target) {
                            left++;
                        } else {
                            right--;
                        }
                    }
                }
            }
            return quadruplets;
        }
    }


    /**
     * 时间复杂度应该是 O(n^3)
     */
    class Solution1 {
        public List<List<Integer>> nSum(int[] nums, long target, int startIdx, int n) {
            List<List<Integer>> result = new LinkedList<>();
            if (n == 2) {
                int left = startIdx, right = nums.length - 1;
                while (left < right) {
                    int sum = nums[left] + nums[right];
                    if (sum == target) {
                        List<Integer> list = new LinkedList<>();
                        list.add(nums[left]);
                        list.add(nums[right]);
                        result.add(list);
                    }
                    if (sum > target) {
                        while (right - 1 > left && nums[right - 1] == nums[right]) {
                            right--;
                        }
                        right--;
                    } else {
                        while (left + 1 < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                    }
                }

            } else if (n > 2) {
                for (int i = startIdx; i < nums.length; i++) {
                    List<List<Integer>> list = nSum(nums, target - nums[i], i + 1, n - 1);
                    for (List<Integer> integers : list) {
                        integers.add(nums[i]);
                    }
                    result.addAll(list);
                    while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                        i++;
                    }
                }
            }
            return result;
        }

        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums);
            return nSum(nums, target, 0, 4);
        }
    }
}
