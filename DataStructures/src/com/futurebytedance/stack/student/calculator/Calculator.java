package com.futurebytedance.stack.student.calculator;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/11 - 23:52
 * @Description 栈实现综合计算器---高阶版(四则+括号)
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "3+4*((50+4)+3)";
        int result = getCalculator(expression);
        System.out.println(result);
    }

    public static int getCalculator(String expression) {
        LinkedListStack numStack = new LinkedListStack(10);
        LinkedListStack operatorStack = new LinkedListStack(10);
        StringBuilder str = new StringBuilder();
        int num1 = 0;
        int num2 = 0;
        char operator = ' ';
        int res = 0;
        int index = 0;
        while (true) {
            char element = expression.charAt(index);
            //判断element是什么
            //element是符号
            if (isOperator(element)) {
                //如果符号栈为空，入栈
                if (operatorStack.getLength() == 0) {
                    operatorStack.push(new LinkedListStack.Node(element));
                } else {
                    //如果符号栈不为空
                    //1.判断有无右括号
                    if (element == ')') {
                        num2 = numStack.pop();
                        num1 = numStack.pop();
                        operator = (char) operatorStack.pop();
                        res = getResult(num1, num2, operator);
                        operatorStack.pop();
                        numStack.push(new LinkedListStack.Node(res));
                    } else if (element != '(' && operatorStack.getTopElement() != '(' && getPriority(operatorStack.getTopElement()) > getPriority(element)) {//判断运算符优先级
                        num2 = numStack.pop();
                        num1 = numStack.pop();
                        operator = (char) operatorStack.pop();
                        res = getResult(num1, num2, operator);
                        numStack.push(new LinkedListStack.Node(res));
                    } else {
                        operatorStack.push(new LinkedListStack.Node(element));
                    }
                }
            } else { //element如果是数字
                if (index < expression.length() - 1) {
                    //是符号
                    if (isOperator(expression.charAt(index + 1))) {
                        str.append(element);
                        numStack.push(new LinkedListStack.Node(Integer.parseInt(str.toString())));
                        str = new StringBuilder();
                    } else {
                        str.append(element);
                    }
                } else {
                    //如果是最后一位
                    str.append(element);
                    numStack.push(new LinkedListStack.Node(Integer.parseInt(str.toString())));
                    str = new StringBuilder();
                }
            }
            if (index == expression.length() - 1) {
                break;
            }
            index++;
        }
        while (operatorStack.getLength() > 0) {
            num2 = numStack.pop();
            num1 = numStack.pop();
            operator = (char) operatorStack.pop();
            res = getResult(num1, num2, operator);
            numStack.push(new LinkedListStack.Node(res));
        }
        return res;
    }

    public static boolean isOperator(char operator) {
        return operator == '+' || operator == '-' || operator == '*' || operator == '\\' || operator == '(' || operator == ')';
    }

    public static int getResult(int num1, int num2, char operator) {
        int res = 0;
        switch (operator) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num1 / num2;
                break;
            default:
                throw new RuntimeException("符号错误!");
        }
        return res;
    }

    public static int getPriority(int operator) {
        if (operator == '+' || operator == '-' || operator == '(' || operator == ')') {
            return 0;
        } else if (operator == '*' || operator == '/') {
            return 1;
        } else {
            throw new RuntimeException("符号错误!");
        }
    }
}

//数组实现一个栈
class LinkedListStack {
    Node head = new Node(0);
    int maxSize;

    public LinkedListStack(int maxSize) {
        this.maxSize = maxSize;
    }

    //静态内部类
    static class Node {
        int ele;
        Node next;

        public Node(int ele) {
            this.ele = ele;
        }
    }

    //入栈
    //末尾添加
    public void push(Node node) {
        if (isFull()) {
            throw new RuntimeException("栈已满，请勿重复添加!");
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    //返回最顶端的元素
    public int getTopElement() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，没有元素!");
        }
        Node temp = head.next;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp.ele;
    }

    //出栈
    //末尾删除
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，没有元素!");
        }
        Node cur = head;
        Node next = head.next;
        while (next.next != null) {
            cur = cur.next;
            next = next.next;
        }
        cur.next = null;
        return next.ele;
    }

    //空链表
    public boolean isEmpty() {
        return head.next == null;
    }

    //链表已满
    public boolean isFull() {
        return getLength() == maxSize;
    }

    //输出链表长度
    public int getLength() {
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