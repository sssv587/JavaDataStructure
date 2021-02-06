package com.futurebytedance.queue.student;

import com.futurebytedance.queue.student.CircleArrayQueue;

import java.util.Scanner;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/6 - 20:00
 * @Description
 */
public class CircleArrayQueueUtil {
    public static void queueTest(CircleArrayQueue queue) {
        char key;//接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数字");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g'://取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.println("取出的数据是:" + res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h'://查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.println("队列头的数据是:" + res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
            }
        }
    }
}
