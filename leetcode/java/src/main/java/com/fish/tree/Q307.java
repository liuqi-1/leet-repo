package com.fish.tree;

/**
 * @author liuqi
 * @date 2023/11/13
 */
public class Q307 {

    class NumArray {
        private int[] tree;
        private int[] nums;
        int n;

        public NumArray(int[] nums) {
            this.n = nums.length;
            this.nums = nums;
            this.tree = new int[n + 1];
            for (int i = 0; i < n; i++) {
                add(i + 1, nums[i]);
            }
        }

        public void update(int index, int val) {
            add(index + 1, val - nums[index]);
            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            // prefixSum(right+1)右端点为nums[right]，prefixSum(left)右端点为nums[left]左边的节点
            return prefixSum(right + 1) - prefixSum(left);
        }

        private int lowBit(int x) {
            return x & -x;
        }

        private void add(int index, int val) {
            while (index <= n) {
                tree[index] += val;
                index += lowBit(index);
            }
        }

        private int prefixSum(int index) {
            int ans = 0;
            while (index > 0) {
                ans += tree[index];
                index -= lowBit(index);
            }
            return ans;
        }
    }


    /**
     * 线段树解法
     */
    class NumArray2 {
        int[] nums;
        int[] d;
        int n;

        public NumArray2(int[] nums) {
            this.nums = nums;
            this.n = nums.length;
            this.d = new int[(int) Math.pow(2, Math.ceil(Math.log(nums.length) / Math.log(2)) + 1)];
            build(0, n - 1, 1);
        }

        public void build(int s, int t, int p) {
            if (s == t) {
                d[p] = nums[s];
                return;
            }
            int mid = s + ((t - s) >> 1);
            build(s, mid, 2 * p);
            build(mid + 1, t, 2 * p + 1);
            d[p] = d[2 * p] + d[2 * p + 1];
        }

        public int getSum(int l, int r, int s, int t, int p) {
            if (l <= s && t <= r) {
                return d[p];
            }
            int mid = s + ((t - s) >> 1);
            int sum = 0;
            if (l <= mid) {
                sum += getSum(l, r, s, mid, 2 * p);
            }
            if (r > mid) {
                sum += getSum(l, r, mid + 1, t, 2 * p + 1);
            }
            return sum;
        }

        public void update(int index, int val) {
            updateHelp(index, val - nums[index], 0, n - 1, 1);
        }

        public void updateHelp(int index, int c, int s, int t, int p) {
            if (s == t) {
                d[p] += c;
                nums[index] += c;
                return;
            }
            int mid = s + ((t - s) >> 1);
            if (index <= mid) {
                updateHelp(index, c, s, mid, 2 * p);
            } else {
                updateHelp(index, c, mid + 1, t, 2 * p + 1);
            }
            d[p] += c;
        }

        public int sumRange(int left, int right) {
            return getSum(left, right, 0, n - 1, 1);
        }
    }


    /**
     * 暴力求解
     */
    class NumArray1 {

        int[] nums;

        public NumArray1(int[] nums) {
            this.nums = nums;
        }

        public void update(int index, int val) {
            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            int sum = 0;
            while (left <= right) {
                sum += nums[left];
                left++;
            }
            return sum;
        }
    }
}
