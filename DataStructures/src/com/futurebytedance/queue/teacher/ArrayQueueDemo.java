package com.futurebytedance.queue.teacher;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/6 - 17:13
 * @Description 使用数组实现队列
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        ArrayQueueUtil.queueTest(queue);
        System.out.println("程序退出~~~");
    }
}


//使用数组模拟队列 - 编写一个ArrayQueue
class ArrayQueue {
    private final int maxSize;//表示数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private final int[] arr;//该数组用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[this.maxSize];
        front = -1;//指向队列头部,分析出front是指向队列头的前一个位置
        rear = -1;//指向队列尾，指向队列尾的数据(即就是队列最后一个数据)
    }

    //判断队列是否满
    public boolean isFull() {
        return this.rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return this.rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
        } else {
            this.rear++;//让rear后移
            arr[rear] = n;
        }
    }

    //获取队列的数据，出队列
    public int getQueue() {
        //判断队列是否空
        if (isEmpty()) {
            //通过抛出异常
            throw new RuntimeException("队列为空,不能取数据!");
        } else {
            front++;//front后移
            return arr[front];
        }
    }

    //显式队列的所有数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列空的，没有数据!");
        } else {
            for (int i = front + 1; i <= rear; i++) {
                System.out.println("arr[" + i + "]=" + arr[i]);
            }
        }
    }

    //显式队列的头数据，注意，不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据!");
        } else {
            return arr[front + 1];
        }
    }
}

