package com.futurebytedance.tree.student.arraybinarytree;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/26 - 0:03
 * @Description 顺序存储二叉树 -- 针对完全二叉树
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 3, 6, 8, 10, 14};
        BinaryTree binaryTree = new BinaryTree(arr);

        System.out.println("前序输出顺序存储的二叉树的结果为:");
        binaryTree.preOrder();

        System.out.println("中序输出顺序存储二叉树的结果为:");
        binaryTree.infixOrder();

        System.out.println("后序输出顺序存储二叉树的结果为:");
        binaryTree.postOrder();
    }
}

class BinaryTree {
    private final int[] array;

    public BinaryTree(int[] array) {
        this.array = array;
    }

    //前序遍历二叉树
    public void preOrder(int index) {
        if (array == null || index < 0 || index > array.length) {
            System.out.println("您输入的顺序存储二叉树不正确!");
            return;
        }
        System.out.println(array[index]);

        if (2 * index + 1 < array.length) {
            preOrder(2 * index + 1);
        }
        if (2 * index + 2 < array.length) {
            preOrder(2 * index + 2);
        }
    }

    public void preOrder() {
        this.preOrder(0);
    }

    //中序遍历顺序存储二叉树
    public void infixOrder(int index) {
        if (array == null || index > array.length || index < 0) {
            System.out.println("您输入的顺序存储二叉树不正确!");
            return;
        }
        if (2 * index + 1 < array.length) {
            infixOrder(2 * index + 1);
        }
        System.out.println(array[index]);

        if (2 * index + 2 < array.length) {
            infixOrder(2 * index + 2);
        }
    }

    public void infixOrder() {
        this.infixOrder(0);
    }

    //后序遍历线索化存储二叉树
    public void postOrder(int index) {
        if (array == null || index < 0 || index > array.length) {
            System.out.println("您输入的顺序存储二叉树不正确!");
            return;
        }
        if (2 * index + 1 < array.length) {
            postOrder(2 * index + 1);
        }
        if (2 * index + 2 < array.length) {
            postOrder(2 * index + 2);
        }
        System.out.println(array[index]);
    }

    public void postOrder() {
        this.postOrder(0);
    }
}
