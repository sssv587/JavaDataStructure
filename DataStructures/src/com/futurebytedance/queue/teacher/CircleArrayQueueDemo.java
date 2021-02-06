package com.futurebytedance.queue.teacher;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/6 - 18:08
 * @Description 环形队列的实现
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4);//说明设置4，其队列的最大存储元素是3
        ArrayQueueUtil.queueTest(circleArrayQueue);
    }
}

class CircleArrayQueue {
    private final int maxSize;//表示数组的最大容量
    //front变量的含义做一个调整：front就指向队列的第一个元素，也就是说arr[front]
    //front的初始值 = 0
    private int front;
    //rear变量的含义做一个调整：rear就指向队列的最后一个元素的后一个位置，也就是说arr[rear]。因为希望空出一个空间做为约定。
    //rear的初始值 = 0
    private int rear;
    private final int[] arr;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        //该数组用于存放数据，模拟队列
        arr = new int[this.maxSize];
    }

    //判断队列是否满
    public boolean isFull() {
        return (this.rear + 1) % maxSize == front;
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
            //直接将数据加入
            arr[rear] = n;
            //将rear后移，这里必须考虑取模
            rear = (rear + 1) % maxSize;
        }
    }

    //获取队列的数据，出队列
    public int getQueue() {
        //判断队列是否空
        if (isEmpty()) {
            //通过抛出异常
            throw new RuntimeException("队列为空,不能取数据!");
        } else {
            //这里需要分析出front是指向队列的第一个元素
            //1.先把front对应的值保留到一个临时变量
            //2.将front后移，考虑取模
            //3.将临时保存的变量返回
            int value = arr[front];
            front = (front + 1) % maxSize;
            return value;
        }
    }

    //显式队列的所有数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列空的，没有数据!");
        } else {
            //思路：从front开始遍历，遍历多少个元素
            for (int i = front; i < front + size(); i++) {
                System.out.println("arr[" + i % maxSize + "]=" + arr[i % maxSize]);
            }
        }
    }

    //求出当前队列有效数据的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显式队列的头数据，注意，不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据!");
        } else {
            return arr[front];
        }
    }
}
