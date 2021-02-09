package com.futurebytedance.stack.teacher;

import java.util.Scanner;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/9 - 16:50
 * @Description 栈的实现 ---> 数组
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show:表示显式栈");
            System.out.println("exit:退出程序");
            System.out.println("push:表示添加数据到栈(入栈)");
            System.out.println("pop:表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择:");
            key = scanner.next();
            switch (key) {
                case "show":
                    arrayStack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        int res = arrayStack.pop();
                        System.out.println("出栈的数据是:" + res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

//定义一个ArrayStack表示栈
class ArrayStack {
    private final int maxSize;//栈的大小
    private final int[] stack;//数组，数组模拟栈，数据就放在该数组
    private int top = -1;

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈-push
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈-pop,将栈顶的数据返回
    public int pop() {
        //先判断栈是否空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显式栈的情况[遍历栈]，遍历时，需要从栈顶开始显式数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        //需要从栈顶开始显式数据
        for (int i = top; i >= 0; i--) {
            System.out.println("stack[" + i + "]=" + stack[i]);

        }
    }
}