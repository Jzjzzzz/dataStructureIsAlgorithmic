package com.jzj.search;

/**
 * @Author Jzj
 * @Date 2022/3/27 15:28
 * @Version 1.0
 * @Message: 插值查找算法
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int [] arr=new int[100];
        for (int i=1;i<100;i++){
            arr[i] = i+1;
        }
        int i = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println(i);
    }
    /**
     * 插值查找算法
     * 说明：数组必须有序
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，如果没有找到就返回一个-1
     */
    public static int insertValueSearch(int[]arr,int left,int right,int findVal){
        //注意:findVal<arr[0] || findVal>arr[arr.length-1]必须需要，否则我们得到的mid可能越界
        if(left>right || findVal<arr[0] || findVal>arr[arr.length-1]) return -1;
        //求出mid
        int mid = left+(right-left)*(findVal-arr[left])/(arr[right]-arr[left]);
        int midVal = arr[mid];
        if(findVal>midVal){
            return insertValueSearch(arr,mid+1,right,findVal);
        }
        else if(findVal<midVal){
            return insertValueSearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }
}
