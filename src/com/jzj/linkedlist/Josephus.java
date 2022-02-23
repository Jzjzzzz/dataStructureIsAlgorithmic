package com.jzj.linkedlist;

/**
 * @Author Jzj
 * @Date 2022/2/23 10:29
 * @Version 1.0
 * @Message:
 */
public class Josephus {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        //测试小孩出圈是否正确
        circleSingleLinkedList.countBoy(1,2,5);
    }
}
//创建一个环形的单向链表
class CircleSingleLinkedList{
    //创建一个first节点，当前没有编号
    private Boy first = null;

    //添加小孩节点，构建成一个环形的队列
    public void addBoy(int nums){
        if(nums<1){
            System.out.println("num的值不正确");
            return;
        }
        Boy curBoy = null; //辅助指针，帮助构建环形链表
        //使用for来创建我们的环形链表
        for (int i = 1;i<=nums;i++){
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if(i==1){
                first = boy;
                first.setNext(first); //构成环
                curBoy = first; //让curBoy指向第一个小孩
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }
    //遍历当前的环形链表
    public void showBoy(){
        if(first ==null){
            System.out.println("链表为空");
            return;
        }
        Boy curBoy = first;
        do{
            System.out.println(curBoy.getNo());
            curBoy =curBoy.getNext();
        }
        while (first != curBoy);
    }

    /**
     * 根据用户的输入，计算出小孩出圈的顺序
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初由多少小孩在圈中
     */
    public void countBoy(int startNo,int countNum,int nums){
        if(first ==null || startNo<1 || startNo> nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建要给辅助指针，帮助完成小孩出圈
        Boy helper =first;
        //需求创建一个辅助指针helper，事先应该指向环形链表的最后这个节点
        while (true){
            //说明helper指向的是最后的节点
            if(helper.getNext() ==first){
                break;
            }
            helper =helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次
        for(int j= 0;j<startNo-1;j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时的移动m-1次，然后出圈
        //这里是一个循环操作，直到圈中只有一个节点
        while (true){
            //说明圈中只有一个节点
            if(helper == first){
                break;
            }
            //让first和helper指针同时的移动countNum -1
            for(int j =0; j<countNum-1;j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //这里first指向的节点，就是要出圈的小孩节点
            System.out.println("小孩出圈："+first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后留在圈中的小孩编号是："+first.getNo());
    }
}

//创建一个Boy类，表示一个节点
class Boy{
    private int no; //编号
    private Boy next; //指向下一个节点，默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
