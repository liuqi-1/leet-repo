package com.fish.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author liuqi
 * @date 2023/6/19
 * <p>
 * 1262. 可被三整除的最大和
 * 官方题解： https://leetcode.cn/problems/greatest-sum-divisible-by-three/solutions/2309835/ke-bei-san-zheng-chu-de-zui-da-he-by-lee-cvzo/
 */
public class Q1262 {
    /**
     * 贪心+正向思维;
     */
    class Solution {
        public int maxSumDivThree(int[] nums) {
            // 使用 v[0], v[1], v[2] 分别表示 a, b, c
            List<Integer>[] v = new List[3];
            for (int i = 0; i < 3; ++i) {
                v[i] = new ArrayList<Integer>();
            }
            for (int num : nums) {
                v[num % 3].add(num);
            }
            Collections.sort(v[1], (a, b) -> b - a);
            Collections.sort(v[2], (a, b) -> b - a);

            int ans = 0;
            int lb = v[1].size(), lc = v[2].size();
            for (int cntb = lb - 2; cntb <= lb; ++cntb) {
                if (cntb >= 0) {
                    for (int cntc = lc - 2; cntc <= lc; ++cntc) {
                        if (cntc >= 0 && (cntb - cntc) % 3 == 0) {
                            ans = Math.max(ans, getSum(v[1], 0, cntb) + getSum(v[2], 0, cntc));
                        }
                    }
                }
            }
            return ans + getSum(v[0], 0, v[0].size());
        }

        public int getSum(List<Integer> list, int start, int end) {
            int sum = 0;
            for (int i = start; i < end; ++i) {
                sum += list.get(i);
            }
            return sum;
        }
    }

//    作者：力扣官方题解
//    链接：https://leetcode.cn/problems/greatest-sum-divisible-by-three/solutions/2309835/ke-bei-san-zheng-chu-de-zui-da-he-by-lee-cvzo/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    /**
     * 贪心+逆向思维
     */
    class Solution1 {
        public int maxSumDivThree(int[] nums) {
            // 使用 v[0], v[1], v[2] 分别表示 a, b, c
            List<Integer>[] v = new List[3];
            for (int i = 0; i < 3; ++i) {
                v[i] = new ArrayList<Integer>();
            }
            for (int num : nums) {
                v[num % 3].add(num);
            }
            Collections.sort(v[1], (a, b) -> b - a);
            Collections.sort(v[2], (a, b) -> b - a);

            int tot = Arrays.stream(nums).sum();
            int remove = Integer.MAX_VALUE;

            if (tot % 3 == 0) {
                remove = 0;
            } else if (tot % 3 == 1) {
                if (v[1].size() >= 1) {
                    remove = Math.min(remove, v[1].get(v[1].size() - 1));
                }
                if (v[2].size() >= 2) {
                    remove = Math.min(remove, v[2].get(v[2].size() - 2) + v[2].get(v[2].size() - 1));
                }
            } else {
                if (v[1].size() >= 2) {
                    remove = Math.min(remove, v[1].get(v[1].size() - 2) + v[1].get(v[1].size() - 1));
                }
                if (v[2].size() >= 1) {
                    remove = Math.min(remove, v[2].get(v[2].size() - 1));
                }
            }

            return tot - remove;
        }
    }
//
//    作者：力扣官方题解
//    链接：https://leetcode.cn/problems/greatest-sum-divisible-by-three/solutions/2309835/ke-bei-san-zheng-chu-de-zui-da-he-by-lee-cvzo/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


    /**
     * DP
     */
    class Solution2 {
        public int maxSumDivThree(int[] nums) {
            int[] f = {0, Integer.MIN_VALUE, Integer.MIN_VALUE};
            for (int num : nums) {
                int[] g = new int[3];
                System.arraycopy(f, 0, g, 0, 3);
                for (int i = 0; i < 3; ++i) {
                    g[(i + num % 3) % 3] = Math.max(g[(i + num % 3) % 3], f[i] + num);
                }
                f = g;
            }
            return f[0];
        }
    }
}
