package com.futurebytedance.dijkstra.student;

import java.util.Arrays;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/28 - 18:02
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
        Graph graph = new Graph(vertex, matrix);
        graph.dijkstra(6);
        graph.showGraph();
    }
}

class Graph {
    private final char[] vertex;
    private final int[][] matrix;
    private VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph() {
        vv.show();
    }

    public void dijkstra(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);
        for (int i = 1; i < vertex.length; i++) {
            index = vv.updateArr();
            update(index);
        }
    }

    private void update(int index) {
        int len;
        for (int i = 0; i < matrix[index].length; i++) {
            len = vv.getDistance(index) + matrix[index][i];
            if (!vv.in(i) && len < vv.getDistance(i)) {
                vv.updatePre(i, index);
                vv.updateDistance(i, len);
            }
        }
    }
}


class VisitedVertex {
    private final int[] already_arr;
    private final int[] pre_visited;
    private final int[] dis;

    public VisitedVertex(int length, int index) {
        already_arr = new int[length];
        pre_visited = new int[length];
        dis = new int[length];
        Arrays.fill(dis, 65535);
        already_arr[index] = 1;
        dis[index] = 0;
    }

    public int getDistance(int index) {
        return dis[index];
    }

    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    public void updateDistance(int index, int len) {
        dis[index] = len;
    }

    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        already_arr[index] = 1;
        return index;
    }

    public void show() {
        for (int ele : already_arr) {
            System.out.print(ele + "\t");
        }
        System.out.println();
        for (int ele : pre_visited) {
            System.out.print(ele + "\t");
        }
        System.out.println();
        for (int ele : dis) {
            System.out.print(ele + "\t");
        }
        System.out.println();
    }
}

