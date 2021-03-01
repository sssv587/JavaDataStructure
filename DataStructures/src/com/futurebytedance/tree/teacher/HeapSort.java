package com.futurebytedance.tree.teacher;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/1 - 23:32
 * @Description 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序
//        int[] arr = {4, 6, 8, 5, 9};
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        headSort(arr);
        System.out.println(Arrays.toString(arr));

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
    }

    //编写一个堆排序的方法
    public static void headSort(int[] arr) {
        System.out.println("堆排序!");
        int temp;
        //分步完成
//        addJustHeap(arr, 1, arr.length);
//        System.out.println("第一次" + Arrays.toString(arr)); //[4, 9, 8, 5, 6]

//        addJustHeap(arr, 0, arr.length);
//        System.out.println("第二次" + Arrays.toString(arr)); //[9, 6, 8, 5, 4]

        //完成最终代码
        //1)将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            addJustHeap(arr, i, arr.length);
        }

        //2)将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
        //3)重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
        for (int i = arr.length - 1; i > 0; i--) {
            //交换
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            //此时因为根节点一下的所有子树全部都是大顶堆，所以这里可以不用循环了
            addJustHeap(arr, 0, i);
        }
    }

    //将一个数组(二叉树),调整成一个大顶堆

    /**
     * 功能： 完成 将 以 i 对应的非叶子结点的树调整成大顶堆
     * 举例  int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => 得到 {4, 9, 8, 5, 6}
     * 如果我们再次调用  adjustHeap 传入的是 i = 0 => 得到 {4, 9, 8, 5, 6} => {9,6,8,5, 4}
     * 功能：完成将以i对应的非叶子节点的树调整成大顶堆
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整,length是在逐渐减少
     */
    public static void addJustHeap(int[] arr, int i, int length) {
        int temp = arr[i]; //先取出当前元素的值，保存在临时变量
        //开始调整
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) { //说明左子节点的值小于右子节点的值
                k++; //指向右子节点
            }
            if (arr[k] > temp) { //如果子节点大于父节点
                arr[i] = arr[k]; //把较大的值赋给当前节点
                i = k; // i指向k，继续循环比较
            } else {
                break;
            }
        }
        //当for循环结束后，我们已经将以i为父节点的树的最大值，放在了 最顶(局部)
        arr[i] = temp; //将temp值放到调整过后的位置
    }
}
