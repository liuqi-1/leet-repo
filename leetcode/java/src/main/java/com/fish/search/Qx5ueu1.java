package com.fish.search;

import org.junit.jupiter.api.Test;

/**
 * @author liuqi
 * @date 2023/6/13
 * <p>
 * <p>
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。
 * <p>
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 * 作者：LeetCode
 * 链接：https://leetcode.cn/leetbook/read/tencent/x5ueu1/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Qx5ueu1 {
    /**
     * 最开始以为是logn级别的复杂度，但是实际上寻找idx的过程还是属于n级别的
     */
    class Solution1 {
        int search(int[] nums, int t, int l, int r) {
            while (l <= r) {
                int mid = (l + r) / 2;
                if (nums[mid] == t) {
                    return mid;
                } else if (nums[mid] < t) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return -1;
        }

        public int search(int[] nums, int target) {
            int len = nums.length;
            if (nums.length == 1) {
                return nums[0] == target ? 0 : -1;
            }
            int idx = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    idx = i + 1;
                    break;
                }
            }
            int r1 = search(nums, target, 0, idx - 1);
            if (r1 != -1) {
                return r1;
            }
            return search(nums, target, idx, len - 1);
        }
    }
    class Solution {
        public int search(int[] nums, int target) {
            int n = nums.length;
            if (n == 0) {
                return -1;
            }
            if (n == 1) {
                return nums[0] == target ? 0 : -1;
            }
            int l = 0, r = n - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                if (nums[0] <= nums[mid]) {
                    if (nums[0] <= target && target < nums[mid]) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                } else {
                    if (nums[mid] < target && target <= nums[n - 1]) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
            }
            return -1;
        }
    }

//    作者：力扣官方题解
//    链接：https://leetcode.cn/problems/search-in-rotated-sorted-array/solutions/220083/sou-suo-xuan-zhuan-pai-xu-shu-zu-by-leetcode-solut/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    @Test
    public void test() {
        int[] nums = new int[]{4, 5};
        System.out.println(new Solution().search(nums, 0));
    }
}
