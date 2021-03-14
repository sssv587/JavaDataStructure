package com.futurebytedance.graph.teacher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/14 - 17:04
 * @Description 图的创建与遍历
 */
public class Graph {
    private final ArrayList<String> vertexList; //存储顶点集合
    private final int[][] edges; //存储图对应的邻接矩阵
    private int numOfEdges; //表示边的数目
    //定义一个数组boolean[],记录某个节点是否被访问过
    private boolean[] isVisited;

    public static void main(String[] args) {
        int n = 8;//节点的个数
        //String[] vertexes = {"A", "B", "C", "D", "E"};
        String[] vertexes = {"1", "2", "3", "4", "5", "6", "7", "8"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for (String vertexValue : vertexes) {
            graph.insertVertex(vertexValue);
        }
        //添加边
        //A-B A-C B-C B-D B-E
//        graph.insertEdge(0, 1, 1); // A-B
//        graph.insertEdge(0, 2, 1); //
//        graph.insertEdge(1, 2, 1); //
//        graph.insertEdge(1, 3, 1); //
//        graph.insertEdge(1, 4, 1); //
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        //显式一把邻接矩阵
        graph.showGraph();

        //DFS遍历
        System.out.println("深度遍历!");
        graph.deptFirstSearch(); //A->B->C->D->E->
        System.out.println();

        //BFS遍历
        System.out.println("广度优先!");
        graph.broadFirstSearch();//A=>B=>C=>D=>E=>
    }

    //构造器
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        isVisited = new boolean[n];
        numOfEdges = 0;
    }

    //得到第一个邻接节点的下标 w

    /**
     * @param index 角标
     * @return 如果存在就返回对应的下标
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    //i第一次就是0
    private void deptFirstSearch(boolean[] isVisited, int i) {
        //首先我们访问该节点,输出
        System.out.print(getValueByIndex(i) + "->");
        //将节点设置为已经访问
        isVisited[i] = true;
        //查找结点v的第一个邻接结点w
        int w = getFirstNeighbor(i);
        while (w != -1) { //说明有
            if (!isVisited[w]) {
                deptFirstSearch(isVisited, w);
            }
            //如果w节点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    //对DFS进行一个重载,遍历我们所有的节点,并进行DFS
    public void deptFirstSearch() {
        //遍历所有的节点进行DFS[回溯]
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                deptFirstSearch(isVisited, i);
            }
        }
    }

    //图中常用的方法
    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //对一个节点进行广度优先遍历的方法
    private void broadFirstSearch(boolean[] isVisited, int i) {
        int u;//表示队列的头结点对应的下标
        int w;//邻接节点w
        //队列,记录节点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        //访问节点，输出节点信息
        System.out.print(getValueByIndex(i) + "=>");
        //标记为已访问
        isVisited[i] = true;
        //将节点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            //取出队列的头结点下标
            u = queue.removeFirst();
            //得到第一个邻接节点的下标w
            w = getFirstNeighbor(u);
            while (w != -1) { //找到了
                //是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    //标记已经访问
                    isVisited[w] = true;
                    //入队列
                    queue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个邻接点
                w = getNextNeighbor(u, w); //体现出广度优先
            }
        }
    }

    //遍历所有的节点，都进行广度优先搜索
    public void broadFirstSearch() {
        isVisited = new boolean[8];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                broadFirstSearch(isVisited, i);
            }
        }
    }

    //返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回节点i(下标)对应的数据 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边

    /**
     * @param v1     v1表示点的下标即是第几个顶点 "A"-"B" "A"->0 "B"->1
     * @param v2     v2表示第二个顶点对应的下标
     * @param weight 表示权值(可以理解为1是有关系,0是无关系)
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
