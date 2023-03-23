package com.fish.math;

/**
 * @author liuqi
 * @date 2023/5/30
 * <p>
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 * <p>
 * 作者：LeetCode
 * 链接：https://leetcode.cn/leetbook/read/tencent/x51e9r/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class OnlyOneNum {
    class Solution {
        public int singleNumber(int[] nums) {
            int result = 0;
            for (int num : nums) {
                result = result ^ num;
            }
            return result;
        }
    }
}
