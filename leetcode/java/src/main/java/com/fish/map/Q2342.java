package com.fish.map;

/**
 * @author liuqi
 * @date 2023/11/18
 */
public class Q2342 {
    /**
     * 计算数位和的函数本身就不复杂，不用单独开一个函数（从做题的角度来讲，如果是工程性还是有必要的
     * 另外，hashmap用数组代替即可，找到数位和的最大值，可以很大的提高时间复杂度
     */
    class Solution {
        public int maximumSum(int[] nums) {
            int ans = -1;
            int[] map = new int[82];
            for (int num : nums) {
                int sum = 0;
                int temp = num;
                while (temp != 0) {
                    sum += temp % 10;
                    temp /= 10;
                }
                if (map[sum] != 0) {
                    ans = Math.max(ans, map[sum] + num);
                }
                map[sum] = Math.max(map[sum], num);
            }
            return ans;
        }
    }
}
