package com.jzj.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author Jzj
 * @Date 2022/2/27 22:41
 * @Version 1.0
 * @Message: 综合计算器（逆波兰）
 */
public class PolandNotation {
    public static void main(String[] args) {


        String suffixExpression = "3 4 + 5 * 6 -";
        List<String> list = getListString(suffixExpression);

        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);

        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println(calculate(parseSuffixExpressionList));
    }

    //将中缀表达式转成对应的list
    public static List<String> toInfixExpressionList(String s) {
        ArrayList<String> list = new ArrayList<>();
        int i = 0; //这是一个指针。用于遍历s
        String str; //做多位数拼接工作
        char c; //每遍历到一个字符，就放在c中
        do {
            //如果c是一个非数字，需要加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                list.add("" + c);
                i++;
            } else { //如果是一个数，需要考虑多位数问题
                str = ""; //置空
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c; //拼接
                    i++;
                }
                list.add(str);
            }
        } while (i < s.length());
        return list;
    }

    //将一个逆波兰表达式，依次将数据和运算符，放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }


    //中缀转后缀
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>(); //符号栈
        //第二个栈没有pop操作，而且不需要先进后出特性，所以可以直接使用ArrayList代替
        ArrayList<String> s2 = new ArrayList<>(); //存储中间结果的栈
        for (String item : ls) {
            //如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号，则依次弹出s1栈顶的运算符，并压入s2,直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); //将（弹出s1栈，消除小括号
            } else {
                //当item的优先级小于等于s1栈顶运算符，将s1栈顶的运算符弹出并加入到s2中，再次转到（4.1）与s1中新的栈顶运算符相比较
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要把item压入栈中
                s1.push(item);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    public static int calculate(List<String> ls) {
        //创建栈
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String item : ls) {
            if (item.matches("\\d+")) { //匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数，并运算,再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res 入栈
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

//返回运算符优先级的类
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("传入的运算符有误");
                break;
        }
        return result;
    }
}