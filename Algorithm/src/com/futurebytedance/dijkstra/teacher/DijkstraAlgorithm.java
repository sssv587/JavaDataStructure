package com.futurebytedance.dijkstra.teacher;

import java.util.Arrays;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/28 - 16:55
 * @Description 迪杰斯特拉算法
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        //创建Graph
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        //迪杰斯特拉算法
        graph.dsj(6);
        graph.showDijkstra();
    }
}


class Graph {
    private final char[] vertex;//顶点数组
    private final int[][] matrix;//邻接矩阵
    private VisitedVertex vv; //已经访问的顶点的集合

    //构造器
    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }


    //显示结果
    public void showDijkstra() {
        vv.show();
    }

    //显示图
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    //迪杰斯特拉算法实现

    /**
     * @param index 表示出发顶点对应的下标
     */
    public void dsj(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);//更新index顶点到周围顶点的距离和前驱顶点
        for (int j = 1; j < vertex.length; j++) {
            index = vv.updateArr();//选择并返回新的访问顶点
            update(index);//更新index下标顶点到周围顶点的距离和前驱顶点
        }
    }

    //更新index下标顶点到周围顶点的距离和前驱顶点
    private void update(int index) {
        int len;
        //根据遍历我们的邻接矩阵的matrix[index]行
        for (int j = 0; j < matrix[index].length; j++) {
            //len含义是：出发顶点到index顶点的距离加上从index顶点到j顶点的距离的和
            len = vv.getDis(index) + matrix[index][j];
            //如果j这个顶点没有被访问过，并且len小于出发顶点到j顶点的距离，就需要更新
            if (!vv.in(j) && len < vv.getDis(j)) {
                vv.updatePre(j, index); //更新j顶点的前驱为index顶点
                vv.updateDis(j, len);//更新出发顶点到j顶点的距离
            }
        }
    }
}


class VisitedVertex {
    //记录各个顶点是否访问过 1表示访问过，0未访问，会动态更新
    public int[] already_arr;
    //每个下标对应的值为前一个顶点下标，会动态更新
    public int[] pre_visited;
    //记录出发顶点到其他所有顶点的距离，比如G为出发顶点，就会记录G到其他顶点的距离，会动态更新，求的最短路径就会存放到dis
    public int[] dis;

    //构造器

    /**
     * @param length 顶点的个数
     * @param index  出发顶点对应的下标,比如G顶点，下标就是6
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化，dis数组
        Arrays.fill(dis, 65535);
        this.already_arr[index] = 1;//设置出发顶点被访问过
        this.dis[index] = 0; //设置出发顶点的访问距离为0
    }

    /**
     * 功能：判断index顶点是否被访问过
     *
     * @param index 角标
     * @return 如果访问过，就返回true，否则返回false
     */
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 功能：更新出发顶点到index顶点的距离
     *
     * @param index 角标
     * @param len   距离
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 功能：更新pre顶点的前驱节点为index的节点
     *
     * @param pre   当前节点
     * @param index 前驱节点
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * 功能：返回出发顶点到index顶点的距离
     *
     * @param index 节点
     * @return 距离
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点，比如这里的G完后，就是A作为新的访问顶点(注意不是出发顶点)
     *
     * @return 返回index
     */
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        //更新index顶点被访问过
        already_arr[index] = 1;
        return index;
    }

    //显示最后的结果
    //即将三个数组的情况输出
    public void show() {
        System.out.println("=============================================");
        //输出already_arr
        for (int i : already_arr) {
            System.out.print(i + "\t");
        }

        System.out.println();

        //输出pre_visited
        for (int i : pre_visited) {
            System.out.print(i + "\t");
        }

        System.out.println();

        //输出dis
        for (int i : dis) {
            System.out.print(i + "\t");
        }

        System.out.println();

        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "(" + i + ")\t");
            } else {
                System.out.print("N ");
            }
            count++;
        }
        System.out.println();
    }
}