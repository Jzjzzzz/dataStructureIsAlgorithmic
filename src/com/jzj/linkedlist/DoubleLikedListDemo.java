package com.jzj.linkedlist;

/**
 * @Author Jzj
 * @Date 2022/2/23 7:46
 * @Version 1.0
 * @Message: 双向链表
 */
public class DoubleLikedListDemo {
    public static void main(String[] args) {
        DoubleHeroNode heroNode2 = new DoubleHeroNode(2, "吴勇", "玉麒麟");
        DoubleHeroNode heroNode1 = new DoubleHeroNode(1, "宋江", "及时雨");
        DoubleHeroNode heroNode3 = new DoubleHeroNode(3, "卢俊义", "智多星");
        DoubleHeroNode heroNode4 = new DoubleHeroNode(4, "林冲", "豹子头");


        //创建一个链表
        DoubleLikedList doubleLinkedList = new DoubleLikedList();

        doubleLinkedList.addByOrder(heroNode1);
        doubleLinkedList.addByOrder(heroNode2);
        doubleLinkedList.addByOrder(heroNode3);
        doubleLinkedList.addByOrder(heroNode4);

        //修改前
        System.out.println("修改前");
        doubleLinkedList.list();
        System.out.println("--------------");

        //修改
        // doubleLinkedList.update(new DoubleHeroNode(3,"自行车","智多星"));

        //删除
        // doubleLinkedList.delete(1);

        //修改后
        // System.out.println("修改后");
        // doubleLinkedList.list();
        // System.out.println("--------------");


    }

}

class DoubleLikedList {
    //先初始化一个头结点，头结点不要动，不存放具体的数据
    private DoubleHeroNode head = new DoubleHeroNode(0, "", "");


    //返回头节点
    public DoubleHeroNode getHead() {
        return head;
    }


    //显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        DoubleHeroNode temp = head.next;
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

    //添加节点到双向链表最后
    public void add(DoubleHeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        DoubleHeroNode temp = head;
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
        //形成一个双向链表
        temp.next = heroNode;
        //双向链表比单双链表多了这个修改
        heroNode.pre = temp; //指向上一个节点
    }

    //修改节点的信息，根据no编号来修改，既no编号不能改
    //1.根据newHeroNode的no来修改即可,双向链表修改跟单向链表修改一致
    public void update(DoubleHeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        DoubleHeroNode temp = head.next;
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
    //对于双向链表，我们可以直接找到要删除的这个节点
    //找到后，自我删除
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        boolean flag = false;
        DoubleHeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }

        } else {
            System.out.println("删除失败，未找到节点");
        }
    }
    //有序添加
    //第二种方式在添加时，根据排名插入到指定位置（如果已存在排名，则添加失败）
    public void addByOrder(DoubleHeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        //因为单链表，因为我们找的temp是位于添加位置的前一个节点，否则插入不了
        DoubleHeroNode temp = head;
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
            heroNode.pre = temp;
            temp.pre = heroNode;
            temp.next = heroNode;
        }
    }


}

//定义HeroNode，每个HeroNode对象就是一个节点
class DoubleHeroNode {
    //前面三个为具体数据，后面为链表指针
    public int no; //id
    public String name; //姓名
    public String nickname; //昵称
    public DoubleHeroNode next; //指向下一个节点,默认为null
    public DoubleHeroNode pre; //指向前一个节点,默认为null

    public DoubleHeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "DoubleHeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}



