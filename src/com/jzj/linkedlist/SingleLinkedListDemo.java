package com.jzj.linkedlist;

/**
 * @Author Jzj
 * @Date 2022/2/21 5:32
 * @Version 1.0
 * @Message: 单链表
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "吴勇", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "卢俊义", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

        //创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
        // singleLinkedList.add(heroNode1);
        // singleLinkedList.add(heroNode2);
        // singleLinkedList.add(heroNode3);
        // singleLinkedList.add(heroNode4);

        //排序添加
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode3);


        //显示
        singleLinkedList.list();
    }
}

class SingleLinkedList {
    //先初始化一个头结点，头结点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //添加节点到单向链表
    //当不考虑编号顺序时，找到当前链表的最后节点，将最后这个节点的next，指向新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            } else {
                //如果没有找到最后，将temp后移
                temp = temp.next;
            }

        }
        //当退出while循环时，temp就指向了链表的最后
        temp.next = heroNode;
    }
    //第二种方式在添加时，根据排名插入到指定位置（如果已存在排名，则添加失败）
    public void addByOrder(HeroNode heroNode){
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        //因为单链表，因为我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false; //标志添加的编号是否存在，默认为false
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no > heroNode.no){ //位置找到，就在temp的后面插入
                break;
            }else if(temp.next.no == heroNode.no){ //说明希望添加的heroNode的编号已经存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag的值
        if(flag){
            System.out.println("准备插入的编号已经存在");
        }else{
            //插入到链表中
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;

        }
    }
}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
