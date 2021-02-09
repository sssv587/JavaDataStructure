package com.futurebytedance.stack.student;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/9 - 17:10
 * @Description 栈的实现 ---> 单链表
 */
public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkedListStack linkedListStack = new LinkedListStack(5);
        linkedListStack.pushIntoStack(new Node(1));
        linkedListStack.pushIntoStack(new Node(2));
        linkedListStack.pushIntoStack(new Node(3));
        linkedListStack.pushIntoStack(new Node(4));
        linkedListStack.pushIntoStack(new Node(5));
//        linkedListStack.pushIntoStack(new Node(6));

        linkedListStack.popOutStack();
        linkedListStack.popOutStack();
        linkedListStack.popOutStack();
        linkedListStack.popOutStack();
        linkedListStack.popOutStack();
//        linkedListStack.popOutStack();
    }
}

class LinkedListStack {
    private final Node head = new Node(0);
    private final int maxLength;

    public LinkedListStack(int maxLength) {
        this.maxLength = maxLength;
    }

    //入栈
    public void pushIntoStack(Node node) {
        Node temp = head;
        int length = getLength();
        if (length > maxLength) {
            System.out.println("栈已经溢出");
            return;
        }
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    //出栈
    public void popOutStack() {
        Node pre_temp = head;
        Node temp = head.next;
        if (temp == null) {
            System.out.println("栈为空");
            return;
        }
        while (temp.next != null) {
            pre_temp = pre_temp.next;
            temp = temp.next;
        }
        System.out.println(temp.getNo());
        pre_temp.next = null;
    }

    public int getLength() {
        int length = 0;
        Node temp = head;
        if (head.next == null) {
            return length;
        }
        while (temp.next != null) {
            temp = temp.next;
            length++;
        }
        return length;
    }
}

class Node {
    private final int no;
    public Node next;

    public int getNo() {
        return no;
    }

    public Node(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                '}';
    }
}