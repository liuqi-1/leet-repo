package com.fish.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author liuqi
 * @date 2023/11/1
 */
public class QuickSort {
    public void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = new Random().nextInt(right - left + 1) + left;
        int pivotValue = arr[pivot];
        arr[pivot] = arr[left];
        int rightT = right;
        int leftT = left;
        while (true) {
            // 从右边找到小于pivotValue的值
            while (rightT > leftT && arr[rightT] >= pivotValue) {
                rightT--;
            }
            if (rightT == leftT) {
                break;
            }
            arr[leftT] = arr[rightT];
            leftT++;
            // 从左边找到大于pivotValue的值
            while (leftT < rightT && arr[leftT] <= pivotValue) {
                leftT++;
            }
            if (leftT == rightT) {
                break;
            }
            arr[rightT] = arr[leftT];
            rightT--;
        }
        arr[leftT] = pivotValue;
        quickSort(arr, left, leftT - 1);
        quickSort(arr, leftT + 1, right);
    }

    @Test
    public void test() {
        int[] array = new int[100];
//        int[] array = new int[]{12, 3, 4, 12, 4, 54, 5};
        for (int i = 0; i < 100; i++) {
            array[i] = new Random().nextInt()%100;
        }
        System.out.println(Arrays.toString(array));
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
}
