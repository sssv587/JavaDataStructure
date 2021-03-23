package com.futurebytedance.prim.student;

import java.util.Arrays;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/23 - 23:04
 * @Description 普利姆算法
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertx = data.length;
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};
        MGraph mGraph = new MGraph(vertx);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, vertx, data, weight);
        minTree.showGraph(mGraph);
        minTree.graphOfPrim(mGraph, 4);
    }
}

class MinTree {
    public static int sum = 0;

    public void createGraph(MGraph graph, int vertx, char[] data, int[][] weight) {
        for (int i = 0; i < vertx; i++) {
            graph.data[i] = data[i];
            System.arraycopy(weight[i], 0, graph.weight[i], 0, vertx);
        }
    }

    public void showGraph(MGraph graph) {
        for (int i = 0; i < graph.vertx; i++) {
            for (int j = i; j < graph.vertx; j++) {
                if (graph.weight[i][j] < 1000) {
                    System.out.print(graph.data[i] + "->" + graph.data[j] + " " + graph.weight[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    //Prim算法
    public void graphOfPrim(MGraph graph, int v) {
        int[] visited = new int[graph.vertx];
        visited[v] = 1;
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        for (int i = 1; i < graph.vertx; i++) {
            for (int j = 0; j < graph.vertx; j++) {
                if (visited[j] == 1) {
                    for (int k = 0; k < graph.vertx; k++) {
                        if (visited[j] == 1 && visited[k] == 0 && minWeight > graph.weight[j][k]) {
                            minWeight = graph.weight[j][k];
                            h1 = j;
                            h2 = k;
                        }
                    }
                }
            }
            visited[h2] = 1;
            sum += graph.weight[h1][h2];
            minWeight = 10000;
            System.out.println(graph.data[h1] + "->" + graph.data[h2] + "\t" + graph.weight[h1][h2]);
        }
        System.out.println("最小生成树的权值为:" + sum);
    }
}

class MGraph {
    int vertx;
    char[] data;
    int[][] weight;

    public MGraph(int vertx) {
        this.vertx = vertx;
        data = new char[vertx];
        weight = new int[vertx][vertx];
    }
}