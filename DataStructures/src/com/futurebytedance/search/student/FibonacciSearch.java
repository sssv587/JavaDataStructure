package com.futurebytedance.search.student;

import java.util.Arrays;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/19 - 23:52
 * @Description 斐波那契查找算法
 */
public class FibonacciSearch {
    public static final int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234, 1235, 1236};
        int index = fibSearch(arr, 1236);
        System.out.println("index=" + index);
    }

    //返回斐波那契的对应位置的数值
    public static int[] fib() {
        int[] fibs = new int[maxSize];
        fibs[0] = 1;
        fibs[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fibs[i] = fibs[i - 1] + fibs[i - 2];
        }
        return fibs;
    }

    public static int fibSearch(int[] arr, int value) {
        int[] fibs = fib();
        int left = 0;
        int right = arr.length - 1;
        int k = 0;
        //找到k对应的值以及斐波那契数列对应的数组长度
        while (right + 1 > fibs[k]) {
            k++;
        }
        int fibLength = fibs[k];
        //拿到了应该获取长度的数组,并将原数组的元素拷贝进去
        int[] fibArray = Arrays.copyOf(arr, fibLength);
        for (int i = right + 1; i < fibLength; i++) {
            fibArray[i] = arr[right];
        }
        while (left <= right) {
            int mid = left + fibs[k - 1] - 1;
            if (value < fibArray[mid]) {
                right = mid - 1;
                k--;
            } else if (value > fibArray[mid]) {
                left = mid + 1;
                k -= 2;
            } else {
                return Math.min(mid, right);
            }
        }
        return -1;
    }
}
