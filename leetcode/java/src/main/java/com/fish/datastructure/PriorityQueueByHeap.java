package com.fish.datastructure;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 使用堆实现有限队列
 */
public class PriorityQueueByHeap<T extends Comparable> {
    private T[] data;// 数组的第一个元素不需要，用于占位
    int heapSize;
    int length;

    public PriorityQueueByHeap(int length) {
        data = (T[]) new Comparable[length + 1];
        this.length = length;
    }

    /**
     * 构造函数，默认容量为10
     */
    public PriorityQueueByHeap() {
        this(5);
    }


    private void grow() {
        T[] newData = (T[]) new Comparable[length << 1 + 1];
        System.arraycopy(data, 0, newData, 0, length + 1);
        length <<= 1;
        data = newData;
    }

    public void add(T t) {
        heapSize++;
        if (heapSize > length) {
            grow();
        }
        data[heapSize] = t;
        int idx = heapSize;
        while (idx > 1 && data[parent(idx)].compareTo(data[idx]) < 0) {
            swap(idx, parent(idx));
            idx = parent(idx);
        }
    }

    public T peek() {
        if (heapSize == 0) {
            throw new RuntimeException("queue is empty");
        }
        return data[1];
    }

    public T poll() {
        if (heapSize == 0) {
            throw new RuntimeException("queue is empty");
        }
        T res = data[1];
        data[1] = data[heapSize];
        heapSize--;
        maxHeapify(1);
        return res;
    }


    /**
     * 将以下标i为根结点的子树重新调整为最大堆
     */
    private void maxHeapify(int i) {
        int largestIdx = i;
        int l = left(i);
        int r = right(i);
        if (l <= heapSize && data[l].compareTo(data[i]) > 0) {
            largestIdx = l;
        }
        if (r <= heapSize && data[r].compareTo(data[largestIdx]) > 0) {
            largestIdx = r;
        }
        if (largestIdx != i) {
            swap(i, largestIdx);
            maxHeapify(largestIdx);
        }
    }

    private int parent(int i) {
        return i / 2;
    }

    private int left(int i) {
        return 2 * i;
    }

    private int right(int i) {
        return 2 * i + 1;
    }

    private void swap(int idx1, int idx2) {
        T temp = data[idx1];
        data[idx1] = data[idx2];
        data[idx2] = temp;
    }

    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());
        PriorityQueueByHeap queue = new PriorityQueueByHeap();
        for (int i = 0; i < 10; i++) {
            queue.add(random.nextInt());
        }
        try {
            while (true) {
                System.out.println(queue.poll());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
