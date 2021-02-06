package com.futurebytedance.sparsearray.student;


import static com.futurebytedance.sparsearray.student.SparseArrayUtil.*;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/4 - 23:49
 * @Description 二维数组 --> 稀疏数组 封装性
 */
public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组 11*11
        //0：表示没有棋子 1：表示黑子 2：表示蓝子
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[3][4] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组为：");
        showArrays(chessArray);

        //将二维数组转稀疏数组
        int[][] sparseArray = getSparseArray(chessArray);
        System.out.println("得到的稀疏数组为:");
        showArrays(sparseArray);

        //将稀疏数组写入文件中
        insertDataIntoFiles(sparseArray);

        //将稀疏数组 --> 恢复成 原始的二维数组
        int[][] chessArray2 = getChessArrayFromSparseArray(sparseArray);
        System.out.println("恢复后的二维数组为:");
        //showArrays(chessArray2);

        //将稀疏数组还原为二维数组并输出
        createChessArrayFromFile();
    }
}
