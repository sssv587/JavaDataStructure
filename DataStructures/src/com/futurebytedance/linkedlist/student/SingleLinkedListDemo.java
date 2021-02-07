package com.futurebytedance.linkedlist.student;

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

        //头结点后插入，测试成功!
        linkedList.addAfterHead(node1);
//        linkedList.addAfterHead(node2);
//        linkedList.addAfterHead(node3);
        linkedList.addAfterHead(node4);

        //尾节点后插入，测试成功!
//        linkedList.addAfterTail(node1);
//        linkedList.addAfterTail(node2);
        linkedList.addAfterTail(node3);
//        linkedList.addAfterTail(node4);


        //自定义排序插入，测试成功!
//        linkedList.addByOrder(node3);
        linkedList.addByOrder(node2);
//        linkedList.addByOrder(node1);


        //删除元素
        linkedList.del(1);

        //更新元素
        linkedList.updateNode(3, "刘亦菲");

        linkedList.view();
    }
}

class SingleLinkedList {
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
