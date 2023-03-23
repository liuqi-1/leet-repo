package com.fish.datastructure;

import java.util.Arrays;

/**
 * @author liuqi
 * @date 2023/11/13
 * <p>
 * 建立线段树，插入线段树，更新线段树（线段树某一区间内所有值全部加上相同的变化量，懒惰标记更新方法）
 * <p>
 * 如果不是将某区间内所有元素加上相同变化量，而是区间内的所有元素全部改成相同的值，
 * 则需要额外的v数组，用来进行懒惰标记，在v[i]为1的情况下，b[i]才有意义。
 * 因为无法通过b[i]是否为零来判别是否进行了标记。
 */
public class SegmentTree1 {
    int[] a;// 下标从0开始，线段树区间也是从0开始
    int[] d; // 下标从1开始，下标0无用
    int[] b;// 下标从1开始，表示对应节点的懒惰标记
    int n;

    public SegmentTree1(int[] a) {
        this.a = a;
        this.n = a.length;
        // 用对数换底公式初始化d数组
        this.d = new int[(int) (Math.pow(2, Math.ceil(Math.log(9) / Math.log(2)) + 1))];
        this.b = new int[d.length];
        build(0, n - 1, 1);
    }


    /**
     * @param l 查询区间左端点
     * @param r 查询区间右端点
     * @param s 当前区间左端点
     * @param t 当前区间右端点
     * @param p 当前区间索引
     * @return
     */
    public int getSum(int l, int r, int s, int t, int p) {
        if (l <= s && t <= r) {
            return d[p];
        }
        int m = s + ((t - s) >> 1);
        if (b[p] != 0) {// 含有懒惰标记，需要更新到子节点
            d[2 * p] += b[p] * (m - s + 1);
            b[2 * p] += b[p];
            d[2 * p + 1] += b[p] * (t - m);
            b[2 * p + 1] += b[p];
            b[p] = 0;
        }
        int sum = 0;
        if (l <= m) {
            sum += getSum(l, r, s, m, p * 2);
        }
        if (r > m) {
            sum += getSum(l, r, m + 1, t, p * 2 + 1);
        }
        return sum;
    }

    /**
     * [l,r]更新区间
     * c 变化量
     * [s,t]管辖区间
     * p 节点编号
     */
    public void update(int l, int r, int c, int s, int t, int p) {
        if (l <= s && t <= r) {
            d[p] += (t - s + 1) * c;
            b[p] = c;
            return;
        }
        int m = s + ((t - s) >> 1);
        if (b[p] != 0) {
            d[2 * p] += b[p] * (m - s + 1);
            b[2 * p] += b[p];
            d[2 * p + 1] += b[p] * (t - m);
            b[2 * p + 1] += b[p];
            b[p] = 0;
        }
        if (l <= m) {
            update(l, r, c, s, m, 2 * p);
        }
        if (r > m) {
            update(l, r, c, m + 1, t, 2 * p + 1);
        }
        d[p] = d[2 * p] + d[2 * p + 1];
    }

    /**
     * 建立线段树
     *
     * @param s 区间左端点
     * @param t 区间右端点
     * @param p 节点编号
     */
    void build(int s, int t, int p) {
        // 叶子节点，区间长度为1
        if (s == t) {
            d[p] = a[s];
            return;
        }
        // 计算区间中点
        int m = s + ((t - s) >> 1);
        // 递归创建左右子区间
        build(s, m, 2 * p);
        build(m + 1, t, 2 * p + 1);
        // 更新当前区间的值
        d[p] = d[2 * p] + d[2 * p + 1];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        SegmentTree1 tree = new SegmentTree1(arr);
        int sum = tree.getSum(0, 8, 0, arr.length - 1, 1);
        System.out.println(Arrays.toString(arr));
        System.out.println(sum);

        tree.update(4, 5, 1, 0, 8, 1);

        sum = tree.getSum(0, 8, 0, arr.length - 1, 1);
        System.out.println(Arrays.toString(arr));
        System.out.println(sum);
    }
}
