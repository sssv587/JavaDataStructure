package com.futurebytedance.sort.student;

import java.util.Arrays;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/15 - 17:41
 * @Description 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort1(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr1 = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort2(arr1);
        System.out.println(Arrays.toString(arr1));
    }

    /*
    只能从排序方法上推导解决此问题，不能直接上代码，从第一次循环开始推导
     */
    public static void shellSort1(int[] arr) {
        int temp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j >= gap; j -= gap) {
                    if (arr[j] < arr[j - gap]) {
                        temp = arr[j];
                        arr[j] = arr[j - gap];
                        arr[j - gap] = temp;
                    }
                }
            }
        }

        //第1次循环,分5组
//        for (int i = 5; i < arr.length; i++) {
//            for (int j = i; j >= 5; j -= 5) {
//                if (arr[j] < arr[j - 5]) {
//                    temp = arr[j - 5];
//                    arr[j - 5] = arr[j];
//                    arr[j] = temp;
//                }
//            }
//        }
    }

    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int index = i;
                int indexVal = arr[i];
                while (index > gap - 1 && indexVal < arr[index - gap]) {
                    arr[index] = arr[index - gap];
                    index -= gap;
                }
                if (indexVal != arr[index]) {
                    arr[index] = indexVal;
                }
            }
        }
    }
}
