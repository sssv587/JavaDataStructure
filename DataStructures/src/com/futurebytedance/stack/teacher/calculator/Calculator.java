package com.futurebytedance.stack.teacher.calculator;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/11 - 16:44
 * @Description 栈实现综合计算器
 */
public class Calculator {
    public static void main(String[] args) {
        //完成表达式的运算
        String expression = "300+2*9-2";
        //创建两个栈，数栈，符号栈
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack operatorStack = new ArrayStack(10);
        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int operator = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch
        String keepNumber = "";
        //开始while循环的扫描expression
        while (true) {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operatorStack.isOperator(ch)) {
                //判断当前的符号栈是否为空
                if (!operatorStack.isEmpty()) {
                    //如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符
                    //就需要从数栈中pop出两个数,在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈，
                    //如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈
                    if (operatorStack.priority(ch) <= operatorStack.priority(operatorStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operator = operatorStack.pop();
                        res = numStack.cal(num1, num2, operator);
                        //把运算结果入数栈
                        numStack.push(res);
                        //然后将当前符号入符号栈
                        operatorStack.push(ch);
                    } else {
                        //如果当前的操作符优先级大于栈中的操作符，就直接入符号栈
                        operatorStack.push(ch);
                    }
                } else {
                    //如果为空直接入符号栈
                    operatorStack.push(ch);
                }
            } else {//如果是数，则直接入数栈
//                numStack.push(ch - 48);
                //分析思路
                //1.当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                //2.在处理数，需要向expression的表达式后的index后再看以为，如果是数就进行扫描，如果是符号才入栈
                //3.因此我们需要定义一个变量字符串，用于拼接

                //处理多位数
                keepNumber += ch;

                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNumber));
                } else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    //注意是看后面一位，不是index++
                    if (operatorStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果最后一位是运算符，则入栈
                        numStack.push(Integer.parseInt(keepNumber));
                        //重要的！！！,keepNum清空
                        keepNumber = "";
                    }
                }
            }
            //让index+1，并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
        while (true) {
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字【结果】
            if (operatorStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            operator = operatorStack.pop();
            res = numStack.cal(num1, num2, operator);
            numStack.push(res);
        }
        //将数栈的最后数，pop出，就是结果
        int res2 = numStack.pop();
        System.out.println("最后的结果是:" + res2);
    }
}

//定义一个ArrayStack表示栈
//需要扩展功能
class ArrayStack {
    private final int maxSize;//栈的大小
    private final int[] stack;//数组，数组模拟栈，数据就放在该数组
    private int top = -1;

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //增加一个方法，可以返回当前栈的值，但不是真正的pop
    public int peek() {
        return stack[top];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈-push
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈-pop,将栈顶的数据返回
    public int pop() {
        //先判断栈是否空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显式栈的情况[遍历栈]，遍历时，需要从栈顶开始显式数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        //需要从栈顶开始显式数据
        for (int i = top; i >= 0; i--) {
            System.out.println("stack[" + i + "]=" + stack[i]);

        }
    }

    //返回运算符的优先级，优先级是程序员来确定的，优先级使用数字表示
    //数字越大，则优先级越高。
    public int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1;//假定目前的表达式只有 + - * /
        }
    }

    //判断是不是一个运算符
    public boolean isOperator(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int operator) {
        int result = 0;//result用于存放计算的结果
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;//注意顺序
                break;
            case '*':
                result = num1 * num2;
                break;
            case '\\':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }
}