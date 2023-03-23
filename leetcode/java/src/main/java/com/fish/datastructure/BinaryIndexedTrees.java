package com.fish.datastructure;


import org.junit.jupiter.api.Test;

/**
 * @author liuqi
 * @date 2023/11/13
 * <p>
 * 树状数组
 */
public class BinaryIndexedTrees {
    public int[] c; // 树状数组
    public int[] a; // 原数组
    int n;//数组长度

    public BinaryIndexedTrees(int[] a) {
        this.a = a;
        this.n = a.length;
        this.c = new int[n + 1];
        build();
    }

    /**
     * 构建树状数组
     */
    public void build() {
        for (int i = 0; i < n; i++) {
            add(i + 1, a[i]);
        }
    }

    /**
     * 单点更新
     *
     * @param index
     * @param val
     */
    public void add(int index, int val) {
        while (index <= n) {
            c[index] += val;
            index += lowbit(index);
        }
    }


    /**
     * 找到x二进制表示中，最低位的1以及后面所有0组成的数
     *
     * @param x
     * @return
     */
    public int lowbit(int x) {
        return x & -x;
    }

    /**
     * 前x个元素的前缀和
     *
     * @param x>=1
     * @return
     */
    public int getSum(int x) {
        int ans = 0;
        while (x > 0) {
            ans = ans + c[x];
            x = x - lowbit(x);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        BinaryIndexedTrees tree = new BinaryIndexedTrees(arr);
        System.out.println(tree.getSum(5));
        System.out.println(tree.getSum(8));
        tree.add(3, -1);
        System.out.println(tree.getSum(5));
    }
}
