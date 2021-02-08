package com.futurebytedance.linkedlist.teacher;

import java.util.Stack;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/8 - 16:04
 * @Description 演示栈的基本使用
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        //出栈
        //smith,tom,jack
        while (stack.size() > 0) {
            System.out.println(stack.pop());//pop就是将栈顶的数据取出
        }
    }
}
