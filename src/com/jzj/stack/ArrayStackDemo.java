package com.jzj.stack;

/**
 * @Author Jzj
 * @Date 2022/2/27 17:38
 * @Version 1.0
 * @Message: 数组模拟栈
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试一下ArrayStack 是否正确
        ArrayStack arrayStack = new ArrayStack(4);
        arrayStack.push(2);
        arrayStack.push(4);
        arrayStack.push(6);
        arrayStack.push(8);
        //遍历
        arrayStack.list();
        //测试出栈
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());


    }
}

//定义一个ArrayStack 表示栈
class ArrayStack {
    private int maxSize; //栈的大小
    private int[] stack; //数组，数组模拟栈，数据就放在该数组中
    private int top = -1; //top表示栈顶，初始化为-1

    public ArrayStack(int maxSize) {
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

}
