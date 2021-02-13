package com.futurebytedance.recursion.student;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/13 - 17:25
 * @Description 八皇后问题---回溯算法
 * <p>
 * 1.用一维数组代替解法，角标就是第几行，值就是第几列
 * 2.for循环尝试赋值，并进行判断
 * 3.回溯解决问题
 */
public class Queue8 {
    static int maxSize = 8;
    static int[] arr = new int[maxSize];
    static int count;
    static int judgeCount;

    public static void main(String[] args) {
        getResult(0);
        System.out.println("共有" + count + "种解法");
        System.out.println("回溯" + judgeCount + "次");
    }

    //回溯解法
    public static void getResult(int n) {
        if (n == maxSize) {
            viewSolution();
            return;
        }
        //8列
        for (int i = 0; i < maxSize; i++) {
            arr[n] = i;
            if (judge(n)) {
                getResult(n + 1);
            }
        }
    }

    //判断是否在同一行、同一斜线
    public static boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            judgeCount++;
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }


    //打印最终输出结果
    public static void viewSolution() {
        count++;
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }
}
