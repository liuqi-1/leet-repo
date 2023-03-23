package com.fish.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuqi
 * @date 2023/7/3
 * <p>
 * https://leetcode.cn/leetbook/read/tencent/x5zwpv/
 */
public class Qx5zwpv {
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            for (int i = 0; i < nums.length; i++) {
                int len = result.size();
                for (int idx = 0; idx < len; idx++) {
                    List<Integer> list = result.get(idx);
                    List<Integer> newList = new ArrayList<>();
                    newList.addAll(list);
                    newList.add(nums[i]);
                    result.add(newList);
                }
            }
            return result;
        }
    }
}
