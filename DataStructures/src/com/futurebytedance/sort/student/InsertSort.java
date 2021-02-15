package com.futurebytedance.sort.student;

import java.util.Arrays;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/15 - 17:31
 * @Description 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, -1, 89};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /*
    外层循环空的是开始比较的元素 arr.length-1 但是要从第一个元素开始比较---> 第0个元素不需要比较
    初始化两个值，一个记录要放置元素的现有角标，一个记录要放置元素的值
    放置元素时默认前面的小数组是有序的，所以只需要找到插入的位置即可 ---> while循环
    最后判断需不需要插入以及插入到哪个位置
     */
    public static void insertSort(int[] arr) {
        int index;
        int indexVal;
        for (int i = 1; i < arr.length; i++) {
            index = i;
            indexVal = arr[i];
            while (index > 0 && indexVal < arr[index - 1]) {
                arr[index] = arr[index - 1];
                index--;
            }
            if (indexVal != arr[index]) {
                arr[index] = indexVal;
            }
        }
    }
}
