package com.jzj.sort;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @Author Jzj
 * @Date 2022/3/27 2:11
 * @Version 1.0
 * @Message: 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
        //int[]arr={ 101 , 34 , 119 , 1 ,- 1 , 90 , 123 };
        //创建要给 80000 个的随机的数组， 在我的机器是 2 - 3 秒，比冒泡快.
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);// 生成一个[ 0 , 8000000 ) 数
        }
        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
        selectSort(arr);
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
    }

    //选择排序
    public static void selectSort(int[] arr) {
        //在推导的过程，我们发现了规律，因此，可以使用for来解决
        //选择排序时间复杂度是 O(n^ 2 )
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {// 说明假定的最小值，并不是最小
                    min = arr[j];// 重置min
                    minIndex = j;// 重置minIndex
                }
            }
            // 将最小值，放在arr[ 0 ], 即交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
