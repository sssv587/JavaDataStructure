package com.futurebytedance.binarysearchnorecursion.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/15 - 22:37
 * @Description 二分查找的递归与非递归方式
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 3, 8, 10, 11, 67, 100, 100, 100};
        int index = binarySearchWithRecursion(arr, 0, arr.length - 1, 100);
        System.out.println(index);

        List<Integer> list = binarySearchWithRecursion1(arr, 0, arr.length - 1, 1);
        System.out.println(list);

        List<Integer> list1 = binarySearchWithNoRecursion(arr, 1);
        System.out.println(list1);
    }

    private static List<Integer> binarySearchWithRecursion1(int[] arr, int left, int right, int target) {
        ArrayList<Integer> list = new ArrayList<>();
        if (left < 0 || right > arr.length || target < arr[left] || target > arr[right]) {
            return list;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (target > midVal) {
            return binarySearchWithRecursion1(arr, mid + 1, right, target);
        } else if (target < midVal) {
            return binarySearchWithRecursion1(arr, left, mid - 1, target);
        } else {
            list.add(mid);
            int index = mid - 1;
            while (index >= left && arr[index] == target) {
                list.add(index);
                index--;
            }
            index = mid + 1;
            while (index <= right && arr[index] == target) {
                list.add(index);
                index++;
            }
            Collections.sort(list);
            return list;
        }
    }

    public static int binarySearchWithRecursion(int[] arr, int left, int right, int target) {
        int mid = (left + right) / 2;
        if (left <= right) {
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                return binarySearchWithRecursion(arr, mid + 1, right, target);
            } else if (arr[mid] > target) {
                return binarySearchWithRecursion(arr, left, mid - 1, target);
            }
        }
        return -1;
    }

    public static List<Integer> binarySearchWithNoRecursion(int[] arr, int target) {
        ArrayList<Integer> list = new ArrayList<>();
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                list.add(mid);
                int index = mid - 1;
                while (index >= left && arr[index] == target) {
                    list.add(index);
                    index--;
                }
                index = mid + 1;
                while (index <= right && arr[index] == target) {
                    list.add(index);
                    index++;
                }
                Collections.sort(list);
                return list;
            }
        }
        return list;
    }
}
