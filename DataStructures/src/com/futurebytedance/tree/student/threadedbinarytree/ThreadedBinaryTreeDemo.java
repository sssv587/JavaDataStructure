package com.futurebytedance.tree.student.threadedbinarytree;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/26 - 0:37
 * @Description 线索化二叉树
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        Node root = new Node(1, "tom");
        Node node1 = new Node(3, "jack");
        Node node2 = new Node(8, "smith");
        Node node3 = new Node(10, "mary");
        Node node4 = new Node(6, "king");
        Node node5 = new Node(14, "dim");
        Node node6 = new Node(7, "sss");
        Node node7 = new Node(20, "sss");
        Node node8 = new Node(21, "sss");
        Node node9 = new Node(22, "sss");
        Node node10 = new Node(23, "sss");

        ThreadBinaryTree threadBinaryTree = new ThreadBinaryTree();
        threadBinaryTree.setRoot(root);

        root.setLeft(node1);
        root.setRight(node4);
        node1.setLeft(node2);
        node1.setRight(node3);
        node4.setLeft(node5);
        node4.setRight(node6);
        node1.setParent(root);
        node4.setParent(root);
        node2.setParent(node1);
        node3.setParent(node1);
        node5.setParent(node4);
        node6.setParent(node4);
        node2.setLeft(node7);
        node2.setRight(node8);
        node7.setParent(node2);
        node8.setParent(node2);
        node3.setLeft(node9);
        node3.setRight(node10);
        node9.setParent(node3);
        node10.setParent(node3);

//        threadBinaryTree.infixThreadedList();
//        threadBinaryTree.infixOrder();

//        threadBinaryTree.preThreadedList();
//        threadBinaryTree.preOrder();

        threadBinaryTree.postThreadedList();
        threadBinaryTree.postOrder();
    }
}

class ThreadBinaryTree {
    private Node root;
    public Node pre = null;

    //前序建立线索化二叉树
    public void preThreadedList(Node node) {
        if (node == null) {
            return;
        }
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null && pre.getLeft() != node) {
            pre.setRight(node);
            pre.setRightTpe(1);
        }
        pre = node;

        if (node.getLeftType() == 0) {
            preThreadedList(node.getLeft());
        }
        preThreadedList(node.getRight());
    }

    public void preThreadedList() {
        preThreadedList(root);
    }

    //中序建立线索化二叉树
    public void infixThreadedList(Node node) {
        if (node == null) {
            return;
        }
        //先左
        infixThreadedList(node.getLeft());

        //当前
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightTpe(1);
        }
        pre = node;

        //后右
        infixThreadedList(node.getRight());
    }

    public void infixThreadedList() {
        infixThreadedList(root);
    }

    //后续建立线索化二叉树
    public void postThreadedList(Node node) {
        if (node == null) {
            return;
        }
        postThreadedList(node.getLeft());
        postThreadedList(node.getRight());

        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightTpe(1);
        }

        pre = node;
    }

    public void postThreadedList() {
        this.postThreadedList(root);
    }

    //前序线索二叉树的遍历
    public void preOrder() {
        Node node = root;
        while (node != null) {
            System.out.println(node);
            while (node.getLeftType() == 0) {
                node = node.getLeft();
                System.out.println(node);
            }
            while (node.getRightTpe() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            if (node.getLeftType() == 0) {
                node = node.getLeft();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    //中序线索二叉树的遍历
    public void infixOrder() {
        Node node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightTpe() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    //后续线索二叉树的遍历
    public void postOrder() {
        Node node = root;
        Node pre = null;

        while (node.getLeft() != null && node.getLeftType() == 0) {
            node = node.getLeft();
        }

        while (node != null) {
            if (node.getRightTpe() == 1) {
                System.out.println(node);
                pre = node;
                node = node.getRight();
            } else {
                if (node.getRight() == pre) {
                    System.out.println(node);
                    if (root == null) {
                        return;
                    }
                    pre = node;
                    node = node.getParent();
                } else {
                    //查找右节点
                    node = node.getRight();
                    while (node != null && node.getLeftType() == 0) {
                        node = node.getLeft();
                    }
                }
            }
        }

    }

    public void postOrder1() {
        //1.找后序遍历方式开始的节点
        Node node = root;
        while (node != null && node.getLeftType() == 0) {
            node = node.getLeft();
        }
        Node preNode = null;
        while (node != null) {
            //右节点是线索
            if (node.getRightTpe() == 1) {
                System.out.println(node);
                preNode = node;
                node = node.getRight();
            } else {
                //如果上个处理的节点是当前节点的右节点
                if (node.getRight() == preNode) {
                    System.out.println(node);
                    if (node == root) {
                        return;
                    }
                    preNode = node;
                    node = node.getParent();
                } else { //如果从左节点的进入则找到右子树的最左节点
                    node = node.getRight();
                    while (node != null && node.getLeftType() == 0) {
                        node = node.getLeft();
                    }
                }
            }
        }
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}

class Node {
    private final int no;
    private final String name;
    private Node left;
    private Node right;
    private int leftType;
    private int rightTpe;
    private Node parent;

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightTpe() {
        return rightTpe;
    }

    public void setRightTpe(int rightTpe) {
        this.rightTpe = rightTpe;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}