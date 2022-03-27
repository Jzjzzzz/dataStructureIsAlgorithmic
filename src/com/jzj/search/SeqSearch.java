package com.jzj.search;

/**
 * @Author Jzj
 * @Date 2022/3/27 14:32
 * @Version 1.0
 * @Message: 线性查找
 */
public class SeqSearch {
    public static void main(String[] args) {
        int arr[] = {1, 9, 11, -1, 34, 89};
        int i = seqSearch(arr, 9);
        System.out.println(i);
    }

    public static int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
