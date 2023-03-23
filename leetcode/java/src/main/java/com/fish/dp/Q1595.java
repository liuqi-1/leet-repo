package com.fish.dp;

import java.util.Arrays;
import java.util.List;

/**
 * @author liuqi
 * @date 2023/6/20
 * <p>
 * https://leetcode.cn/problems/minimum-cost-to-connect-two-groups-of-points/
 */
public class Q1595 {

    class Solution {
        public int connectTwoGroups(List<List<Integer>> cost) {
            int size1 = cost.size(), size2 = cost.get(0).size(), m = 1 << size2;
            int[] dp1 = new int[m];
            Arrays.fill(dp1, Integer.MAX_VALUE / 2);
            int[] dp2 = new int[m];
            dp1[0] = 0;
            for (int i = 1; i <= size1; i++) {
                for (int s = 0; s < m; s++) {
                    dp2[s] = Integer.MAX_VALUE / 2;
                    for (int k = 0; k < size2; k++) {
                        if ((s & (1 << k)) == 0) {
                            continue;
                        }
                        dp2[s] = Math.min(dp2[s], dp2[s ^ (1 << k)] + cost.get(i - 1).get(k));
                        dp2[s] = Math.min(dp2[s], dp1[s] + cost.get(i - 1).get(k));
                        dp2[s] = Math.min(dp2[s], dp1[s ^ (1 << k)] + cost.get(i - 1).get(k));
                    }
                }
                System.arraycopy(dp2, 0, dp1, 0, m);
            }
            return dp1[m - 1];
        }
    }

// 作者：力扣官方题解
// 链接：https://leetcode.cn/problems/minimum-cost-to-connect-two-groups-of-points/solutions/2311504/lian-tong-liang-zu-dian-de-zui-xiao-chen-6qoj/
// 来源：力扣（LeetCode）
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

}
