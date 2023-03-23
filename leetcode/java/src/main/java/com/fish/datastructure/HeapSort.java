package com.fish.datastructure;

import java.util.Arrays;
import java.util.Random;

/**
 * 堆排序算法
 * 从小到大排列用大顶堆
 */
public class HeapSort<T extends Comparable> {
    public static final HeapSort INSTANCE = new HeapSort();

    private HeapSort() {

    }

    /**
     * 建立最大堆
     */
    private void buildMaxHeap(T[] data) {
        int heapSize = data.length;
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            maxHeapify(i, heapSize, data);
        }
    }

    /**
     * 让以下标i为根结点的子树变成一个最大堆
     *
     * @param i
     */
    private void maxHeapify(int i, int heapSize, T[] data) {
        int l = left(i);
        int r = right(i);
        int largestIdx = i;
        if (l + 1 <= heapSize && data[l].compareTo(data[i]) > 0) {
            largestIdx = l;
        }
        if (r + 1 <= heapSize && data[r].compareTo(data[largestIdx]) > 0) {
            largestIdx = r;
        }
        if (largestIdx != i) {
            swap(i, largestIdx, data);
            maxHeapify(largestIdx, heapSize, data);
        }
    }

    public T[] sort(T[] data) {
        int heapSize = data.length;
        buildMaxHeap(data);
        for (int i = data.length - 1; i >= 1; i--) {
            swap(0, i, data);
            heapSize--;
            maxHeapify(0, heapSize, data);
        }
        return data;
    }

    /**
     * 如果下标从1开始，则parent(i) = i/2
     *
     * @param i
     * @return
     */
    private int parent(int i) {
        return (i + 1) / 2 - 1;
    }

    /**
     * 如果下标从1开始，right(i)=2*i+1
     *
     * @param i
     * @return
     */
    private int right(int i) {
        return 2 * (i + 1);
    }

    /**
     * 如果下标从1开始，则Left(i)=2*i
     *
     * @param i
     * @return
     */
    private int left(int i) {
        return 2 * i + 1;
    }

    private void swap(int idx1, int idx2, T[] data) {
        T temp = data[idx1];
        data[idx1] = data[idx2];
        data[idx2] = temp;
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{123, 41, -213, 412, 1, 1241, 23};
        Integer[] data1 = new Integer[]{23, 1};
        HeapSort.INSTANCE.sort(data);
        HeapSort.INSTANCE.sort(data1);
        System.out.println(Arrays.toString(data));
        System.out.println(Arrays.toString(data1));
    }
}
