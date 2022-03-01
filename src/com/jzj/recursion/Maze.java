package com.jzj.recursion;

/**
 * @Author Jzj
 * @Date 2022/3/1 20:54
 * @Version 1.0
 * @Message: 迷宫
 */
public class Maze {
    public static void main(String[] args) {
        //创建一个二维数组表示迷宫
        int[][] map = new int[8][7];
        //使用1表示墙
        //上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(map[i][j] + " ");
            }
            System.out.println();
        }

        //使用递归回溯给小球找路
        setWay(map, 1, 1);
        //输出新的地图,小球走过，并标识过的地图
        System.out.println("输出新的地图,小球走过，并标识过的地图");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯来给小球找路
     *
     * @param map 表示地图
     * @param i   从哪个位置开始找
     * @param j   从哪个位置开始找
     * @return 如果找到通路，就返回true，否则返回false（如果小球能到map[6][5] 位置，则说明通路找到）
     * 规定：当map[i][j]为0表示该点没有走过，当为1表示墙，当为2表示可以走,3表示该位置已经走过但是走不通
     * 再走迷宫时，需要确定一个策略 下-》右-》上-》左， 如果该点走不通，再回溯
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) { //通路已经找到ok
            return true;
        } else {
            if (map[i][j] == 0) { //如果当前这个点还没有走过
                //按照策略 下-》右-》上-》左
                map[i][j] = 2; //假定该点是可以走通
                if (setWay(map, i + 1, j)) { //向下
                    return true;
                } else if (setWay(map, i, j + 1)) { //向右
                    return true;
                } else if (setWay(map, i - 1, j)) { //向上
                    return true;
                } else if (setWay(map, i, j - 1)) { //向左
                    return true;
                } else {
                    //说明改点是走不通的，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { //如果map[i][j] !=0,可能是1,2,3
                return false;
            }
        }
    }
}
