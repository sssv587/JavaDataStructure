package com.futurebytedance.linkedlist.teacher.doublelinkedlist;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/8 - 20:01
 * @Description 双线链表的实现
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //创建一个双线链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        //创建节点
        HeroNode heroNode1 = new HeroNode(1, "关云长", "汉寿亭侯");
        HeroNode heroNode2 = new HeroNode(2, "张飞", "万人敌");
        HeroNode heroNode3 = new HeroNode(3, "赵子龙", "长枪一战");
        HeroNode heroNode4 = new HeroNode(4, "马超", "神威将军");

        doubleLinkedList.add(heroNode1);
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.add(heroNode4);

        doubleLinkedList.list();

        System.out.println();

        //修改
        HeroNode heroNode = new HeroNode(3, "赵子龙", "忠肝义胆");
        doubleLinkedList.update(heroNode);
        doubleLinkedList.list();

        System.out.println();

        //删除
        doubleLinkedList.del(2);
        doubleLinkedList.list();
    }
}


//创建一个双向链表的类
class DoubleLinkedList {
    //先初始化一个头结点,头结点不要动,不存放具体数据
    private final HeroNode head = new HeroNode(0, "", "");

    //返回头结点
    public HeroNode getHead() {
        return head;
    }

    //从双向链表中删除一个结点
    //说明
    //1.对于双线链表，我们可以直接找到要删除的这个节点
    //2.找到后，自我删除即可
    public void del(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到待删除节点
        while (true) {
            if (temp == null) {//已经到链表的最后
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            //如果是最后一个节点，就不需要执行下面这句话，否则出现空指针
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("要删除的节点不存在!");
        }
    }

    //修改一个节点中的内容，可以看到双向链表的节点内容修改和单向链表一样
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~~~");
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;//已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {//没有找到
            System.out.printf("没有找到编号%d的节点", newHeroNode.no);
        }
    }

    //添加一个元素到双向链表的最后
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助变量temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //
            if (temp.next == null) {//
                //当退出while时，temp就指向了链表的最后
                //将最后这个节点的next指向新的节点
                temp.next = heroNode;
                heroNode.pre = temp;
                break;
            }
            //如果没有找到最后,就将temp后移
            temp = temp.next;
        }
    }

    //显式链表[遍历]
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
        }
        //因为头结点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (temp != null) {
            //判断是否到链表最后
            //输出节点的信息
            System.out.println(temp);
            //将temp后移，一定小心
            temp = temp.next;
        }
    }
}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点，默认为null
    public HeroNode pre;//指向前一个节点，默认为null

    //构造器
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //为了显示方法，重写toString()方法
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
