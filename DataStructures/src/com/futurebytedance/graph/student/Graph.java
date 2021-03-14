package com.futurebytedance.graph.student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/14 - 21:05
 * @Description 图的创建与遍历(DFS与BFS)
 */
public class Graph {
    private final ArrayList<String> vertexList;
    private final int[][] edges;
    private int numberOfEdges;
    private boolean[] isMarked;

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        String[] vertex = {"1", "2", "3", "4", "5", "6", "7", "8"};
        for (String s : vertex) {
            graph.insertVertex(s);
        }

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        graph.showGraph();

        //DFS
        System.out.println("深度优先遍历");
        graph.depthFirstSearch();

        System.out.println();

        //BFS
        System.out.println("广度优先遍历");
        graph.broadFirstSearch();
    }

    public Graph(int n) {
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        isMarked = new boolean[n];
    }

    //DFS算法
    public void depthFirstSearch(int i) {
        System.out.print(getValueByIndex(i) + "->");
        isMarked[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isMarked[w]) {
                depthFirstSearch(w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    public void depthFirstSearch() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isMarked[i]) {
                depthFirstSearch(i);
            }
        }
    }

    //BFS算法
    public void broadFirstSearch(boolean[] isMarker, int i) {
        int u;
        int w;
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.print(getValueByIndex(i) + "=>");
        isMarker[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()) {
            u = queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isMarker[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    isMarker[w] = true;
                    queue.add(w);
                }
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void broadFirstSearch() {
        isMarked = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isMarked[i]) {
                broadFirstSearch(isMarked, i);
            }
        }
    }


    public void showGraph() {
        for (int[] ele : edges) {
            System.out.println(Arrays.toString(ele));
        }
    }

    //获取邻接矩阵的下一个节点
    public int getFirstNeighbor(int i) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[i][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //获取邻接矩阵的下下个节点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    public void setNumberOfEdges(int numberOfEdges) {
        this.numberOfEdges = numberOfEdges;
    }

    public void insertVertex(String value) {
        vertexList.add(value);
    }

    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numberOfEdges++;
    }
}
