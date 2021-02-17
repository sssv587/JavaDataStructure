package com.futurebytedance.sort.student;

import java.util.Arrays;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/17 - 20:49
 * @Description 正整数的基数排序
 */
public class RadixSortBeyondZero {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //实现正数的基数排序
    public static void radixSort(int[] arr) {
        int max = 0;

        for (int ele : arr) {
            if (ele > max) {
                max = ele;
            }
        }

        int maxLength = (max + "").length();

        //定义一个二维数组作为桶
        int[][] bucket = new int[10][arr.length];

        //定义一个一维数组作为记录每个桶元素的个数
        int[] perBucketElementCount = new int[10];

        //实现基数排序
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int k : arr) {
                int lastElement = k / n % 10;
                bucket[lastElement][perBucketElementCount[lastElement]] = k;
                perBucketElementCount[lastElement]++;
            }

            //取出bucket中的数据还原至arr中
            int index = 0;
            for (int j = 0; j < perBucketElementCount.length; j++) {
                if (perBucketElementCount[j] > 0) {
                    for (int k = 0; k < perBucketElementCount[j]; k++) {
                        arr[index] = bucket[j][k];
                        index++;
                    }
                    perBucketElementCount[j] = 0;
                }
            }
        }

    }
}
