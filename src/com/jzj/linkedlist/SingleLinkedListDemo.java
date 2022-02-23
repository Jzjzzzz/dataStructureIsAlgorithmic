package com.jzj.linkedlist;

import java.util.Stack;

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
        System.out.println("修改前");
        singleLinkedList.list();
        System.out.println("------------------");
        //测试修改节点
        // HeroNode newHeroNode = new HeroNode(3, "刘德华", "智多星");
        // singleLinkedList.update(newHeroNode);

        //测试删除节点
        // singleLinkedList.delete(3);

        //计算链表节点数
        // System.out.println(singleLinkedList.count());

        //倒序查找节点
        // System.out.println("查找的节点为:"+singleLinkedList.findLastIndexNode(1));

        //反转单链表
        // singleLinkedList.reverseList();

        //使用栈，反向输出单链表
        singleLinkedList.reversePrint();

        //显示
        // System.out.println("修改后");
        // singleLinkedList.list();
    }
}

class SingleLinkedList {
    //先初始化一个头结点，头结点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");


    //返回头节点
    public HeroNode getHead(){
        return head;
    }

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
    public void addByOrder(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        //因为单链表，因为我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false; //标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) { //位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) { //说明希望添加的heroNode的编号已经存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag的值
        if (flag) {
            System.out.println("准备插入的编号已经存在");
        } else {
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
        HeroNode temp = head.next;
        while (true) {
            //找到链表的最后
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;

        }
    }

    //修改节点的信息，根据no编号来修改，既no编号不能改
    //1.根据newHeroNode的no来修改即可
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false; //表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            //没有找到
            System.out.println("没有找到节点,不能修改");
        }
    }

    //删除节点
    public void delete(int no) {
        boolean flag = false;
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("删除失败，未找到节点");
        }
    }

    //计算节点个数(不包括头节点)
    public int count() {
        if (head.next == null) {
            System.out.println("链表为空");
            return 0;
        }
        HeroNode temp = head.next; //从头节点后的节点开始
        int sum = 0;
        while (true) {
            if (temp.next == null) break;
            else sum++;
            temp = temp.next;
        }
        return sum;
    }

    //查找单链表中的倒数第K个节点
    //思路：
    //1.编写一个方法，接收head节点，同时接收一个index
    //2.index表示是倒数第index个节点
    //3.先把链表从头到尾遍历，得到的链表总的长度
    //4.得到size后，我们从链表的第一个开始遍历（size-index）个，就可以得到
    //5.如果找到了，则返回该节点
    public HeroNode findLastIndexNode(int index) {
        if (head.next == null) {
            System.out.println("链表为空");
            return null;
        }
        int size = this.count(); //总的链表数(不包括头节点)
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode temp = head.next; //从头节点后的节点开始
        index = size - index; //计算需要循环几次
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }

        return temp;
    }

    //将单链表进行反转
    public void reverseList() {
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助的指针（变量），帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null; //指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while (cur != null) {
            next = cur.next; //先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next; //将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; //将cur 连接到新的链表上
            cur = next; //让cur后移
        }
        //将head.next 指向reverseHead.next,实现单链表的反转
        head.next = reverseHead.next;
    }
    //方式2,利用栈这个数据结构，逆序打印单链表
    public void reversePrint(){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        Stack<HeroNode> stack = new Stack<>(); //创建栈
        HeroNode temp = head.next;
        while (temp!= null){
            stack.add(temp); //入栈
            temp = temp.next;
        }
        while (!stack.empty()){
            System.out.println(stack.pop());
        }

    }
}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    //前面三个为具体数据，后面为链表指针
    public int no; //id
    public String name; //姓名
    public String nickname; //昵称
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
