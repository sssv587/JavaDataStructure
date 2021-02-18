package com.futurebytedance.search.student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/18 - 21:42
 * @Description 插值查找算法
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i;
        }
        List<Integer> list = insertValueSearch(arr, 0, arr.length - 1, 2);
        System.out.println(list);
    }

    public static List<Integer> insertValueSearch(int[] arr, int left, int right, int findVal) {
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return new ArrayList<>();
        }
        int mid = left + right * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if (findVal > midVal) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            List<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            while (temp >= left && arr[temp] == findVal) {
                list.add(temp);
                temp--;
            }
            list.add(mid);
            temp = right + 1;
            while (temp <= right && arr[temp] == findVal) {
                list.add(temp);
                temp++;
            }
            return list;
        }
    }
}
