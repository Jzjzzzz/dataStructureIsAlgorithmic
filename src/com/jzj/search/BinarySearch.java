package com.jzj.search;

import java.util.ArrayList;

/**
 * @Author Jzj
 * @Date 2022/3/27 14:50
 * @Version 1.0
 * @Message: 二分查找
 * 注意：使用二分查找的前提是，该数组是有序的。
 */
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1000, 1234};
        int i = binarySearch(arr, 0, arr.length, 1000);
        ArrayList<Integer> list = binarySearch2(arr, 0, arr.length, 1000);
        System.out.println(list);
        System.out.println(i);
    }

    /**
     * 二分查找算法
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，如果没有找到就返回一个-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        //当left>right时，说明递归整个数组，但是没有找到
        if (left > right) return -1;
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        //向右递归
        if (findVal > midVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        }
        //向左递归
        else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }


    //具有相同值的二分查找
    //在找到mid索引值，不要马上返回
    //向mid索引值的左边扫描，将所有满足1000，的元素的下标，加入到集合中
    //向mid索引值的右边扫描，将所有满足1000，的元素的下标，加入到集合中
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        //当left>right时，说明递归整个数组，但是没有找到
        if (left > right) new ArrayList<Integer>();
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        //向右递归
        if (findVal > midVal) {
            return binarySearch2(arr, mid + 1, right, findVal);
        }
        //向左递归
        else if (findVal < midVal) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            ArrayList<Integer> resIndexList = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp -= 1;
            }

            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp += 1;
            }
            resIndexList.add(mid);
            return resIndexList;
        }
    }
}
