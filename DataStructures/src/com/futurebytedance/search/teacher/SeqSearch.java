package com.futurebytedance.search.teacher;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/18 - 19:32
 * @Description 线性(顺序)查找算法
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};//没有顺序的数组
        int index = seqSearch(arr, 1);
        if (index == -1) {
            System.out.println("没有查找到");
        } else {
            System.out.println("找到了，下标为=" + index);
        }
    }

    /**
     * 这里我们实现的线性查找是找到一个满足条件的值，就返回
     * @param arr 要查找的数组
     * @param value 要查找的值
     * @return 下标值
     */
    public static int seqSearch(int[] arr, int value) {
        //线性查找是逐一比对，发现有相同的值时，就返回下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}