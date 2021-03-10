package com.futurebytedance.avlbinarytree.student;

import java.util.Objects;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/10 - 23:31
 * @Description 平衡二叉树
 */
public class AvlBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {10, 11, 7, 6, 8, 9};
        AvlBinaryTree avlBinaryTree = new AvlBinaryTree();
        for (int ele : arr) {
            avlBinaryTree.add(new Node(ele));
        }

        avlBinaryTree.infixOrder();
        System.out.println("当前树的高度为:" + avlBinaryTree.root.getLength());
        System.out.println("当前树的左子树高度为:" + avlBinaryTree.root.getLeftLength());
        System.out.println("当前树的右子树高度为:" + avlBinaryTree.root.getRightLength());
        System.out.println("当前树的root节点为:" + avlBinaryTree.root);

        System.out.println("删除后的结果为:");
        avlBinaryTree.delNode(10);
        avlBinaryTree.infixOrder();
    }
}

class AvlBinaryTree {
    Node root;

    //获取要删除的节点
    public Node getDeleteNode(int value) {
        if (root == null) {
            throw new RuntimeException("二叉树为空!");
        } else {
            return root.getDeleteNode(value);
        }
    }

    //获取要删除的节点的父节点
        public Node getDeleteParentNode(int value){
        if (root == null) {
            throw new RuntimeException("二叉树为空!");
        } else {
            return root.getDeleteParentNode(value);
        }
    }

    //获取右节点的最小值
    public int getRightMinValue(Node node) {
        Node target = node.right;
        while (target.left != null) {
            target = target.left;
        }
        return target.value;
    }

    //获取左节点的最大值
    public int getLeftMinValue(Node node) {
        Node target = node.left;
        while (target.right != null) {
            target = target.right;
        }
        return target.value;
    }

    public void delNode(int value) {
        Node deleteNode = getDeleteNode(value);
        Node deleteParentNode = getDeleteParentNode(value);
        if (deleteNode == null) {
            throw new RuntimeException("要删除的节点不存在!");
        } else if (deleteNode.left == null && deleteNode.right == null) {  //叶子结点
            if (deleteParentNode != null) {
                if (deleteParentNode.left != null && deleteParentNode.left == deleteNode) {
                    deleteParentNode.left = null;
                } else {
                    deleteParentNode.right = null;
                }
            } else {
                root = null;
            }
        } else if (deleteNode.left != null && deleteNode.right != null) {  //有两颗子树
            int rightMinValue = getRightMinValue(deleteNode);
            int leftMinValue = getLeftMinValue(deleteNode);
            if (rightMinValue < deleteNode.value) {
                delNode(rightMinValue);
                deleteNode.value = rightMinValue;
            } else {
                delNode(leftMinValue);
                deleteNode.value = leftMinValue;
            }
        } else {  //有一个子树
            if (deleteParentNode != null) {
                if (deleteNode.left != null) {
                    if (deleteParentNode.left != null && deleteParentNode.left == deleteNode) {
                        deleteParentNode.left = deleteNode.left;
                    } else {
                        deleteParentNode.right = deleteNode.left;
                    }
                } else {
                    if (deleteParentNode.left != null && deleteParentNode.left == deleteNode) {
                        deleteParentNode.left = deleteNode.right;
                    } else {
                        deleteParentNode.right = deleteNode.right;
                    }
                }
            } else {
                if (deleteNode.left != null) {
                    root = deleteNode.left;
                } else {
                    root = deleteNode.right;
                }
            }
        }
    }

    //构建排序二叉树
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历二叉树
    public void infixOrder() {
        if (root == null) {
            throw new RuntimeException("二叉树为空，无法遍历!");
        } else {
            root.infixOrder();
        }
    }
}

class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //获取要删除的节点的Node
    public Node getDeleteNode(int value) {
        if (this.value == value) {
            return this;
        } else if (this.value > value) {
            if (this.left != null) {
                return this.left.getDeleteNode(value);
            }
        } else {
            if (this.right != null) {
                return this.right.getDeleteNode(value);
            }
        }
        return null;
    }

    //获取要删除节点的父节点
    public Node getDeleteParentNode(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else if (this.value > value) {
            if (this.left != null) {
                return this.left.getDeleteParentNode(value);
            }
        } else {
            if (this.right != null) {
                return this.right.getDeleteParentNode(value);
            }
        }
        return null;
    }

    //进行左旋转
    private void leftRotate() {
        Node newNode = new Node(this.value);
        newNode.left = this.left;
        newNode.right = this.right.left;
        this.value = this.right.value;
        this.right = this.right.right;
        this.left = newNode;
    }

    //进行右旋转
    private void rightRotate() {
        Node newNode = new Node(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.value = this.left.value;
        this.left = this.left.left;
        this.right = newNode;
    }

    //获取当前节点的左子树的高度
    public int getLeftLength() {
        if (this.left != null) {
            return this.left.getLength();
        }
        return 0;
    }

    //获取当前节点的右子树的高度
    public int getRightLength() {
        if (this.right != null) {
            return this.right.getLength();
        }
        return 0;
    }

    //获取二叉树的高度
    public int getLength() {
        return Math.max((this.left == null) ? 0 : this.left.getLength(), (this.right == null) ? 0 : this.right.getLength()) + 1;
    }

    //构建排序二叉树
    public void add(Node node) {
        if (node == null) {
            System.out.println("当前插入节点为空");
            return;
        }
        if (this.value > node.value) {
            if (this.left == null) {
                this.left = node;
                return;
            }
            this.left.add(node);
        } else {
            if (this.right == null) {
                this.right = node;
                return;
            }
            this.right.add(node);
        }
        if (this.left != null && this.getLeftLength() - this.getRightLength() > 1) {
            if (this.left.getRightLength() > this.left.getLeftLength()) {
                this.left.leftRotate();
            }
            this.rightRotate();
        } else if (this.right != null && this.getRightLength() - this.getLeftLength() > 1) {
            if (this.right.getLeftLength() > this.right.getRightLength()) {
                this.right.rightRotate();
            }
            this.leftRotate();
        }
    }

    //中序遍历二叉树
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
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
        return this.value - o.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return value == node.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
