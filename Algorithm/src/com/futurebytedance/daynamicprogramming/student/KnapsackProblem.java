package com.futurebytedance.daynamicprogramming.student;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/17 - 23:05
 * @Description 动态规划算法- 0-1背包问题
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] pounds = {1, 4, 3};
        int[] price = {1500, 3000, 2000};
        int m = 4;
        int n = pounds.length;
        int[][] finalArray = new int[n + 1][m + 1];
        int[][] path = new int[n + 1][m + 1];

        for (int i = 1; i < finalArray.length; i++) {
            for (int j = 1; j < finalArray[0].length; j++) {
                //若准备加入的新增的商品的重量小于j时，使用当前上一行的同列值
                if (pounds[i - 1] > j) {
                    finalArray[i][j] = finalArray[i - 1][j];
                } else {
                    if (finalArray[i - 1][j] > price[i - 1] + finalArray[i - 1][j - pounds[i - 1]]) {
                        finalArray[i][j] = finalArray[i - 1][j];
                    } else {
                        path[i][j] = 1;
                        finalArray[i][j] = price[i - 1] + finalArray[i - 1][j - pounds[i - 1]];
                    }
                }
            }
        }

        int maxValue = finalArray[0][0];
        for (int[] ints : finalArray) {
            for (int j = 0; j < finalArray[0].length; j++) {
                if (ints[j] > maxValue) {
                    maxValue = ints[j];
                }
//                System.out.print(ints[j] + "\t");
            }
//            System.out.println();
        }

        System.out.println("背包的最大值为:" + maxValue);

        for (int i = finalArray.length - 1; i > 0; ) {
            for (int j = finalArray[0].length - 1; j > 0; ) {
                if (path[i][j] == 1) {
                    System.out.println("第" + i + "个商品放入背包");
                    j -= pounds[i - 1];
                }
                i--;
            }
        }
    }
}
