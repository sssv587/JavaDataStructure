package com.futurebytedance.sort.student;

import java.util.Arrays;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/2 - 1:09
 * @Description 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //堆排序
    public static void heapSort(int[] arr) {
        int temp;

        //构建大根堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            addJustHeap(arr, i, arr.length);
        }

        //将大根堆(数组第0个元素)与最后一个叶子节点交换
        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            addJustHeap(arr, 0, i);
        }

    }

    //调整大根堆
    public static void addJustHeap(int[] arr, int i, int length) {
        int temp = arr[i];

        for (int k = 2 * i + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }

        arr[i] = temp;
    }
}
