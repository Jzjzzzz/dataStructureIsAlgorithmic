package com.jzj.sort;

import java.util.Arrays;

/**
 * @Author Jzj
 * @Date 2022/3/25 14:46
 * @Version 1.0
 * @Message: 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};

        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left; //左下标
        int r = right; //右下标
        int privot = arr[(left + right) / 2]; //中轴值

        int temp = 0;
        //while循环的目的是让比privot值小的放到左边
        //比privot值大的放到右边
        while (l < r) {
            //在privot的左边一直找，找到大于等于privot值，才退出
            while (arr[l] < privot) {
                l += 1;
            }
            //在privot的右边一直找，找到小于等于privot值，才退出
            while (arr[r] > privot) {
                r -= 1;
            }
            //如果l>=r说明privot的左右两个值，已经按照左边全部是小于等于privot值，右边全部是大于等于privot值
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //如果交换完后，发现这个arr[l] ==privot值相等--，前移
            if (arr[l] == privot) {
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] ==privot值相等++，后移
            if (arr[r] == privot) {
                l += 1;
            }
        }
        //如果l==r,必须l++,r--,否则为出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        if (right > 1) {
            quickSort(arr, l, right);
        }
    }
}
