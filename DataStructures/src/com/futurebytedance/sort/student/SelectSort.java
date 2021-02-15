package com.futurebytedance.sort.student;

import java.util.Arrays;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/14 - 21:08
 * @Description 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[]{-2, 1, 0, -3, 20, 18};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectSort(int[] arr) {
        //选择排序
        //外层循环控制要放置的位置
        //内层循环控制寻找从i+1开始的最小值
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (arr[i] != min) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
