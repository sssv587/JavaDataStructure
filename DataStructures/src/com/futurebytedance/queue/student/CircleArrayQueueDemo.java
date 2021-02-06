package com.futurebytedance.queue.student;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/6 - 18:43
 * @Description My 数组模拟环形列表实现 --- 不留标记位置
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4);
        CircleArrayQueueUtil.queueTest(circleArrayQueue);
    }
}

class CircleArrayQueue {
    private int maxLength;//环形队列的最大长度
    private int front;//指向第一个元素的位置
    private int rear;//指向最后一个元素的下一个位置
    private int[] arr;

    //构造方法
    public CircleArrayQueue(int maxLength) {
        this.maxLength = maxLength;
        arr = new int[this.maxLength];
        front = 0;
        rear = 0;
    }

    //判断为空的条件
    public boolean isEmpty() {
        return rear == front;
    }

    //判断队列满的条件
    public boolean isFull() {
        return (rear + 1) % maxLength == front;
    }

    //入队列:改变front
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满，无法添加元素!");
        } else {
            arr[rear] = n;
            rear = (rear + 1) % maxLength;
        }
    }

    //出队列:改变front
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法出队!");
        } else {
            int value = arr[front];
            front = (front + 1) % maxLength;
            return value;
        }
    }

    //求出当前队列有效数据的个数
    public int size() {
        return (rear + maxLength - front) % maxLength;
    }

    //遍历队列中的元素
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空的，没有数据!");
        } else {
            for (int i = front; i < front + size(); i++) {
                System.out.println("arr[" + i % maxLength + "]=" + arr[i % maxLength]);
            }
        }
    }

    //取出头部元素
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据!");
        } else {
            return arr[front];
        }
    }
}