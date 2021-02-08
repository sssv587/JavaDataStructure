package com.futurebytedance.linkedlist.student.doublelinkedlist;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/8 - 20:29
 * @Description 实现双线链表
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList linkedList = new DoubleLinkedList();

        Node node1 = new Node(1, "庞统");
        Node node2 = new Node(2, "诸葛亮");
        Node node3 = new Node(3, "周瑜");
        Node node4 = new Node(4, "司马懿");

        //插入头结点测试
//        linkedList.insertIntoHead(node1);
//        linkedList.insertIntoHead(node1);
//        linkedList.insertIntoHead(node2);
//        linkedList.insertIntoHead(node3);
//        linkedList.insertIntoHead(node4);

        //插入尾结点测试
//        linkedList.insertIntoEnd(node1);
//        linkedList.insertIntoEnd(node2);
//        linkedList.insertIntoEnd(node3);
//        linkedList.insertIntoEnd(node4);
//        linkedList.insertIntoEnd(node1);

        //插入到任意节点
//        linkedList.insertIntoAnotherPlace(node4,1);

        //倒序插入
        linkedList.insertByOrder(node1);
        linkedList.insertByOrder(node2);
        linkedList.insertByOrder(node3);
        linkedList.insertByOrder(node4);

        linkedList.view();
    }
}

class DoubleLinkedList {
    private final Node head = new Node(0, "");

    public Node getHead() {
        return head;
    }

    //实现排序插入
    public void insertByOrder(Node node) {
        if (isExist(node)) {
            System.out.println("数据重复！请勿重新插入!");
            return;
        }
        Node temp = head.next;
        //如果是第一个元素插入
        if (temp == null) {
            head.next = node;
            node.pre = head;
        }
        while (temp != null) {
            if (temp.getNo() < node.getNo()) {
                node.next = temp;
                node.pre = temp.pre;
                temp.pre.next = node;
                temp.pre = node;
                break;
            }
            temp = temp.next;
        }
    }

    //插入到任意位置
    public void insertIntoAnotherPlace(Node node, int index) {
        if (isExist(node) || index <= 0 || index > getLength()) {
            System.out.println("数据重复！请勿重新插入!");
            return;
        }
        Node temp = head.next;
        for (int i = 1; i < index; i++) {
            temp = temp.next;
        }
        node.next = temp;
        node.pre = temp.pre;
        temp.pre.next = node;
        temp.pre = node;
    }

    //插入到末尾
    public void insertIntoEnd(Node node) {
        if (isExist(node)) {
            System.out.println("数据重复！请勿重新插入!");
            return;
        }
        Node temp = head;
        while (true) {
            if (temp.next == null) {
                temp.next = node;
                node.pre = temp;
                break;
            }
            temp = temp.next;
        }
    }

    //插入到头结点的后面
    public void insertIntoHead(Node node) {
        if (isExist(node)) {
            System.out.println("数据重复！请勿重新插入!");
            return;
        }
        node.next = head.next;
        if (head.next != null) {
            head.next.pre = node;
        }
        head.next = node;
    }

    //判断插入节点是否重复
    public boolean isExist(Node node) {
        if (head.next == null) {
            return false;
        }
        Node temp = head;
        while (temp != null) {
            if (temp.getNo() == node.getNo()) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    //判断插入节点是否重复
    public boolean isExist(int no) {
        if (head.next == null) {
            return false;
        }
        Node temp = head;
        while (temp != null) {
            if (temp.getNo() == no) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    //返回链表的长度
    public int getLength() {
        int length = 0;
        Node temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    //遍历链表
    public void view() {
        Node temp = head.next;
        if (temp == null) {
            System.out.println("链表为空!");
        }
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class Node implements Comparable<Node> {
    private final int no;
    private final String name;
    public Node next;
    public Node pre;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.no - o.no;
    }
}
