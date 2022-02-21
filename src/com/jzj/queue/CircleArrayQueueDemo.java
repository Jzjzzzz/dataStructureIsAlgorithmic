package com.jzj.queue;

/**
 * @Author Jzj
 * @Date 2022/2/21 5:01
 * @Version 1.0
 * @Message: 环形队列
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {

    }
}
class CircleArray{
    private int maxSize; //表示数组的最大容量
    private int front; //队列头
    private int rear; //队列尾
    private int [] arr; //该数组用于存放数据，模拟队列

    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull(){
        return (rear+1)%maxSize ==front;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == maxSize;
    }

    //添加数据到队列
    public void add(int n){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列满了");
            return;
        }
        //直接将数据加入
        arr[rear] =n;
        //将rear后移，这里必须考虑取模
        rear = (rear+1)%maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否为空
        if(isEmpty()){
            throw new RuntimeException("队列为空，不能取数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1.先把front对应的值保留到一个临时变量
        //2.将front后移，考虑取模
        //3.将临时保存的变量返回
        int value =  arr[front];
        front = (front+1%maxSize);
        return value;
    }
}
