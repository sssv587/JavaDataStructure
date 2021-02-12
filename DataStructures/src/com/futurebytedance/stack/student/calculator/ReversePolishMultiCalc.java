package com.futurebytedance.stack.student.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/12 - 16:27
 * @Description 波兰表达式完成版 中缀表达式->后缀表达式->计算
 */
public class ReversePolishMultiCalc {
    public static void main(String[] args) {
        String str = "1.5+( (\t2+3)*4)-5.5";
        List<String> lastExpression = middleExpressionToLast(deleteBlank(str));
        System.out.println(lastExpression);
        double result = calculator(lastExpression);
        System.out.println(result);
    }

    //取出字符串中的空格符
    public static String deleteBlank(String str) {
        return str.replaceAll("[ \t\n]+", "");
    }

    //将中缀表达式转换为后缀表达式
    public static List<String> middleExpressionToLast(String str) {
        //初始化符号栈
        Stack<String> s1 = new Stack<>();
        //初始化中缀表达式
        ArrayList<String> list = new ArrayList<>();
        StringBuilder ls = new StringBuilder();
        int index = 0;

        do {
            String ele = str.substring(index, index + 1);
            if (isCharacter(ele)) {//如果是符号
                if (s1.size() == 0) {
                    s1.push(ele);
                } else if (ele.equals("(")) {
                    s1.push(ele);
                } else if (ele.equals(")")) {
                    while (!s1.peek().equals("(")) {
                        list.add(s1.pop());
                    }
                    s1.pop();
                } else if (Priority.getPriority(ele.charAt(0)) <= Priority.getPriority(s1.peek().charAt(0))) {
                    list.add(s1.pop());
                    s1.push(ele);
                } else {
                    s1.push(ele);
                }
            } else {
                while (index < str.length() - 1 && (".".equals(str.substring(index + 1, index + 2)) || ((str.charAt(index + 1)) >= 48 && str.charAt(index + 1) <= 57))) {
                    ls.append(ele);
                    index++;
                    ele = str.substring(index, index + 1);
                }
                if (ls.length() > 0) {
                    ls.append(ele);
                    list.add(ls.toString());
                    ls = new StringBuilder();
                } else {
                    list.add(ele);
                }
            }
            index++;
        } while (index <= str.length() - 1);
        list.add(s1.pop());
        return list;
    }

    //判断符号
    public static boolean isCharacter(String ele) {
        return ele.equals("+") || ele.equals("-") || ele.equals("*") || ele.equals("/") || ele.equals("(") || ele.equals(")");
    }


    //计算后缀表达式
    public static double calculator(List<String> list) {
        Stack<Double> numStack = new Stack<>();
        for (String ele : list) {
            if (ele.matches("[0-9.]+")) {
                numStack.push(Double.parseDouble(ele));
            } else {
                Double num2 = numStack.pop();
                Double num1 = numStack.pop();
                double result = getResult(num1, num2, ele);
                numStack.push(result);
            }
        }
        return numStack.pop();
    }

    //计算
    public static double getResult(double num1, double num2, String operator) {
        double result = 0;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                System.out.println("此运算符不存在!");
        }
        return result;
    }
}


//获取符号计算优先级
enum Priority {
    ADD(0), MINUS(0), MUL(1), DIV(1);

    Priority(int id) {
        this.id = id;
    }

    public int id;

    public static int getPriority(char s) {
        int result = 0;
        switch (s) {
            case '+':
                result = ADD.id;
                break;
            case '-':
                result = MINUS.id;
                break;
            case '*':
                result = MUL.id;
                break;
            case '/':
                result = DIV.id;
                break;
            default:
                result = -1;
                break;
        }
        return result;
    }
}