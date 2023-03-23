package com.fish.tree;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author liuqi
 * @date 2023/6/12
 * <p>
 * 给你一棵树，树上有 n 个节点，按从 0 到 n-1 编号。树以父节点数组的形式给出，其中 parent[i] 是节点 i 的父节点。树的根节点是编号为 0 的节点。
 * <p>
 * 树节点的第 k 个祖先节点是从该节点到根节点路径上的第 k 个节点。
 * <p>
 * 实现 TreeAncestor 类：
 * <p>
 * TreeAncestor（int n， int[] parent） 对树和父数组中的节点数初始化对象。
 * getKthAncestor(int node, int k) 返回节点 node 的第 k 个祖先节点。如果不存在这样的祖先节点，返回 -1 。
 */
public class Q1483 {
    class TreeAncestor {
        static final int LOG = 16;
        int[][] ancestors;

        public TreeAncestor(int n, int[] parent) {
            ancestors = new int[n][LOG];
            for (int i = 0; i < n; i++) {
                Arrays.fill(ancestors[i], -1);
            }
            for (int i = 0; i < n; i++) {
                ancestors[i][0] = parent[i];
            }
            for (int j = 1; j < LOG; j++) {
                for (int i = 0; i < n; i++) {
                    if (ancestors[i][j - 1] != -1) {
                        ancestors[i][j] = ancestors[ancestors[i][j - 1]][j - 1];
                    }
                }
            }
        }

        public int getKthAncestor(int node, int k) {
            for (int j = 0; j < LOG; j++) {
                if (((k >> j) & 1) != 0) {
                    node = ancestors[node][j];
                    if (node == -1) {
                        return -1;
                    }
                }
            }
            return node;
        }
    }

//    作者：力扣官方题解
//    链接：https://leetcode.cn/problems/kth-ancestor-of-a-tree-node/solutions/2301511/shu-jie-dian-de-di-k-ge-zu-xian-by-leetc-hdxd/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    /**
     * 会超时
     */
    class TreeAncestor1 {
        int n = 0;
        int[] parent;
        HashMap<int[], Integer> map = new HashMap<>();

        public TreeAncestor1(int n, int[] parent) {
            this.n = n;
            this.parent = parent;
        }

        public int getKthAncestor(int node, int k) {
            int[] pair = new int[]{node, k};
            if (map.containsKey(pair)) {
                return map.get(pair);
            }
            int result = node;
            for (int i = 0; i < k; i++) {
                if (result == -1) {
                    map.put(pair, -1);
                    return -1;
                }
                result = parent[result];
                map.put(pair, result);
            }
            map.put(pair, result);
            return result;
        }
    }

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */
}
