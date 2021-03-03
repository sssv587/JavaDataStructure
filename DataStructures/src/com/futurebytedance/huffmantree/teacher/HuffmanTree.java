package com.futurebytedance.huffmantree.teacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/3 - 22:29
 * @Description 哈夫曼树创建
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    //编写一个前序遍历的方法
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("是空树，不能遍历~~~");
        }
    }

    //创建赫夫曼树的方法
    public static Node createHuffmanTree(int[] arr) {
        //第一步为了操作方便
        //1.遍历arr数组
        //2.将arr的每个元素构建成一个Node
        //3.将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        //我们处理的过程是循环过程
        while (nodes.size() > 1) {
            //排序 从小到大
            Collections.sort(nodes);

            System.out.println("nodes" + nodes);

            //取出根节点权值最小的两颗二叉树
            //(1)取出权值最小的节点(二叉树)
            Node leftNode = nodes.get(0);
            //(2)取出权值第二小的节点(二叉树)
            Node rightNode = nodes.get(1);
            //(3)构建一个新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //(4)从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }

        //返回哈夫曼树的最后一个节点
        return nodes.get(0);
    }
}

//创建节点类
//为了让Node对象支持排序Collections集合排序
class Node implements Comparable<Node> {
    int value;//节点权值
    Node left;//指向左子节点
    Node right;//指向右子节点

    public Node(int value) {
        this.value = value;
    }

    public void preOrder() {
        preOrder(this);
    }

    public void preOrder(Node node) {
        System.out.println(node);
        if (node.left != null) {
            node.left.preOrder(node.left);
        }
        if (node.right != null) {
            node.right.preOrder(node.right);
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大排序
        return this.value - o.value;
    }
}