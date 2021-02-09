package com.futurebytedance.linkedlist.student.circlelinkedlist;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/9 - 16:35
 * @Description 约瑟夫问题实现
 */
public class Josephu {
    public static void main(String[] args) {
        CircleLinkedList circleLinkedList = new CircleLinkedList();
        //创建一个环形链表
        circleLinkedList.createCircleLinkedList(10);

        circleLinkedList.viewList();

        //解决约瑟夫问题
        circleLinkedList.popByOrder(3, 2);
    }
}

class CircleLinkedList {
    private Node first = null;

    //解决约瑟夫问题的出队顺序
    public void popByOrder(int startNo, int spaceDistance) {
        int length = getLength();
        if (startNo > length || spaceDistance > length) {
            System.out.println("起始点不存在或间距大于链表中的长度");
        } else {
            //定义一个节点，指向first节点的后一个节点
            Node lastNode;
            Node temp = first;
            while (temp.next != first) {
                temp = temp.next;
            }
            //指向了链表中的最后一个节点
            lastNode = temp;
            //将first节点和lastNode节点后移
            for (int i = 0; i < startNo - 1; i++) {
                first = first.next;
                lastNode = lastNode.next;
            }
            //出队
            while (first != lastNode) {
                for (int i = 0; i < spaceDistance - 1; i++) {
                    first = first.next;
                    lastNode = lastNode.next;
                    lastNode.next = first.next;
                    System.out.println(first.getValue());
                    first = first.next;
                    if (first == lastNode) {
                        break;
                    }
                }
            }
            System.out.println(first.getValue());
        }
    }

    //创建指定个数的环形链表
    public void createCircleLinkedList(int num) {
        if (num < 0) {
            System.out.println("创建的链表长度不能为负数!请重新输入~");
            return;
        }
        Node temp = null;
        for (int i = 1; i <= num; i++) {
            Node node = new Node(i);
            //初始化first节点的值
            if (i == 1) {
                first = node;
                first.next = first;
                temp = first;
            } else {
                temp.next = node;
                node.next = first;
                temp = node;
            }
        }
    }

    //遍历链表
    public void viewList() {
        Node temp = first;
        while (temp.next != first) {
            System.out.println(temp);
            temp = temp.next;
        }
        System.out.println(temp);
    }

    //返回链表的长度
    public int getLength() {
        Node temp = first;
        int length = 0;
        if (first == null) {
            return 0;
        }
        while (temp.next != first) {
            length++;
            temp = temp.next;
        }
        return length + 1;
    }
}

//定义一个链表的Node类
class Node {
    private final int value;
    public Node next;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}

