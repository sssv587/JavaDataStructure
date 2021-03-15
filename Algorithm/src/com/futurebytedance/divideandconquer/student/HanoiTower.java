package com.futurebytedance.divideandconquer.student;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/15 - 23:14
 * @Description 分治算法解决汉诺塔问题
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
    }

    public static void hanoiTower(int n, char a, char b, char c) {
        if (n == 1) {
            System.out.println("第1个盒子 " + a + "->" + c);
        } else {
            hanoiTower(n - 1, a, c, b);
            System.out.println("第" + n + "个盒子 " + a + "->" + c);
            hanoiTower(n - 1, b, a, c);
        }
    }
}
