package com.futurebytedance.sort.student;

import java.util.Arrays;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/16 - 19:54
 * @Description 快速排序
 * 一趟快速排序的算法是：
 * 1）设置两个变量i、j，排序开始的时候：i=0，j=N-1；
 * 2）以第一个数组元素作为基准点。
 * 3）从j开始向前搜索，即由后开始向前搜索(j--)，找到第一个小于A[i](此时基准点)的值A[j]，将值与A[j]交换；
 * 4）从i开始向后搜索，即由前开始向后搜索(i++)，找到第一个大于A[j]（此时基准点）的A[i]，将A[j]与A[i]交换；
 * 5）重复第3步
 * 6）重复第3、4、5步，直到i=j； (3,4步中，没找到符合条件的值，即3中A[j]不小于key,4中A[j]不大于key的时候改变j、i的值，使得j=j-1，i=i+1，直至找到为止。找到符合条件的值，进行交换的时候i， j指针位置不变。另外，i==j这一过程一定正好是i+或j-完成的时候，此时令循环结束），到此找到基准点的下标，作为分治下标。
 * 7）重复1-6步骤递归排序前半部分
 * 8）重复1-6步骤递归排序后半部分
 *
 * 第一次:
 *        quickSort(0,5) ==> 1
 *        -576 78 0 23 -576 70
 *        -576 78 0 23 78 70
 *        -576 -9 0 23 78 70
 * 第二次:
 *        quickSort(2,5) ==> 2
 *        -576 -9 0 23 78 70
 * 第三次：
 *       quickSort(3,5) ==> 3
 *       -576 -9 0 23 78 70
 * 第四次:
 *       quickSort(4,5)
 *       -576 -9 0 23 78 78
 *       -576 -9 0 23 70 78
 *
 *
 *
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    //确定每个基准数的位置
    public static int getMiddle(int[] sortArray, int low, int high) {
        int pivot = sortArray[low];
        while (low < high) {
            while (low < high && sortArray[high] >= pivot) {
                high -= 1;
            }
            sortArray[low] = sortArray[high];
            while (low < high && sortArray[low] <= pivot) {
                low += 1;
            }
            sortArray[high] = sortArray[low];
        }
        sortArray[low] = pivot;
        System.out.println(Arrays.toString(sortArray));
        return low;
    }


    public static void quickSort(int[] sortArray, int low, int high) {
        if (low < high) {
            int middle = getMiddle(sortArray, low, high);
            quickSort(sortArray, low, middle - 1);
            quickSort(sortArray, middle + 1, high);
        }
    }
}
