package com.futurebytedance.sort.student;

import java.util.Arrays;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/4 - 1:15
 * @Description 八种排序综合
 */
public class EightSort {
    public static void main(String[] args) {
        //冒泡排序
        int[] arrBubbleSort = {4, 6, 8, 5, 9, 1, 2, 3};
        bubbleSort(arrBubbleSort);
        System.out.println("冒泡排序=>" + Arrays.toString(arrBubbleSort));

        //选择排序
        int[] arrChooseSort = {4, 6, 8, 5, 9, 1, 2, 3};
        ChooseSort(arrChooseSort);
        System.out.println("选择排序=>" + Arrays.toString(arrChooseSort));

        //插入排序-方法一
        int[] arrInsertSort = {4, 6, 8, 5, 9, 1, 2, 3};
        insertSort(arrInsertSort);
        System.out.println("插入排序=>" + Arrays.toString(arrInsertSort));

        //插入排序-方法二
        int[] arrInsertSort1 = {4, 6, 8, 5, 9, 1, 2, 3};
        insertSort1(arrInsertSort1);
        System.out.println("插入排序=>" + Arrays.toString(arrInsertSort1));

        //希尔排序
        int[] arrShellSort = {4, 6, 8, 5, 9, 1, 2, 3};
        shellSort(arrShellSort);
        System.out.println("希尔排序=>" + Arrays.toString(arrShellSort));

        int[] quickSort = {4, 6, 8, 5, 9, 1, 2, 3};
        quickSort(quickSort, 0, quickSort.length - 1);
        System.out.println("快速排序=>" + Arrays.toString(quickSort));

        //归并排序
        int[] arrMergeSort = {4, 6, 8, 5, 9, 1, 2, 3};
        int[] temp = new int[arrMergeSort.length];
        mergeSort(arrMergeSort, 0, arrMergeSort.length - 1, temp);
        System.out.println("归并排序=>" + Arrays.toString(arrMergeSort));

        //堆排序
        int[] arrHeapSort = {4, 6, 8, 5, 9, 1, 2, 3};
        headSort(arrHeapSort);
        System.out.println("堆排序=>" + Arrays.toString(arrHeapSort));

        //基数排序
        int[] arrRadixSort = {4, 6, 8, 5, 9, 1, 2, 3};
        radixSort(arrRadixSort);
        System.out.println("桶排序=>" + Arrays.toString(arrRadixSort));
    }

    //冒泡排序
    public static void bubbleSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    //选择排序
    public static void ChooseSort(int[] arr) {
        int temp;
        int index;
        for (int i = 0; i < arr.length - 1; i++) {
            temp = arr[i];
            index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (temp > arr[j]) {
                    temp = arr[j];
                    index = j;
                }
            }
            if (arr[i] != temp) {
                arr[index] = arr[i];
                arr[i] = temp;
            }
        }
    }

    //插入排序-方法一
    public static void insertSort(int[] arr) {
        int index;
        for (int i = 1; i < arr.length; i++) {
            int j = 0;
            int temp = arr[i];
            while (j < i) {
                if (arr[i] < arr[j]) {
                    break;
                }
                j++;
            }
            if (j != i) {
                index = i;
                while (index - j > 0) {
                    arr[index] = arr[index - 1];
                    index--;
                }
                arr[j] = temp;
            }

        }
    }

    public static void insertSort1(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int index = i;
            int indexVal = arr[i];
            while (index > 0 && indexVal < arr[index - 1]) {
                arr[index] = arr[index - 1];
                index--;
            }
            if (arr[i] != indexVal) {
                arr[index] = indexVal;
            }
        }
    }

    //希尔排序
    public static void shellSort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int j = gap; j < arr.length; j++) {
                int index = j;
                int indexVal = arr[j];
                while (index > gap - 1 && indexVal < arr[index - gap]) {
                    arr[index] = arr[index - gap];
                    index -= gap;
                }
                if (indexVal != arr[j]) {
                    arr[index] = indexVal;
                }
            }
        }
    }

    //快速排序
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = getMiddle(arr, left, right);
            quickSort(arr, left, mid - 1);
            quickSort(arr, mid + 1, right);
        }
    }

    public static int getMiddle(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= pivot) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }

    //归并排序-分治
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }


    //归并排序-合并
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {

        int i = left;
        int j = mid + 1;
        int index = 0;


        //先排序并拷贝
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[index] = arr[i];
                i++;
            } else {
                temp[index] = arr[j];
                j++;
            }
            index++;
        }

        //继续拷贝剩余元素
        while (i <= mid) {
            temp[index] = arr[i];
            i++;
            index++;
        }
        while (j <= right) {
            temp[index] = arr[j];
            j++;
            index++;
        }

        int p = 0;
        //还原到原数组中
        while (left <= right) {
            arr[left] = temp[p];
            left++;
            p++;
        }
    }

    //堆排序
    public static void headSort(int[] arr) {
        int length = arr.length;
        int temp;
        //首先调整根堆
        for (int i = length / 2 - 1; i >= 0; i--) {
            addJustHeap(arr, i, length);
        }
        for (int i = length - 1; i > 0; i--) {
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            addJustHeap(arr, 0, i);
        }
    }

    //堆排序-调整根堆
    public static void addJustHeap(int[] arr, int start, int length) {
        int temp = arr[start];

        for (int i = 2 * start + 1; i < length; i = 2 * i + 1) {
            if (i + 1 < length && arr[i] < arr[i + 1]) {
                i++;
            }
            if (arr[i] > temp) {
                arr[start] = arr[i];
                start = i;
            } else {
                break;
            }
        }
        arr[start] = temp;
    }

    //桶排序
    private static void radixSort(int[] arr) {
        int maxVal = 0;
        for (int ele : arr) {
            if (maxVal < ele) {
                maxVal = ele;
            }
        }
        int maxLength = String.valueOf(maxVal).length();

        int[][] bucket = new int[10][arr.length];
        int[] bucketElementLength = new int[10];

        for (int n = 1, i = 0; i < maxLength; n *= 10, i++) {
            for (int ele : arr) {
                int lastNumber = ele / n % 10;
                bucket[lastNumber][bucketElementLength[lastNumber]] = ele;
                bucketElementLength[lastNumber]++;
            }

            int index = 0;

            for (int j = 0; j < bucketElementLength.length; j++) {
                if (bucketElementLength[j] > 0) {
                    for (int k = 0; k < bucketElementLength[j]; k++) {
                        arr[index] = bucket[j][k];
                        index++;
                    }
                }
                bucketElementLength[j] = 0;
            }
        }
    }
}
