package com.futurebytedance.sort.student;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/17 - 20:51
 * @Description 包含负数的基数排序
 */
public class RadixSortContainsMinus {
    public static void main(String[] args) {
        int[] arr = {53, -3, 542, 748, 14, -214};
        radixMergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixMergeSort(int[] arr) {
        ArrayList<Integer> listAboveZero = new ArrayList<>();
        ArrayList<Integer> listBelowZero = new ArrayList<>();

        for (int ele : arr) {
            if (ele >= 0) {
                listAboveZero.add(ele);
            } else {
                listBelowZero.add(ele);
            }
        }
        int index1 = 0;
        int index2 = 0;
        int[] arrAboveZero = new int[listAboveZero.size()];
        int[] arrBelowZero = new int[listBelowZero.size()];
        for (Integer ele : listAboveZero) {
            arrAboveZero[index1] = ele;
            index1++;
        }
        for (Integer ele : listBelowZero) {
            arrBelowZero[index2] = ele;
            index2++;
        }

        int max = arrAboveZero[0];
        for (int ele : arrAboveZero) {
            if (max < ele) {
                max = ele;
            }
        }
        int maxLength = (max + "").length();

        //定义一个桶
        int[][] bucket = new int[20][arr.length];
        //定义一个对应各桶位置的数组，用于记录桶元素上的个数
        int[] perElementBucket = new int[20];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int k : arrAboveZero) {
                int lastIndex = k / n % 10;
                bucket[lastIndex][perElementBucket[lastIndex]] = k;
                perElementBucket[lastIndex]++;
            }
            for (int k : arrBelowZero) {
                k = Math.abs(k);
                int lastIndex = k / n % 10 + 10;
                bucket[lastIndex][perElementBucket[lastIndex]] = k;
                perElementBucket[lastIndex]++;
            }

            int index = 0;
            for (int j = perElementBucket.length - 1; j >= perElementBucket.length / 2; j--) {
                if (perElementBucket[j] > 0) {
                    for (int k = 0; k < perElementBucket[j]; k++) {
                        arrBelowZero[index] = -bucket[j][k];
                        index++;
                    }
                }
                perElementBucket[j] = 0;
            }
            index = 0;
            for (int j = 0; j < perElementBucket.length / 2; j++) {
                if (perElementBucket[j] > 0) {
                    for (int k = 0; k < perElementBucket[j]; k++) {
                        arrAboveZero[index] = bucket[j][k];
                        index++;
                    }
                }
                perElementBucket[j] = 0;
            }
        }
        int arrIndex = 0;
        //将正数、负数数组还原成arr
        for (int ele : arrBelowZero) {
            arr[arrIndex] = ele;
            arrIndex++;
        }
        for (int ele : arrAboveZero) {
            arr[arrIndex] = ele;
            arrIndex++;
        }
    }
}
