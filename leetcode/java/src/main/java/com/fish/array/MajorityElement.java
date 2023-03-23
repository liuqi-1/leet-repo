package com.fish.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuqi
 * @date 2023/5/31
 * <p>
 * 给定一个大小为 n 的数组nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 作者：LeetCode
 * 链接：https://leetcode.cn/leetbook/read/tencent/x5yxxs/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * 官方题解：https://leetcode.cn/problems/majority-element/solutions/146074/duo-shu-yuan-su-by-leetcode-solution/
 */
public class MajorityElement {
    /**
     * 算法1：哈希表
     */
    class Solution1 {
        public int majorityElement(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                int cnt = map.getOrDefault(num, 0);
                map.put(num, cnt + 1);
            }
            AtomicInteger result = new AtomicInteger();
            map.forEach((k, v) -> {
                if (v > nums.length / 2) {
                    result.set(k);
                }
            });
            return result.get();
        }
    }

    /**
     * 算法2：排序
     */
    class Solution2 {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }
    }

    /**
     * 算法3：Boyer-Moore投票算法
     */
    class Solution {
        public int majorityElement(int[] nums) {
            int count = 0;
            Integer candidate = null;
            for (int num : nums) {
                if (count == 0) {
                    candidate = num;
                }
                count += (num == candidate) ? 1 : -1;
            }
            return candidate;
        }
    }
}
