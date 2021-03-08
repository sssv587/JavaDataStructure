package com.futurebytedance.binarysorttree.student;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/8 - 23:34
 * @Description 二叉排序树
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 9, 2, 1};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int element : arr) {
            binarySortTree.createBinarySortTree(new Node(element));
        }

        binarySortTree.delNode(1);
        binarySortTree.infixOrder();
    }
}

class BinarySortTree {
    Node root;

    //创建二叉排序树
    public void createBinarySortTree(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉树为空!");
        } else {
            root.infixOrder();
        }
    }

    //查找要删除的节点
    public Node getDelNode(int value) {
        if (root == null) {
            return null;
        } else {
            return root.getDelNode(value);
        }
    }

    //查找要删除的节点的父节点
    public Node getDelParentNode(int value) {
        if (root == null) {
            return null;
        } else {
            return root.getDelParentNode(value);
        }
    }

    //找到右子树的最小节点
//    public int getRightAppendNode(Node node) {
//        Node target;
//        if (node.right != null) {
//            target = node.right;
//            while (target.left != null) {
//                target = target.left;
//            }
//            delNode(target.value);
//        } else {
//            return -1;
//        }
//        return target.value;
//    }

    //找到左子树的最大节点
    public int getLeftAppendNode(Node node) {
        Node target;
        if (node.left != null) {
            target = node.left;
            while (target.right != null) {
                target = target.right;
            }
            delNode(target.value);
        } else {
            return -1;
        }
        return target.value;
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) {
            System.out.println("根节点不存在!");
        } else {
            Node targetNode = getDelNode(value);
            Node parentNode = getDelParentNode(value);
            if (targetNode == null) {
                System.out.println("未找到该节点!!!");
                return;
            }
            if (targetNode.left == null && targetNode.right == null) { //叶子节点
                if (parentNode != null) {
                    if (parentNode.left.value == targetNode.value) {
                        parentNode.left = null;
                    } else {
                        parentNode.right = null;
                    }
                    return;
                }
                root = null;
            } else if (targetNode.left != null && targetNode.right != null) {//左右子树不为空
                int leftNodeValue = getLeftAppendNode(targetNode);
                if (leftNodeValue != -1) {
                    targetNode.value = leftNodeValue;
                } else {
                    System.out.println("无法进行删除!");
                }
            } else { //处理一个子节点
                //需要考虑根节点
                if (targetNode.left != null) {
                    if (parentNode != null) {
                        //说明是左子节点
                        if (parentNode.isLeft(targetNode)) {
                            parentNode.left = targetNode.left;
                        } else {
                            parentNode.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if (parentNode != null) {
                        if (parentNode.isLeft(targetNode)) {
                            parentNode.left = targetNode.right;
                        } else {
                            parentNode.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }

            }
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

    //查找要删除节点的父节点
    public Node getDelParentNode(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        }
        if (this.value > value) {
            if (this.left != null) {
                return this.left.getDelParentNode(value);
            }
        } else {
            if (this.right != null) {
                return this.right.getDelParentNode(value);
            }
        }
        return null;
    }

    //判断是父节点的左节点还是右节点
    public boolean isLeft(Node node) {
        return this.left.value == node.value;
    }

    //获取要删除的节点
    public Node getDelNode(int value) {
        if (this.value == value) {
            return this;
        }
        if (this.value > value) {
            if (this.left != null) {
                return this.left.getDelNode(value);
            }
        } else {
            if (this.right != null) {
                return this.right.getDelNode(value);
            }
        }
        return null;
    }

    //构建二叉排序树
    public void add(Node node) {
        if (node == null) {
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
    }

    //中序遍历
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
}