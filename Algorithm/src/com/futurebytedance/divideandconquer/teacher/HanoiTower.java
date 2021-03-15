package com.futurebytedance.divideandconquer.teacher;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/15 - 22:13
 * @Description 分治算法解决汉诺塔问题
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(8, 'A', 'B', 'C');
    }

    //汉诺塔移动的方法
    //使用分治算法
    public static void hanoiTower(int num, char a, char b, char c) {
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
        } else {
            //如果我们有n>=2的情况，我们总是可以看做是两个盘 1.最下边的盘 2.上面的所有盘
            //1.先把最上面的盘A->B,移动过程会使用到c
            hanoiTower(num - 1, a, c, b);
            //2.最下面的盘A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            //3.把B塔的所有盘从B->C,移动过程使用到a塔
            hanoiTower(num - 1, b, a, c);
        }
    }
}
