package com.futurebytedance.sort.student;

import java.util.Arrays;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/16 - 19:30
 * @Description 归并排序
 * <p>
 * left=0 mid=0 right=1
 * [4, 8, 5, 7, 1, 3, 6, 2]
 * [4, 8, 0, 0, 0, 0, 0, 0]
 * left=2 mid=2 right=3
 * [4, 8, 5, 7, 1, 3, 6, 2]
 * [5, 7, 0, 0, 0, 0, 0, 0]
 * left=0 mid=1 right=3
 * [4, 5, 7, 8, 1, 3, 6, 2]
 * [4, 5, 7, 8, 0, 0, 0, 0]
 * left=4 mid=4 right=5
 * [4, 5, 7, 8, 1, 3, 6, 2]
 * [1, 3, 7, 8, 0, 0, 0, 0]
 * left=6 mid=6 right=7
 * [4, 5, 7, 8, 1, 3, 2, 6]
 * [2, 6, 7, 8, 0, 0, 0, 0]
 * left=4 mid=5 right=7
 * [4, 5, 7, 8, 1, 2, 3, 6]
 * [1, 2, 3, 6, 0, 0, 0, 0]
 * left=0 mid=3 right=7
 * [1, 2, 3, 4, 5, 6, 7, 8]
 * [1, 2, 3, 4, 5, 6, 7, 8]
 * 归并排序后:[1, 2, 3, 4, 5, 6, 7, 8]
 *
 * 完整结果
 * mergeSort(arr,0,3,temp) -> mergeSort(arr,0,1,temp) -> mergeSort(arr,0,0,temp)
 *                                                       mergeSort(arr,1,1,temp)
 *                                                       merge(arr,0,0,1,temp)
 *                         -> mergeSort(arr,2,3,temp) -> mergeSort(arr,2,2,temp)
 *                                                       mergeSort(arr,3,3,temp)
 *                                                       merge(arr,2,2,3,temp)
 *                         -> merge(arr,0,1,3,temp)
 *
 * mergeSort(arr,4,7,temp) -> mergeSort(arr,4,5,temp) -> mergeSort(arr,4,4,temp)
 *                                                       mergeSort(arr,5,5,temp)
 *                                                       merge(arr,4,4,5,temp)
 *                         -> mergeSort(arr,6,7,temp) -> mergeSort(arr,6,6,temp)
 *                                                       mergeSort(arr,7,7,temp)
 *                                                       merge(arr,6,6,7,temp)
 *                         -> merge(arr,4,5,7,temp)
 * merge(arr,0,3,7,temp)
 *
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }

    //分治
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    //合并
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        //将数组元素赋值给temp,直到其中一个子数组赋值完为止
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        //将其中一个没有赋值完的数组继续赋值
        while (i <= mid) {
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        //将temp数组赋值到arr中
        t = 0;
        int leftIndex = left;
        while (leftIndex <= right) {
            arr[leftIndex] = temp[t];
            t += 1;
            leftIndex += 1;
        }
    }
}
