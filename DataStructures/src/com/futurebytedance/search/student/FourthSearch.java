package com.futurebytedance.search.student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/4 - 1:49
 * @Description 四种查找算法
 */
public class FourthSearch {
    public static void main(String[] args) {
        //线性查找
        int[] arrSeqSearch = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        List<Integer> indexSeqSearch = seqSearch(arrSeqSearch, 1000);
        List<Integer> indexSeqSearch1 = seqSearch(arrSeqSearch, 8);
        System.out.println("线性查找结果:" + indexSeqSearch);
        System.out.println("线性查找结果:" + indexSeqSearch1);

        //二分查找-递归
        int[] arrBinarySearch = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        List<Integer> indexBinarySearch = binarySearch(arrBinarySearch, 0, arrBinarySearch.length - 1, 1000);
        System.out.println("二分查找递归算法结果:" + indexBinarySearch);

        //二分查找-非递归
        int[] arrBinarySearchNoRecursive = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        List<Integer> indexBinarySearchNoRecursive = BinarySearchNoRecursive(arrBinarySearchNoRecursive, 0, arrBinarySearch.length - 1, 1000);
        List<Integer> indexBinarySearchNoRecursive1 = BinarySearchNoRecursive(arrBinarySearchNoRecursive, 1, arrBinarySearch.length - 1, 1234);
        System.out.println("二分查找非递归算法结果:" + indexBinarySearchNoRecursive);
        System.out.println("二分查找非递归算法结果:" + indexBinarySearchNoRecursive1);

        //插值查找
        int[] arrInsertValueSearch = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        List<Integer> indexInsertValueSearch = insertValueSearch(arrInsertValueSearch, 0, arrBinarySearch.length - 1, 1000);
        System.out.println("差值查找算法结果:" + indexInsertValueSearch);

        //斐波那契查找
        int[] arrFibonacciSearch = {1, 8, 10, 89, 1000, 1234, 1235, 1236};
        int indexFibonacciSearch = fibonacciSearch(arrFibonacciSearch, 1236);
        System.out.println("斐波那契查找算法结果:" + indexFibonacciSearch);
    }

    private static List<Integer> seqSearch(int[] arr, int targetVal) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == targetVal) {
                list.add(i);
                if (arr[i] != targetVal) {
                    break;
                }
            }
        }
        return list;
    }


    //二分查找-递归
    private static List<Integer> binarySearch(int[] arr, int left, int right, int targetVal) {
        List<Integer> list = new ArrayList<>();
        if (left > right || targetVal < arr[0] || targetVal > arr[arr.length - 1]) {
            return list;
        }
        int mid = (left + right) / 2;

        if (arr[mid] > targetVal) {
            right = mid - 1;
            return binarySearch(arr, left, right, targetVal);
        } else if (arr[mid] < targetVal) {
            left = mid + 1;
            return binarySearch(arr, left, right, targetVal);
        } else {
            int index = left;
            while (index < mid && arr[index] == targetVal) {
                list.add(index);
                index++;
            }
            list.add(mid);
            index = mid + 1;
            while (index < right && arr[index] == targetVal) {
                list.add(index);
                index++;
            }
            return list;
        }
    }

    //二分查找-非递归
    private static List<Integer> BinarySearchNoRecursive(int[] arr, int left, int right, int targetVal) {
        List<Integer> list = new ArrayList<>();

        if (left > right || targetVal < arr[0] || targetVal > arr[arr.length - 1]) {
            return list;
        }

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] < targetVal) {
                left = mid + 1;
            } else if (arr[mid] > targetVal) {
                right = mid - 1;
            } else {
                int index = left;
                while (index < mid && arr[index] == targetVal) {
                    list.add(index);
                    index++;
                }
                list.add(mid);
                index = mid + 1;
                while (index < right && arr[index] == targetVal) {
                    list.add(index);
                    index++;
                }
                break;
            }
        }
        return list;
    }

    //差值查找算法
    public static List<Integer> insertValueSearch(int[] arr, int left, int right, int findVal) {
        List<Integer> list = new ArrayList<>();

        if (left > right || findVal < arr[left] || findVal > arr[arr.length - 1]) {
            return list;
        }

        int mid = left + (findVal - arr[left]) / (arr[right] - arr[left]) * (right - left);
        if (arr[mid] > findVal) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else if (arr[mid] < findVal) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else {
            int index = left;
            while (index < mid && arr[index] == findVal) {
                list.add(index);
                index++;
            }
            list.add(mid);
            index = mid + 1;
            while (index < right && arr[index] == findVal) {
                list.add(index);
                index++;
            }
            return list;
        }
    }

    //斐波那契查找算法
    private static int[] fib() {
        int[] fib = new int[20];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < 20; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    public static int fibonacciSearch(int[] arr, int findVal) {
        int[] fib = fib();
        int left = 0;
        int right = arr.length - 1;
        int k = 0;
        while (fib[k] < right + 1) {
            k++;
        }
        int[] temp = Arrays.copyOf(arr, fib[k]);

        for (int i = right + 1; i < fib[k]; i++) {
            temp[i] = arr[right];
        }

        while (left <= right) {
            int mid = left + fib[k - 1] - 1;
            if (findVal > temp[mid]) {
                left = mid + 1;
                k -= 2;
            } else if (findVal < temp[mid]) {
                right = mid - 1;
                k -= 1;
            } else {
                return Math.min(mid, right);
            }
        }

        return -1;
    }
}
