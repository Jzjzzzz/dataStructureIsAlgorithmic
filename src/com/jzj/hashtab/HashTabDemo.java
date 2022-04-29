package com.jzj.hashtab;

import java.util.Scanner;

/**
 * @Author Jzj
 * @Date 2022/4/28 17:10
 * @Version 1.0
 * @Message:
 */
public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add:");
            System.out.println("list:");
            System.out.println("exit:");
            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id,name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }

    }
}

//创建HashTab 管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArrays;
    private int size; //表示有多少条链表

    public HashTab(int size) {
        this.size = size;
        //初始化empLinkedListArrays
        empLinkedListArrays = new EmpLinkedList[size];
        for (int i =0;i<size;i++){
            empLinkedListArrays[i] = new EmpLinkedList();
        }
    }
    public void add(Emp emp){
        //根据员工的id，得到该员工应当添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        empLinkedListArrays[empLinkedListNo].add(emp);
    }

    //编写散列函数，使用一个简单取模法
    private int hashFun(int id) {
        return id % size;
    }

    //遍历所有的链表,遍历hashtab
    public void list(){
        for(int i =0;i<size;i++){
            empLinkedListArrays[i].list();
        }
    }

}

//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next; //默认为空
    public Emp(int id,String name){
        super();
        this.id=id;
        this.name = name;
    }
}

//创建EmpLinkedList,表示链表
class EmpLinkedList{
    //头指针，执行第一个Emp，因此我们这个链表的head，是直接指向第一个Emp
    private Emp head; //默认空

    //1.假定，当添加雇员时，id是自增长，即id的分配总是从小到大
    //因此我们将该雇员直接加入到本链表的最后即可
    public void add(Emp emp){
        //如果进来的是第一个
        if(head ==null){
            head = emp;
            return;
        }
        //不是第一个，则使用一个辅助指针，帮助定位到最后
        Emp curEmp = head;
        while (true){
            if(curEmp.next ==null){
                break;
            }
            curEmp = curEmp.next; //后移
        }
        curEmp.next = emp;
    }
    //遍历链表
    public void list(){
        if(head==null){
            System.out.println("链表为空");
            return;
        }
        System.out.println("当前链表的信息为：");
        Emp curEmp = head;
        while (true){
            System.out.println(curEmp.id+":"+curEmp.name);
            if(curEmp.next==null){
                return;
            }
            curEmp =curEmp.next;
        }
    }
}