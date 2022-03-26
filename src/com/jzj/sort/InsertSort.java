package com.jzj.sort;

import java.util.Arrays;

/**
 * @Author Jzj
 * @Date 2022/3/24 16:29
 * @Version 1.0
 * @Message: 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, -1, 89};
        insertSort(arr);
    }

    public static void insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            int insertVal = arr[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
            System.out.println("第" + i + "插入");
            System.out.println(Arrays.toString(arr));
        }
    }
}
