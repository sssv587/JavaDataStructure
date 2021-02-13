package com.futurebytedance.recursion.student;

import java.util.ArrayList;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/13 - 17:25
 * @Description 迷宫问题--回溯算法  最优解
 */
public class MiGong {
    static int count;
    private final static int[][] arr = new int[8][7];
    private final static int maxLine = 8;
    private final static int maxRow = 7;
    private final static ArrayList<Integer> arrayList = new ArrayList<>();

    public static void main(String[] args) {
        setValue();
        getWay(1, 1);
        System.out.println("解法总共有:" + count + "种");
        System.out.println("最短的路径步数为:"+ arrayList.stream().min(Integer::compareTo));
    }

    public static void getWay(int i, int j) {
        if (i == 6 && j == 5) {
            view();
            return;
        }
        if (canMove(i, j, i, j + 1)) {
            arr[i][j] = 3;
            getWay(i, j + 1);
            arr[i][j] = 0;
        }
        //向左走
        if (canMove(i, j, i, j - 1)) {
            arr[i][j] = 3;
            getWay(i, j - 1);
            arr[i][j] = 0;
        }
        //向下走
        if (canMove(i, j, i + 1, j)) {
            arr[i][j] = 3;
            getWay(i + 1, j);
            arr[i][j] = 0;
        }
        //向上走
        if (canMove(i, j, i - 1, j)) {
            arr[i][j] = 3;
            getWay(i - 1, j);
            arr[i][j] = 0;
        }
    }

    //判断下一个路径是否可以走
    public static boolean canMove(int i, int j, int targetI, int targetJ) {
        if (targetI < 0 || targetJ < 0 || targetI >= maxLine || targetJ >= maxRow) {
            return false;
        }
        if (arr[targetI][targetJ] == 1) {
            return false;
        }
        return arr[targetI][targetJ] != 3;
    }

    //初始化二维数组迷宫
    public static void setValue() {
        for (int i = 0; i < 7; i++) {
            arr[0][i] = 1;
            arr[7][i] = 1;
        }
        //左右全部置为1
        for (int i = 0; i < 8; i++) {
            arr[i][0] = 1;
            arr[i][6] = 1;
        }

        //设置挡板,1表示
        arr[3][1] = 1;
        arr[3][2] = 1;

        arr[1][1] = 2;
        arr[6][5] = 2;
    }

    public static void view() {
        System.out.println("得到一个解:");
        for (int i = 0; i < maxLine; i++) {
            for (int j = 0; j < maxRow; j++) {
                System.out.print(arr[i][j] + "\t");
                if (arr[i][j] == 3) {
                    count++;
                }
            }
            System.out.println();
        }
        arrayList.add(count);
        count = 0;
    }
}
