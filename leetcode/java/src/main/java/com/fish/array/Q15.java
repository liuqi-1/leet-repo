package com.fish.array;


import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.cn/problems/3sum/description/
 */
public class Q15 {
    /**
     * 20231120重做，这和恩算有啥区别，呜呜
     */
    class Solution3 {
        public List<List<Integer>> nSum(int[] nums, int sPos, int n, int target) {
            if (n == 1) {
                List<List<Integer>> ans = new LinkedList<>();
                while (sPos < nums.length) {
                    if (nums[sPos] == target) {
                        List<Integer> list = new LinkedList<>();
                        list.add(nums[sPos]);
                        ans.add(list);
                    }
                    sPos++;
                    while (sPos < nums.length && nums[sPos] == nums[sPos - 1]) {
                        sPos++;
                    }
                }
                return ans;
            } else {
                List<List<Integer>> ans = new ArrayList<>();
                while (sPos < nums.length) {
                    List<List<Integer>> ret = nSum(nums, sPos + 1, n - 1, target - nums[sPos]);
                    for (List<Integer> list : ret) {
                        list.add(nums[sPos]);
                        ans.add(list);
                    }
                    sPos++;
                    while (sPos < nums.length && nums[sPos] == nums[sPos - 1]) {
                        sPos++;
                    }
                }
                return ans;
            }
        }

        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            return nSum(nums, 0, 3, 0);
        }
    }


    /**
     * 恩算，超出时间限制
     */
    class Solution1 {
        public List<List<Integer>> threeSum(int[] nums) {
            HashMap<Integer, List<int[]>> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    int key = nums[i] + nums[j];
                    List<int[]> list = map.getOrDefault(key, new ArrayList<>());
                    list.add(new int[]{i, j});
                    map.put(key, list);
                }
            }
            Set<List<Integer>> result = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(-nums[i])) {
                    List<int[]> idxList = map.get(-nums[i]);
                    for (int[] ints : idxList) {
                        if (i == ints[0] || i == ints[1]) {
                            continue;
                        }
                        List<Integer> l = new ArrayList<>(3);
                        l.add(nums[i]);
                        l.add(nums[ints[0]]);
                        l.add(nums[ints[1]]);
                        l.sort(Comparator.comparingInt(Integer::intValue));
                        result.add(l);
                    }
                }
            }
            return result.stream().collect(Collectors.toList());
        }
    }


    /**
     * n数之和通用解法
     */
    class Solution2 {
        public List<List<Integer>> threeSum(int[] nums) {
            // 多少个数字的和
            int n = 3;
            // 从数组中的开始下标
            int start = 0;
            // 排序数组
            Arrays.sort(nums);
            // 调用n数之和通用解法
            return nSum(nums, 0, n, 0);
        }

        /**
         * @param nums   已排序好的数组
         * @param target 目标和
         * @param n      数数量
         * @param start  从数组中什么位置开始计算
         * @return
         */
        public List<List<Integer>> nSum(int[] nums, int target, int n, int start) {
            List<List<Integer>> res = new ArrayList<>();
            // 判断是否应该返回空
            if (nums == null || nums.length < n) {
                return res;
            }
            // 如果n等于1（但其实好像用处不大，如果n大于2，那么在递归过程中，n永远不可能等于1）
            if (n == 1) {
                for (int i = start; i < nums.length; i++) {
                    if (nums[i] == target) {
                        List<Integer> ins = new ArrayList<>();
                        ins.add(target);
                        res.add(ins);
                        return res;
                    }
                }
                return res;
            }
            // 两数之和，双指针解决
            if (n == 2) {
                int left = start;
                int right = nums.length - 1;
                while (left < right) {
                    int cur = nums[left] + nums[right];
                    if (cur == target) {
                        List<Integer> item = new ArrayList<>();
                        item.add(nums[left]);
                        item.add(nums[right]);
                        res.add(item);
                        // 跳过重复数字
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (cur > target) {
                        right--;
                    } else if (cur < target) {
                        left++;
                    }
                }
            } else {
                for (int i = start; i < nums.length; i++) {
                    List<List<Integer>> lists = nSum(nums, target - nums[i], n - 1, i + 1);
                    // 把nums[i] 加到结果集里
                    for (List<Integer> list : lists) {
                        list.add(nums[i]);
                        res.add(list);
                    }
                    // 去掉重复数字
                    while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                        i++;
                    }
                }
            }
            return res;
        }
    }

    @Test
    public void test() {
        // new Solution().threeSum(new int[]{-1, 0, 1, 2, -1, -4}).forEach(e -> {
        //     for (Integer integer : e) {
        //         System.out.print(integer + "  ");
        //     }
        //     System.out.println();
        // });
    }
}
