package com.fish.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuqi
 * @date 2023/8/26
 * <p>
 * https://leetcode.cn/problems/summary-ranges/
 */
public class Q228 {
    class Solution {
        public List<String> summaryRanges(int[] nums) {
            if (nums.length == 0) {
                return new ArrayList<>();
            }
            int left = nums[0], right = nums[0] - 1;
            List<String> result = new ArrayList<>();
            for (int num : nums) {
                if (num == right + 1) {
                    right = num;
                    continue;
                }
                if (left == right) {
                    result.add(left + "");
                } else {
                    result.add(left + "->" + right);
                }
                left = num;
                right = num;
            }
            if (left == right) {
                result.add(left + "");
            } else {
                result.add(left + "->" + right);
            }
            return result;
        }
    }
}