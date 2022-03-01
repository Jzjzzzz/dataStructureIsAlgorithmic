package com.jzj.recursion;

/**
 * @Author Jzj
 * @Date 2022/3/1 23:40
 * @Version 1.0
 * @Message: 八皇后回溯
 */
public class Queue8 {
    //定义一个max表示共有多少个皇后
    int max =8;
    //定义数组array，保存皇后放置位置的结果
    int [] array= new int [max];

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
    }



    //编写一个方法，放置第n个皇后
    //特别注意：check是每一次递归时，进入到check中都有for循环，因此会有回溯
    private void check(int n){
        if(n==max){ //n =8时其实在放第九个，说明前面八个已经放好了，输出
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i =0;i<max; i++){
            //先把当前这个皇后n,放在改行的第一列
            array[n] =i;
            if(judge(n)){ //不冲突
                //接着放n+1个皇后，既开始递归
                check(n+1);
            }
            //如果冲突，就继续执行array[n] =i;既将第n个皇后，放置在本行的后移的一个位置
        }
    }



    /**
     * 查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
     * @param n 表示第N个皇后
     * @return
     */
    private boolean judge(int n){
        for (int i=0;i<n;i++){
            //array[i] == array[n] 表示判断第n个皇后是否和前面的n-1个皇后在同一列
            //同一行不需要判断，因为n每次都递增1不会相同
            //Math.abs(n-i) == Math.abs(array[n] -array[i]) 表示是否在同一斜线上
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] -array[i])){
                return false;
            }
        }
        return true;
    }

    //输出皇后拜访位置的结果
    private void print(){
        for (int i =0;i<array.length;i++){
            System.out.print(array[i]+ " ");
        }
        System.out.println();
    }
}
