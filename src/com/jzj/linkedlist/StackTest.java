package com.jzj.linkedlist;

import java.util.Stack;

/**
 * @Author Jzj
 * @Date 2022/2/23 6:30
 * @Version 1.0
 * @Message:
 */
public class StackTest {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.add("1");
        stack.add("2");
        stack.add("3");
        System.out.println(stack.empty());
        System.out.println(stack.pop());
        System.out.println(stack.empty());
        System.out.println(stack.pop());
        System.out.println(stack.empty());
        System.out.println(stack.pop());
        System.out.println(stack.empty());
    }
}
