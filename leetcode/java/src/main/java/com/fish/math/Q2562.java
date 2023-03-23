package com.fish.math;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author liuqi
 * @date 2023/10/12
 * <p>
 * https://leetcode.cn/problems/find-the-array-concatenation-value/description/?envType=daily-question&envId=2023-10-12
 */
public class Q2562 {
    class Solution {
        public long findTheArrayConcVal(int[] nums) {
            long result = 0;
            int left = 0;
            int right = nums.length - 1;
            Deque<Integer> stack = new LinkedList<Integer>();
            while (left < right) {
                while (nums[right] != 0) {
                    stack.push(nums[right] % 10);
                    nums[right] /= 10;
                }
                while (!stack.isEmpty()) {
                    nums[left] = nums[left] * 10 + stack.poll();
                }
                result = result + nums[left];
                right--;
                left++;
            }
            if (left == right) {
                result += nums[left];
                System.out.println(nums[left]);
            }
            return result;
        }
    }
}
