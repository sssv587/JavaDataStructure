package com.futurebytedance.kruskal.teacher;

import java.util.Arrays;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/26 - 14:45
 * @Description Kruskal算法
 */
public class Kruskal {
    private int edgeNum; //边的个数
    private char[] vertexes; //顶点数组
    private int[][] matrix;//邻接矩阵
    //使用INF表示两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};
        Kruskal kruskal = new Kruskal(vertexes, matrix);
        kruskal.print();

//        EData[] edges = kruskal.getEdges();
//        System.out.println(Arrays.toString(edges)); //没有排序
//        kruskal.sortEdge(edges);
//        System.out.println(Arrays.toString(edges)); //排序后
        kruskal.kruskal();
    }

    //构造器
    public Kruskal(char[] vertexes, int[][] matrix) {
        //初始化顶点数和边的个数
        int vlen = vertexes.length;

        //初始化顶点
        this.vertexes = new char[vlen];
        for (int i = 0; i < vertexes.length; i++) {
            this.vertexes[i] = vertexes[i];
        }

        //初始化边，使用的是复制拷贝的方式
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        //统计边的条数
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
        System.out.println(edgeNum);
    }

    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为:");
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = 0; j < vertexes.length; j++) {
                System.out.printf("%12d\t", matrix[i][j]);
            }
            System.out.println();//换行
        }
    }

    //对边进行排序处理，冒泡排序

    /**
     * 功能：对边进行排序处理，冒泡排序
     *
     * @param edges 边的集合
     */
    private void sortEdge(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - i - 1; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * @param ch 顶点的值，比如'A','B'等
     * @return 返回ch顶点对应的下标，如果找不到，返回-1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == ch) {
                return i;
            }
        }
        //找不到，返回-1
        return -1;
    }

    /**
     * 功能：获取图中的边，放到EData[]数组中，后面我们需要遍历该数组
     * 是通过matrix邻接矩阵来获取
     * EData[] 形式 [['A','B',12],['B','F',7],...]
     *
     * @return 数组
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = i + 1; j < vertexes.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexes[i], vertexes[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    public void kruskal() {
        int index = 0;//表示最后结果数组的索引
        int[] ends = new int[edgeNum];//用于保存"已有最小生成树"中的每个顶点在最小生成树中的终点
        //创建结果数组，保存最后的最小生成树
        EData[] results = new EData[edgeNum];

        //获取图中 所有的边的集合，一共有12条边
        EData[] edges = getEdges();
        System.out.println("图的边的集合=" + Arrays.toString(edges) + " 共" + edges.length);//12

        //按照边的权值大小进行排序(从小到大)
        sortEdge(edges);

        //遍历edges 数组，将边添加到最小生成树时，判断是准备加入的边是否形成了回路，如果没有，就加入results，否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i条边的第一个顶点(起点)
            int p1 = getPosition(edges[i].start);//p1=4
            //获取到第i条边的第二个顶点(终点)
            int p2 = getPosition(edges[i].end);//p2=5

            //获取p1这个顶点在已有的最小生成树中的终点
            int m = getEnd(ends, p1);//m=4
            //获取p2这个顶点在已有的最小生成树中的终点
            int n = getEnd(ends, p2);//n=5
            //是否构成回路
            if (m != n) { //没有构成回路
                ends[m] = n; //设置m 在"已有最小生成树"中的终点 <E,F> [0,0,0,0,5,0,0,0,0,0,0,0]
                results[index++] = edges[i];//有一条边加入到results数组
            }
        }

        //统计并打印"最小生成树",输出results数组
        System.out.println("最小生成树为:");
        for (int i = 0; i < index; i++) {
            System.out.println(results[i]);
        }
    }

    /**
     * 功能：获取下标为i的顶点的终点,用于后面判断两个顶点的终点是否相同
     *
     * @param ends 记录了各个顶点对应的终点是哪个,ends数组是在遍历过程中，逐步形成
     * @param i    表示传入的顶点对应的下标
     * @return 返回的就是下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) { // i = 4 [0,0,0,0,5,0,0,0,0,0,0,0]
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }
}

//创建一个类EData，它的对象实例就表示一条边
class EData {
    char start;//边的一个点
    char end;//边的另外一个点
    int weight;//边的权值

    //构造器
    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    //重写toString，便于输出边的信息
    @Override
    public String toString() {
        return "<" + start + "," + end + "> " + weight;
    }
}