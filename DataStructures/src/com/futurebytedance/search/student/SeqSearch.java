package com.futurebytedance.search.student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/18 - 21:41
 * @Description 线性(顺序)查找算法
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        List<Integer> list = seqSearch(arr, 1000);
        System.out.println(list);
    }

    public static List<Integer> seqSearch(int[] arr, int findVal) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == findVal) {
                list.add(i);
            }
        }
        return list;
    }
}
