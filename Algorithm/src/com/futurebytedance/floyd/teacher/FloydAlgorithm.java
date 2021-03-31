package com.futurebytedance.floyd.teacher;

import java.util.Arrays;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/31 - 15:43
 * @Description 弗洛伊德算法
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        //创建 Graph 对象
        Graph graph = new Graph(vertex.length, matrix, vertex);
        //调用弗洛伊德算法
        graph.floyd();
        graph.show();
    }
}

//创建图
class Graph {
    private final char[] vertex;//存放顶点的数组
    private final int[][] dis;//保存，从各个顶点出发到其他顶点的距离，最后的结果也是保留在该数组中
    private final int[][] pre;//保存到达目标定点的前驱顶点

    //构造器

    /**
     * @param length 大小
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        //对pre数组初始化,注意存放的是前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    //显示pre数组和dis数组
    public void show() {
        for (int i = 0; i < dis.length; i++) {
            //先将pre数组输出
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[pre[i][j]] + " ");
            }
            System.out.println();

            //输出dis数组的一行
            for (int j = 0; j < dis.length; j++) {
                System.out.print("(" + vertex[i] + "到" + vertex[j] + "的最短路径是" + dis[i][j] + ") ");
            }
            System.out.println();
            System.out.println();
        }
    }

    //弗洛伊德算法
    public void floyd() {
        int len ;//变量保存距离
        //对中间顶点遍历,k就是中间顶点的下标 [A, B, C, D, E, F, G]
        for (int k = 0; k < dis.length; k++) {
            //从i顶点出发[A, B, C, D, E, F, G]
            for (int i = 0; i < dis.length; i++) {
                //到达j顶点 //[A, B, C, D, E, F, G]
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j];//=>求出从i顶点出发，经过k中间顶点，到达顶点j的距离
                    if (len < dis[i][j]) { //如果len小于dis[i][j]
                        dis[i][j] = len; //更新距离
                        pre[i][j] = pre[k][j];//更新前驱顶点
                    }
                }
            }
        }
    }
}
