package com.futurebytedance.tree.student;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/23 - 23:57
 * @Description 二叉树的前中后序遍历、查找以及删除节点
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        Node root = new Node(1, "宋江");
        Node node2 = new Node(2, "吴用");
        Node node3 = new Node(3, "卢俊义");
        Node node4 = new Node(4, "林冲");
        Node node5 = new Node(5, "关胜");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        //遍历
        binaryTree.preOrder();//1,2,3,5,4
        binaryTree.infixOrder();//2,1,5,3,4
        binaryTree.postOrder();//2,5,4,3,1

        System.out.println("***********************************************");

        //查找
        Node resNode = binaryTree.preOrderSearch(5);
        System.out.println(resNode);

        resNode = binaryTree.infixOrderSearch(4);
        System.out.println(resNode);

        resNode = binaryTree.postOrderSearch(5);
        System.out.println(resNode);

        System.out.println("***********************************************");

        //删除
        binaryTree.delNode(2);

        binaryTree.delNode1(3);
    }
}

class BinaryTree {
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("此二叉树不存在!");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("此二叉树不存在!");
        }
    }

    //后序遍历
    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("此二叉树不存在!");
        }
    }

    //前序查找
    public Node preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        }
        return null;
    }

    //中序查找
    public Node infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        }
        return null;
    }

    //后序查找
    public Node postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        }
        return null;
    }

    //删除节点方式1
    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                Node node = root.postOrderSearch(no);
                if (node != null) {
                    root.delNode(no);
                    System.out.println("删除成功!");
                    this.infixOrder();
                } else {
                    System.out.println("要删除的节点不存在!");
                }
            }
        } else {
            System.out.println("二叉树不存在!");
        }
    }

    //删除节点方式二
    public void delNode1(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                Node node = root.postOrderSearch(no);
                if (node != null) {
                    root.delNode1(no);
                    System.out.println("删除成功!");
                    this.infixOrder();
                } else {
                    System.out.println("要删除的节点不存在!");
                }
            }
        } else {
            System.out.println("二叉树不存在!");
        }
    }
}

class Node {
    private final int no;
    private final String name;
    private Node left;
    private Node right;

    public int getNo() {
        return no;
    }

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历:根左右
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历:左根右
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历:左右根
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序查找
    public Node preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }
        Node node = null;
        if (this.left != null) {
            node = this.left.preOrderSearch(no);
        }
        if (node != null) {
            return node;
        }
        if (this.right != null) {
            node = this.right.preOrderSearch(no);
        }
        return node;
    }

    //中序查找
    public Node infixOrderSearch(int no) {
        Node node = null;
        if (this.left != null) {
            node = this.left.infixOrderSearch(no);
        }
        if (node != null) {
            return node;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            node = this.right.infixOrderSearch(no);
        }
        return node;
    }

    //后序查找
    public Node postOrderSearch(int no) {
        Node node = null;
        if (this.left != null) {
            node = this.left.postOrderSearch(no);
        }
        if (node != null) {
            return node;
        }
        if (this.right != null) {
            node = this.right.postOrderSearch(no);
        }
        if (this.no == no) {
            return this;
        }
        return node;
    }

    //删除节点
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    /*
     * 思考题(课后练习)
     * 如果要删除的节点是非叶子节点，现在我们不希望将该非叶子节点为根节点的子树删除，需要指定规则, 假如规定如下:
     * 如果该非叶子节点A只有一个子节点B，则子节点B替代节点A
     * 如果该非叶子节点A有左子节点B和右子节点C，则让左子节点B替代节点A。
     * 请大家思考，如何完成该删除功能, 老师给出提示.(课后练习)
     * 后面在讲解 二叉排序树时，在给大家讲解具体的删除方法
     */
    public void delNode1(int no) {
        if (this.left != null && this.left.no == no) {
            if (this.left.left != null && this.left.right != null) {
                this.left.left.left = this.left.right;
                this.left = this.left.left;
            } else if (this.left.left != null || this.left.right != null) {
                this.left = (this.left.left == null) ? this.left.right : this.left.left;
            } else {
                this.left = null;
            }
            return;
        }
        if (this.right != null && this.right.no == no) {
            if (this.right.left != null && this.right.right != null) {
                this.right.left.right = this.right.right;
                this.right = this.right.left;
            } else if (this.right.left != null || this.right.right != null) {
                this.right = (this.right.left == null) ? this.right.right : this.right.left;
            } else {
                this.right = null;
            }
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }
}