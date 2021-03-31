package com.futurebytedance.floyd.student;

import java.util.Arrays;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/31 - 16:16
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

        Graph graph = new Graph(vertex, matrix);
        graph.floyd();
        graph.showGraph();
    }
}


class Graph {
    private final char[] vertex;
    private final int[][] dis;
    private final int[][] pre;

    public Graph(char[] vertex, int[][] dis) {
        this.vertex = vertex;
        this.dis = dis;
        pre = new int[vertex.length][vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    public void showGraph() {
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.print("[" + vertex[i] + "," + vertex[j] + "," + dis[i][j] + "]" + " ");
            }
            System.out.println();
        }

        System.out.println();

        for (char i : vertex) {
            for (char j : vertex) {
                System.out.print("[" + i + "," + j + "," + i + "]" + "\t");
            }
            System.out.println();
        }
    }

    public void floyd() {
        int len;
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                for (int k = 0; k < vertex.length; k++) {
                    len = dis[j][i] + dis[i][k];
                    if (len < dis[j][k]) {
                        dis[j][k] = len;
                        pre[j][k] = pre[i][k];
                    }
                }
            }
        }
    }
}
