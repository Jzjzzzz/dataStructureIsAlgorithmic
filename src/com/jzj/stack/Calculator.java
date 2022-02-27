package com.jzj.stack;

/**
 * @Author Jzj
 * @Date 2022/2/27 21:20
 * @Version 1.0
 * @Message: 栈实现综合计算器（中缀表达式）
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "70+2*6-4";
        //创建两个栈，数栈，符号栈
        ArrayStackTow numStack = new ArrayStackTow(10);
        ArrayStackTow operatorStack = new ArrayStackTow(10);
        //定义需要的相关变量
        int index = 0; //用于扫描
        int num1 = 0;
        int num2 = 0;
        int operator = 0;
        int res = 0;
        char ch = ' '; //将每次扫描得到的char保存到ch
        String keepNum = "";//用于拼接多位数
        //开始while循环的扫描expression
        while (true) {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么，然后做出相应的处理
            if (operatorStack.isOperator(ch)) { //如果是运算符
                //判断当前的符号栈是否为空
                if (!operatorStack.isEmpty()) {
                    //如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop出两个数，
                    //再从符号栈中pop出一个符号，进行运算，将得到的结果，入数栈，然后将当前的操作符入符号栈
                    if (operatorStack.priority(ch) <= operatorStack.priority(operatorStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operator = operatorStack.pop();
                        res = operatorStack.cal(num1, num2, operator);
                        //把运算的结果入数栈
                        numStack.push(res);
                    }
                }
                //把当前运算符入符号栈
                operatorStack.push(ch);
            } else {
                keepNum += ch;
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operatorStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }


            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并允许。
        while (true) {
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字
            if (operatorStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            operator = operatorStack.pop();
            res = operatorStack.cal(num1, num2, operator);
            //把运算的结果入数栈
            numStack.push(res);
        }
        System.out.println(numStack.pop());
    }
}

//栈,扩展计算器功能
class ArrayStackTow {
    private int maxSize; //栈的大小
    private int[] stack; //数组，数组模拟栈，数据就放在该数组中
    private int top = -1; //top表示栈顶，初始化为-1

    public ArrayStackTow(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈 -push
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈满了");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈 -pop,将栈顶的数据返回
    public int pop() {
        //先判断栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈为空无法出栈");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况，遍历栈，遍历时，需要从栈顶开始显示数据
    public void list() {
        //先判断栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈为空无法出栈");
        }
        //需要从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    //返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示
    //数字越大，则优先级越高
    public int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1; //假定目前的表达式只有+，-，*，/
        }
    }

    //判断是不是一个运算符
    public boolean isOperator(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int operator) {
        int res = 0; //存放计算的结果
        switch (operator) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    //显示栈顶的值
    public int peek() {
        return stack[top];
    }
}

