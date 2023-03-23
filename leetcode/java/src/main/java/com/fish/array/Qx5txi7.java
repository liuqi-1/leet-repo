package com.fish.array;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * https://leetcode.cn/leetbook/read/tencent/x5txi7/
 * <p>
 * 官方题解：
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/solutions/307351/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode-/
 */
public class Qx5txi7 {
    // 自己的方法，优先队列实现，时间复杂度为nlogn级别
    class Solution1 {
        // 小顶堆，维护整个数组最大的k个元素，遍历完成后，堆顶元素就是答案。可以降低时间和空间复杂度
        public int findKthLargest(int[] nums, int k) {
            Queue<Integer> q = new PriorityQueue<Integer>((a, b) -> b - a);
            for (int num : nums) {
                q.add(num);
                if (q.size() > k) {
                    q.poll();
                }
            }
            return q.poll();
        }

        // 大顶堆，先将所有元素插入堆，然后删除k-1次，此时堆顶元素就是答案
        public int findKthLargest1(int[] nums, int k) {
            Queue<Integer> q = new PriorityQueue<Integer>((a, b) -> b - a);
            for (int num : nums) {
                q.add(num);
            }
            for (int i = 0; i < k - 1; i++) {
                q.remove();
            }
            return q.remove();
        }
    }

    //  手动实现优先队列
    class Solution2 {
        int heapSize = 0;
        int[] data;

        public int findKthLargest(int[] nums, int k) {
            data = new int[nums.length + 1];
            for (int num : nums) {
                add(num);
                if (heapSize > k) {
                    poll();
                }
            }
            return poll();
        }

        public void add(int num) {
            heapSize++;
            data[heapSize] = num;
            int idx = heapSize;
            while (idx > 1 && data[idx / 2] > data[idx]) {
                swap(idx, idx / 2);
                idx /= 2;
            }
        }

        public int poll() {
            int res = data[1];
            data[1] = data[heapSize--];
            maxHeapify(1);
            return res;
        }

        public void maxHeapify(int idx) {
            int smallestIdx = idx;
            int l = idx * 2;
            int r = idx * 2 + 1;
            if (l <= heapSize && data[l] < data[idx]) {
                smallestIdx = l;
            }
            if (r <= heapSize && data[r] < data[smallestIdx]) {
                smallestIdx = r;
            }
            if (smallestIdx != idx) {
                swap(idx, smallestIdx);
                maxHeapify(smallestIdx);
            }
        }

        public void swap(int i1, int i2) {
            int t = data[i1];
            data[i1] = data[i2];
            data[i2] = t;
        }
    }

    //  快速选择算法
    class Solution3 {
        Random random = new Random();

        public int quickSelect(int l, int r, int k, int[] nums) {
            // 判断l和r是否重合，如果是，直接返回
            if (l == r) {
                return nums[l];
            }
            // 随机选择轴
            int pivotIdx = Math.abs(random.nextInt()) % (r - l + 1) + l;
            int pivot = nums[pivotIdx];
            // 将小于pivot的数移动到左边，大于pivot的数移动到右边
            nums[pivotIdx] = nums[r];
            int lIdx = l, rIdx = r;
            while (true) {
                // 从左边找大于pivot的数，移动到右边
                while (lIdx < rIdx && nums[lIdx] <= pivot) {
                    lIdx++;
                }
                if (lIdx == rIdx) {
                    if (rIdx == nums.length - k) {
                        return pivot;
                    } else if (rIdx < nums.length - k) {
                        return quickSelect(rIdx + 1, r, k, nums);
                    } else {
                        return quickSelect(l, rIdx - 1, k, nums);
                    }
                }
                swap(lIdx, rIdx--, nums);
                // 从右边找小于pivot的数，移动到左边
                while (rIdx > lIdx && nums[rIdx] >= pivot) {
                    rIdx--;
                }
                if (rIdx == lIdx) {
                    if (rIdx == nums.length - k) {
                        return pivot;
                    } else if (rIdx < nums.length - k) {
                        return quickSelect(rIdx + 1, r, k, nums);
                    } else {
                        return quickSelect(l, rIdx - 1, k, nums);
                    }
                }
                swap(lIdx++, rIdx, nums);
            }
        }

        // 辅助函数
        private void swap(int idx1, int idx2, int[] nums) {
            int temp = nums[idx1];
            nums[idx1] = nums[idx2];
            nums[idx2] = temp;
        }

        public int findKthLargest(int[] nums, int k) {
            return quickSelect(0, nums.length - 1, k, nums);
        }
    }

    @Test
    public void test() {
        int result = new Solution3().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2);
        System.out.println(result);
    }
}