package com.futurebytedance.search.student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/18 - 21:41
 * @Description 二分查找算法
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        List<Integer> list = binarySearch(arr, 0, arr.length - 1, 1000);
        System.out.println(list);
    }

    public static List<Integer> binarySearch(int[] arr, int left, int right, int findVal) {
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            List<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            while (temp >= left && arr[temp] == findVal) {
                list.add(temp);
                temp--;
            }
            list.add(mid);

            temp = mid + 1;
            while (temp <= right && arr[temp] == findVal) {
                list.add(temp);
                temp++;
            }
            return list;
        }
    }
}
