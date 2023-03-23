package com.fish.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个下标从0开始的整数数组nums ，判断是否存在两个长度为2的子数组且它们的和相等。
 * 注意，这两个子数组起始位置的下标必须不相同 。
 * 如果这样的子数组存在，请返回 true，否则返回 false 。
 *
 * 子数组 是一个数组中一段连续非空的元素组成的序列。
 * @author liuqi
 * @date 2023/3/26
 */
public class Q2395 {
    class Solution {
        public boolean findSubarrays(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for(int i=0;i<nums.length-1;i++){
                int sum = nums[i]+nums[i+1];
                if(set.contains(sum)){
                    return true;
                }else{
                    set.add(sum);
                }
            }
            return false;
        }
    }
}
