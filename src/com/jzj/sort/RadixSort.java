package com.jzj.sort;

import java.util.Arrays;

/**
 * @Author Jzj
 * @Date 2022/3/26 16:02
 * @Version 1.0
 * @Message: 基数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        int arr[] = {53,3,542,748,14,214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //基数排序方法
    public static void radixSort(int [] arr){
        //根据前面的推导过程，我们可以得到最终的基数排序的代码
        //1.得到数组中最大的数的位数
        int max= arr[0];
        for(int i=1; i<arr.length;i++){
            if(arr[i]>max){
                max =arr[i];
            }
        }
        int maxLength=(max+"").length();

        for(int i=0,n=1;i<maxLength;i++,n*=10){
            //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
            //二维数组包含10个一维数组,为了防止放入数的时候，数据溢出，则每个一维数组，大小定位arr.length
            //基数排序是使用空间换时间的经典算法
            int[][] bucket = new int[10][arr.length];
            //为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的数据个数
            int [] bucketElementCounts = new int[10];
            //第一轮(针对每个元素的个位进行排序处理)
            for(int j =0; j<arr.length;j++){
                //取出每个元素的个位的值
                int digitOfElement = arr[j]/n %10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序(一维数组的下标依次取出数据,放入原来数组)
            int index = 0;
            //遍历每一桶，并将桶中数据，放入到原数组
            for(int k=0;k<bucketElementCounts.length;k++){
                //如果桶中，有数据，我们才放入到原数组
                if(bucketElementCounts[k]!=0){
                    //循环该桶即第k个桶(即第k个一维数组),放入
                    for(int l = 0;l<bucketElementCounts[k];l++){
                        //取出元素放入到arr
                        arr[index++] =bucket[k][l];
                    }
                }
                //每轮处理后，需要将每个bucketElementCounts[k] = 0 !!!
                bucketElementCounts[k] = 0;
            }
        }
        }
}
