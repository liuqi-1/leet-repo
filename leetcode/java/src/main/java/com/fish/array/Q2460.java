package com.fish.array;

/**
 * @author liuqi
 * @date 2023/6/5
 * <p>
 * https://leetcode.cn/problems/apply-operations-to-an-array/
 */
public class Q2460 {
    class Solution {
        public int[] applyOperations(int[] nums) {
            int n = nums.length;
            for(int i = 0; i < n - 1; i++){
                if(nums[i] == nums[i + 1]){
                    nums[i] *= 2;
                    nums[i + 1] = 0;
                }
            }
            int i = 0, j = 0;
            while(i < n){
                if(nums[i] != 0){
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    j++;
                }
                i++;
            }
            return nums;
        }
    }

//    class Solution {
//        public int[] applyOperations(int[] nums) {
//            for (int i = 0; i < nums.length - 1; i++) {
//                if (nums[i] == nums[i + 1]) {
//                    nums[i] *= 2;
//                    nums[i + 1] = 0;
//                }
//            }
//            int idx = 0;
//            while (idx < nums.length && nums[idx] != 0) {
//                idx++;
//            }
//            int idxRight = idx + 1;
//            while (idxRight < nums.length && nums[idxRight] == 0) {
//                idxRight++;
//            }
//            if (idx >= nums.length) {
//                return nums;
//            }
//            while (idxRight < nums.length) {
//                nums[idx++] = nums[idxRight];
//                nums[idxRight++] = 0;
//                while (idx < nums.length && nums[idx] != 0) {
//                    idx++;
//                }
//                while (idxRight < nums.length && nums[idxRight] == 0) {
//                    idxRight++;
//                }
//            }
//            return nums;
//        }
//    }
}
