package com.futurebytedance.horse.student;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/31 - 18:38
 * @Description 骑士周游算法
 */
public class HorseChessboard {
    private static int X;
    private static int Y;
    private static boolean[] visited;
    private static boolean finished;

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];
        horseChessboard(chessboard, 0, 0, 1);

        for (int[] rows : chessboard) {
            for (int column : rows) {
                System.out.print(column + "\t");
            }
            System.out.println();
        }
    }

    public static void horseChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        visited[row * X + column] = true;
        Point p1 = new Point(column, row);
        ArrayList<Point> points = next(p1);
        sort(points);
        while (!points.isEmpty()) {
            Point point = points.remove(0);
            if (!visited[point.y * X + point.x]) {
                horseChessboard(chessboard, point.y, point.x, step + 1);
            }
        }
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }


    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    public static void sort(ArrayList<Point> ps) {
        ps.sort(Comparator.comparingInt(o -> next(o).size()));
    }
}
