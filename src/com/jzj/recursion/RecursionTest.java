package com.jzj.recursion;

/**
 * @Author Jzj
 * @Date 2022/3/2 0:22
 * @Version 1.0
 * @Message:
 */
public class RecursionTest {
    public static void main(String[] args) {
        int factorial = factorial(4);
        System.out.println(factorial);
    }

    //阶乘问题
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;// 1 * 2 * 3
        }
    }
}
