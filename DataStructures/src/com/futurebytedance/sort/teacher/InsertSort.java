package com.futurebytedance.sort.teacher;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/15 - 16:10
 * @Description 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1, -1, 89};
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);//生成一个[0,800000)数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是:" + date1Str);

        insertSort(arr);

        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("希尔排序后的时间是:" + date2Str);
    }

    //插入排序
    public static void insertSort(int[] arr) {
        int insertVal;
        int insertIndex;
        //使用for循环来把代码简化
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //这里我们判断是否需要重置
            if (insertIndex + 1 == i) {
                arr[insertIndex + 1] = insertVal;
            }
//            System.out.println("第" + i + "轮");
//            System.out.println(Arrays.toString(arr));
        }

        /*
        //使用逐步推导的方式来讲解，便于理解
        //第1轮 {101,34,119,1} => {23,101,119,1}

        //定义待插入的数
        int insertVal = arr[1];
        int insertIndex = 0;//即arr[1]的前面这个数的下标

        //给insertVal 找到插入的位置
        //说明
        //1.insertIndex >= 0 保证在给insertVal找插入位置，不越界
        //2.insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
        //3.就需要将arr[insertIndex] 后移
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环时，说明插入的位置找到，insertIndex+1
        //
        arr[insertIndex + 1] = insertVal;

        System.out.println("第一轮插入");
        System.out.println(Arrays.toString(arr));

        //第2轮
        insertVal = arr[2];
        insertIndex = 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.println("第二轮插入");
        System.out.println(Arrays.toString(arr));

        //第三轮
        insertVal = arr[3];
        insertIndex = 2;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.println("第三轮插入");
        System.out.println(Arrays.toString(arr));*/
    }
}
