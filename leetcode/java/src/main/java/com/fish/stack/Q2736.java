package com.fish.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author liuqi
 * @date 2023/11/17
 */
public class Q2736 {
    /**
     * 单调栈+二分查找
     */
    class Solution {
        public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
            int n = nums1.length;
            // 合并两个数组
            int[][] sortedNums = new int[n][2];
            for (int i = 0; i < n; i++) {
                sortedNums[i][0] = nums1[i];
                sortedNums[i][1] = nums2[i];
            }
            // 合并后的数组按照x从大到小排序
            Arrays.sort(sortedNums, (a, b) -> b[0] - a[0]);
            int q = queries.length;
            // 扩充查询数组，讲index信息扩充进去
            int[][] sortedQueries = new int[q][3];
            for (int i = 0; i < q; i++) {
                sortedQueries[i][0] = i;
                sortedQueries[i][1] = queries[i][0];
                sortedQueries[i][2] = queries[i][1];
            }
            // 查询数据按照x从大到小排序
            Arrays.sort(sortedQueries, (a, b) -> b[1] - a[1]);
            List<int[]> stack = new ArrayList<int[]>();
            int[] answer = new int[q];
            Arrays.fill(answer, -1);
            int j = 0;
            for (int[] query : sortedQueries) {
                int i = query[0], x = query[1], y = query[2];
                while (j < n && sortedNums[j][0] >= x) {
                    int[] pair = sortedNums[j];
                    int num1 = pair[0], num2 = pair[1];
                    while (!stack.isEmpty() && stack.get(stack.size() - 1)[1] <= num1 + num2) {
                        stack.remove(stack.size() - 1);
                    }
                    if (stack.isEmpty() || stack.get(stack.size() - 1)[0] < num2) {
                        stack.add(new int[]{num2, num1 + num2});
                    }
                    j++;
                }
                int k = binarySearch(stack, y);
                if (k < stack.size()) {
                    answer[i] = stack.get(k)[1];
                }
            }
            return answer;
        }

        public int binarySearch(List<int[]> list, int target) {
            int low = 0, high = list.size();
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (list.get(mid)[0] >= target) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            return low;
        }
    }


    /**
     * 侥幸，测试用例中包含了太多重复的查询
     */
    class Solution1 {
        public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
            int n = nums1.length;
            Integer[] idxs = new Integer[n];
            HashMap<String, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                idxs[i] = i;
            }
            Arrays.sort(idxs, (i, j) -> (nums1[j] + nums2[j]) - (nums1[i] + nums2[i]));
            int[] ans = new int[queries.length];
            Arrays.fill(ans, -1);
            for (int i = 0; i < queries.length; i++) {
                String key = queries[i][0] + "-" + queries[i][1];
                if (map.containsKey(key)) {
                    ans[i] = map.get(key);
                    continue;
                }
                for (int idx = 0; idx < n; idx++) {
                    int index = idxs[idx];
                    if ((nums1[index] >= queries[i][0]) && (nums2[index] >= queries[i][1])) {
                        ans[i] = nums1[index] + nums2[index];
                        break;
                    }
                }
                map.put(key, ans[i]);
            }
            return ans;
        }
    }
}
