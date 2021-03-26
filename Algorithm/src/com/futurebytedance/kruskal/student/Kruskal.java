package com.futurebytedance.kruskal.student;

import java.util.Arrays;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/26 - 16:12
 * @Description Kruskal算法
 */
public class Kruskal {
    private static final int INF = Integer.MAX_VALUE;
    private final char[] vertexes;
    private final int[][] matrix;
    private static int edgesNum;
    private static EData[] eData;
    private static EData[] resultData;

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
        kruskal.show();
        System.out.println("结果为:");
        kruskal.kruskal();
    }

    public Kruskal(char[] vertexes, int[][] matrix) {
        this.vertexes = vertexes;
        this.matrix = matrix;
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = i + 1; j < vertexes.length; j++) {
                if (matrix[i][j] != INF) {
                    edgesNum++;
                }
            }
        }
        eData = new EData[edgesNum];
        int index = 0;
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = i + 1; j < vertexes.length; j++) {
                if (matrix[i][j] != INF) {
                    eData[index++] = new EData(vertexes[i], vertexes[j], matrix[i][j]);
                }
            }
        }
        quickSort(eData, 0, edgesNum - 1);
        System.out.println(Arrays.toString(eData));
    }

    private void kruskal() {
        int index = 0;
        EData[] result = new EData[vertexes.length - 1];
        int[] vertex = new int[this.vertexes.length];
        for (int i = 0; i < edgesNum; i++) {
            int p1 = getPosition(eData[i].start);
            int p2 = getPosition(eData[i].end);
            int m1 = getEnd(vertex, p1);
            int m2 = getEnd(vertex, p2);
            if (m1 != m2) {
                vertex[m1] = m2;
                result[index++] = eData[i];
            }
        }
        System.out.println("最小生成树为:");
        for (int i = 0; i < vertex.length - 1; i++) {
            System.out.println(result[i]);
        }
    }

    //获取每个元素对应的终点的角标
    public int getEnd(int[] ends, int index) {
        while (ends[index] != 0) {
            index = ends[index];
        }
        return index;
    }

    //获取元素对应角标
    public int getPosition(char p) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == p) {
                return i;
            }
        }
        return -1;
    }

    public void show() {
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = 0; j < vertexes.length; j++) {
                System.out.printf("%12d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    //快速排序
    public static void quickSort(EData[] eData, int low, int high) {
        if (low < high) {
            int mid = getMiddle(eData, low, high);
            quickSort(eData, low, mid - 1);
            quickSort(eData, mid + 1, high);
        }
    }

    private static int getMiddle(EData[] eData, int low, int high) {
        EData e = eData[low];
        while (low < high) {
            while (low < high && eData[high].weight >= e.weight) {
                high -= 1;
            }
            eData[low] = eData[high];
            while (low < high && eData[low].weight <= e.weight) {
                low += 1;
            }
            eData[high] = eData[low];
        }
        eData[low] = e;
        return low;
    }
}


class EData implements Comparable<EData> {
    public char start;
    public char end;
    public int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(EData o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "<" + start + "," + end + "> " + weight;
    }
}