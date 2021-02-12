package com.futurebytedance.stack.teacher.calculator;

import java.util.*;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/11 - 23:22
 * @Description 逆波兰计算器的实现
 */
public class PolandNotation {
    public static void main(String[] args) {
        //完成将一个中缀表达式转成后缀表达式的功能
        //说明
        //1.1+((2+3)*4)-5
        //2.因为直接对str进行操作不方便，因此，先将"1+((2+3)*4)-5" => 中缀表达式对应的List
        //即"1+((2+3)*4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        //3.将得到的中缀表达式对应的List=>后缀表达式对应的List
        //即ArrayList [1,+,(,(,2,+,3,),*,4,),-,5] => ArrayList [1,2,3,+,4,*,5,-]

        String expr = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expr);
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        int res = calculate(parseSuffixExpressionList);
        System.out.println(res);

        //先定义一个逆波兰表达式
        //(3+4)*5-6 => 3 4 + 5 * 6 -
        //说明为了方便，逆波兰表达式的数字和符号使用空格分开
        String expression = "30 4 + 5 * 6 -";
        //思路：
        //1.先将"3 4 + 5 * 6 -" => 放到ArrayList中
        //2.将ArrayList传递给一个方法，遍历配合栈完成计算
        List<String> rpnList = getListString(expression);
        int result = calculate(rpnList);
        System.out.println("计算的结果 ---> " + result);
    }

    //即ArrayList [1,+,(,(,2,+,3,),*,4,),-,5] => ArrayList [1,+,2,+,3,*,4,-,5]
    //方法:将得到的中缀表达式对应的List=>后缀表达式对应的List
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();//符号栈
        //说明：因为s2这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这个我们就不用Stack<String>直接使用List<String> s2
        List<String> s2 = new ArrayList<>();//存储中间结果的s2

        //遍历ls
        for (String item : ls) {
            //如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将(弹出栈，消除小括号
            } else {
                //当item的优先级小于等于栈顶运算符的优先级，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较；
                //问题：我们缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈中
                s1.push(item);
            }
        }

        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;//注意因为是存放到List，因此按顺序输出就是对应的后缀表达式List
    }

    //方法：将中缀表达式转成对应的List
    //s="1+((2+3)*4)-5"
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List，存放中缀表达式对应的内容
        List<String> ls = new ArrayList<>();
        int i = 0;//这是一个指针，用于遍历中缀表达式字符串
        StringBuilder str;//对多位数的拼接
        char c;//每遍历到一个字符，就放入到c
        do {
            //如果c是一个非数字，就需要加入ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;//i需要后移
            } else {//如果是一个数，需要考虑多位数
                str = new StringBuilder();//先将str置成 '0'[48] -> '9'[57]
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str.append(c);//拼接
                    i++;
                }
                ls.add(str.toString());
            }
        } while (i < s.length());

        return ls;
    }

    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        return new ArrayList<>(Arrays.asList(split));
    }

    //完成对逆波兰表达式的运算
    /*
        1)从左至右扫描，将3和4压入堆栈；
        2)遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
        3)将5入栈；
        4)接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
        5)将6入栈；
        6)最后是-运算符，计算出35-6的值，即29，由此得出最终结果
     */
    public static int calculate(List<String> ls) {
        //只需要创建一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历 ls
        for (String item : ls) {
            //这里使用一个正则表达式来取出数
            if (item.matches("\\d+")) {//匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res;
                switch (item) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("符号错误");
                }
                //把res入栈
                stack.push(String.valueOf(res));
            }
        }
        //最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类Operation可以返回一个运算符对应的优先级
class Operation {
    private static final int ADD = 1;
    private static final int MINUS = 1;
    private static final int MUL = 2;
    private static final int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = MINUS;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符!");
        }
        return result;
    }
}