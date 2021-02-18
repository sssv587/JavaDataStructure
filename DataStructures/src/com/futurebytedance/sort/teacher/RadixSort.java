package com.futurebytedance.sort.teacher;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/17 - 19:54
 * @Description 基数排序
 */
public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);//生成一个[0,800000)数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是:" + date1Str);

        radixSort(arr);

        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("基数排序后的时间是:" + date2Str);
    }

    //基数排序方法
    public static void radixSort(int[] arr) {
        //根据前面的推导过程，我们可以得到最终的基数排序代码
        //1.得到数组中最大的数的位数
        int max = arr[0];//假设第一数就是最大数
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();

        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //说明
        //1.二维数组包含10个一维数组
        //2.为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定义为arr.length
        //3.很明显，基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的数据个数
        //可以这样理解
        //bucketElementCounts[0],记录的就是 bucket[0] 桶的放入的数据的个数
        int[] bucketElementCounts = new int[10];

        //这里我们使用循环将代码处理
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //(针对每个元素的对应位进行排序处理),第一次是个位，第二次是十位，第三次是百位..
            for (int value : arr) {
                //取出每个元素的对应位的值
                int digitOfElement = value / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = value;
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来的数组)
            int index = 0;
            //遍历每一趟，并将桶中的数据，放入到原来的数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中，有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //循环即桶第k个桶(即第k个一维数组),放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                bucketElementCounts[k] = 0;
            }
//            System.out.println("第" + (i + 1) + "轮排序的结果是:" + Arrays.toString(arr));
        }

        //第1轮(针对每个元素的个位进行排序处理)
        /*for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位的值
            int digitOfElement = arr[j] % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }

        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来的数组)
        int index = 0;
        //遍历每一趟，并将桶中的数据，放入到原来的数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中，有数据，我们才放入到原数组
            if (bucketElementCounts[k] != 0) {
                //循环即桶第k个桶(即第k个一维数组),放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
            //第1轮处理后，需要将bucketElementCounts[k] = 0!!!!
            bucketElementCounts[k] = 0;
        }

        System.out.println("第1轮，对个位的排序处理后: arr=" + Arrays.toString(arr));

        // ======================================

        //第2轮(针对每个元素的十位进行处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的十位的值
            int digitOfElement = arr[j] / 10 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来的数组)
        index = 0;
        //遍历每一趟，并将桶中的数据，放入到原来的数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中，有数据，我们才放入到原数组
            if (bucketElementCounts[k] != 0) {
                //循环即桶第k个桶(即第k个一维数组),放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
            bucketElementCounts[k] = 0;
        }

        System.out.println("第2轮，对个位的排序处理后: arr=" + Arrays.toString(arr));


        //第三轮排序
        //第3轮(针对每个元素的百位进行处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位的值
            int digitOfElement = arr[j] / 100 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来的数组)
        index = 0;
        //遍历每一趟，并将桶中的数据，放入到原来的数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中，有数据，我们才放入到原数组
            if (bucketElementCounts[k] != 0) {
                //循环即桶第k个桶(即第k个一维数组),放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
            bucketElementCounts[k] = 0;
        }

        System.out.println("第3轮，对个位的排序处理后: arr=" + Arrays.toString(arr));*/
    }
}