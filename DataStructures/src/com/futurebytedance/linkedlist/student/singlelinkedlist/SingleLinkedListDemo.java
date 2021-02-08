package com.futurebytedance.linkedlist.student.singlelinkedlist;

import java.util.Stack;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/8 - 1:22
 * @Description 实现单链表
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();

        Node node1 = new Node(1, "迪丽热巴");
        Node node2 = new Node(1, "古力娜扎");
        Node node3 = new Node(3, "佟丽娅");
        Node node4 = new Node(4, "高圆圆");
        Node node5 = new Node(8, "诸葛亮");
        Node node6 = new Node(7, "关羽");
        Node node7 = new Node(6, "马超");
        Node node8 = new Node(5, "张飞");

        //头结点后插入，测试成功!
//        linkedList.addAfterHead(node1);
//        linkedList.addAfterHead(node2);
//        linkedList.addAfterHead(node3);
//        linkedList.addAfterHead(node4);

        //尾节点后插入，测试成功!
//        linkedList.addAfterTail(node1);
//        linkedList.addAfterTail(node2);
//        linkedList.addAfterTail(node3);
//        linkedList.addAfterTail(node4);


        //自定义排序插入，测试成功!
        linkedList.addByOrder(node4);
        linkedList.addByOrder(node3);
        linkedList.addByOrder(node2);
        linkedList.addByOrder(node1);


        //删除元素
        //linkedList.del(1);

        //更新元素
        linkedList.updateNode(3, "刘亦菲");

        linkedList.view();

        //五道面试题
        //一、求单链表中有效节点的个数
        System.out.println("----------一-----------");
        int length = getLength(linkedList.getHead());
        System.out.println("链表的长度为:" + length);

        //二、查找单链表中的倒数第k个结点
        System.out.println("----------二-----------");
        Node node = getNode(linkedList.getHead(), 3);
        System.out.println(node);

        //三、单链表的反转
        System.out.println("----------三-----------");
        reverseLinkedList(linkedList.getHead());
        linkedList.view();

        //四、从尾到头打印单链表
        System.out.println("----------四-----------");
        reverseView(linkedList.getHead());

        //五、合并两个有序的单链表，合并之后的链表依然有序
        System.out.println("----------五-----------");
        SingleLinkedList linkedList1 = new SingleLinkedList();
        linkedList1.addByOrder(node5);
        linkedList1.addByOrder(node6);
        linkedList1.addByOrder(node7);
        linkedList1.addByOrder(node8);
        SingleLinkedList linkedList2 = combineTwoLinkedList(linkedList.getHead(), linkedList1.getHead());
        linkedList2.view();
    }

    //五、合并两个有序的单链表，合并之后的链表依然有序
    public static SingleLinkedList combineTwoLinkedList(Node head1, Node head2) {
        SingleLinkedList newSingleLinkedList = new SingleLinkedList();
        Node temp1 = head1.next;
        Node temp2 = head2.next;
        Node node1 = null;
        while (temp1 != null) {
            node1 = new Node(temp1.getNo(),temp1.getName());
            newSingleLinkedList.addByOrder(node1);
            temp1 = temp1.next;
        }
        while (temp2 != null) {
            node1 = new Node(temp2.getNo(),temp2.getName());
            newSingleLinkedList.addByOrder(node1);
            temp2 = temp2.next;
        }
        return newSingleLinkedList;
    }

    //四、从尾到头打印单链表
    public static void reverseView(Node head) {
        if (head.next == null) {
            System.out.println("链表汇总没有元素，无法反向遍历!!!");
        }
        Stack<Node> nodeStack = new Stack<>();
        Node temp = head.next;
        while (temp != null) {
            nodeStack.push(temp);
            temp = temp.next;
        }
        while (nodeStack.size() > 0) {
            System.out.println(nodeStack.pop());
        }
    }

    //三、单链表的反转
    public static void reverseLinkedList(Node head) {
        Node newHead = new Node(0, "");
        if (head.next == null) {
            System.out.println("链表中没有元素，无法反转!!!");
            return;
        }
        Node currentNode = head.next;
        Node currentNodeNext = null;
        while (currentNode != null) {
            currentNodeNext = currentNode.next;
            currentNode.next = newHead.next;
            newHead.next = currentNode;
            currentNode = currentNodeNext;
        }
        head.next = newHead.next;
    }

    //二、查找单链表中的倒数第k个结点
    public static Node getNode(Node head, int lastIndex) {
        int length = getLength(head);
        if (head.next == null || lastIndex <= 0 || lastIndex > length) {
            return null;
        }
        Node temp = head.next;
        for (int i = 0; i < length - lastIndex; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //一、求单链表中有效节点的个数
    public static int getLength(Node head) {
        int length = 0;
        if (head.next == null) {
            return 0;
        }
        Node temp = head.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }
}

class SingleLinkedList {
    public Node getHead() {
        return head;
    }

    private final Node head = new Node(0, "");

    //头结点后增
    public void addAfterHead(Node newNode) {
        Node temp = head.next;
        //先判断是否有同样的元素
        if (isExist(newNode)) {
            System.out.println("已存在，请勿插入同样的数据!");
        } else {
            head.next = newNode;
            newNode.next = temp;
        }
    }

    //末尾增
    public void addAfterTail(Node newNode) {
        Node temp = head;
        if (isExist(newNode)) {
            System.out.println("已存在，请勿插入同样的数据!");
        } else {
            while (true) {
                if (temp.next == null) {
                    temp.next = newNode;
                    break;
                }
                temp = temp.next;
            }
        }
    }

    //自定义排序增
    public void addByOrder(Node newNode) {
        Node temp = head;
        if (isExist(newNode)) {
            System.out.println("已存在，请勿插入同样的数据!");
        } else {
            while (true) {
                if (temp.next == null) {
                    temp.next = newNode;
                    break;
                }
                if (temp.next.compareTo(newNode) > 0) {
                    newNode.next = temp.next;
                    temp.next = newNode;
                    break;
                }
                temp = temp.next;
            }
        }
    }

    //删除
    public void del(int no) {
        Node temp = head;
        if (isExist(no)) {
            System.out.println("要删除的节点不存在!");
        } else {
            while (true) {
                if (temp.next.getNo() == no) {
                    temp.next = temp.next.next;
                    break;
                }
                temp = temp.next;
            }
        }
    }

    //修改
    public void updateNode(int no, String updateStr) {
        Node temp = head.next;
        if (isExist(no)) {
            System.out.println("要添加的元素不存在!");
        } else {
            while (true) {
                if (temp.getNo() == no) {
                    temp.setName(updateStr);
                    break;
                }
                temp = temp.next;
            }
        }
    }

    //查找
    public void view() {
        Node temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //判断链表中是否有同样的元素
    public boolean isExist(Node node) {
        Node temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.compareTo(node) == 0) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        return flag;
    }

    public boolean isExist(int no) {
        Node temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.getNo() == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        return !flag;
    }
}

class Node implements Comparable<Node> {
    private final int no;
    private String name;
    public Node next;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public int getNo() {
        return no;
    }

    public void setName(String name) {
        this.name = name;
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
