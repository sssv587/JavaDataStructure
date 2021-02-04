package com.futurebytedance.sparsearray.student;

import java.io.*;
import java.util.Arrays;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/4 - 23:52
 * @Description
 */
public class SparseArrayUtil {
    //输出二维数组中的元素
    public static void showArrays(int[][] arr) {
        for (int[] row : arr) {
            for (int data : row) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }
    }

    //统计一个二维数组中的元素个数
    public static int getNumberFormArray(int[][] chessArray) {
        int sum = 0;
        for (int[] rows : chessArray) {
            for (int value : rows) {
                if (value != 0) {
                    sum++;
                }
            }
        }
        return sum;
    }

    //返回一个二维数组对应的稀疏矩阵
    public static int[][] getSparseArray(int[][] chessArray) {
        int length = getNumberFormArray(chessArray);
        int[][] sparseArray = new int[length + 1][3];

        //给稀疏数组赋值
        sparseArray[0][0] = chessArray.length;
        sparseArray[0][1] = chessArray.length;
        sparseArray[0][2] = length;

        int count = 1;//count 用于记录是第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray[i][j] != 0) {
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                    count++;
                }
            }
        }
        return sparseArray;
    }

    //返回一个稀疏数组对应的二维数组
    public static int[][] getChessArrayFromSparseArray(int[][] sparseArray) {
        int[][] chessArray = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            chessArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return chessArray;
    }

    //将二维数组写入到文件中
    public static void insertDataIntoFiles(int[][] SparseArray) {
        BufferedOutputStream bw = null;
        try {
            bw = new BufferedOutputStream(new FileOutputStream("C:\\Users\\10926\\IdeaProjects\\JavaDataStructure\\DataStructures\\src\\com\\futurebytedance\\sparsearray\\student\\map.data"));
            for (int[] row : SparseArray) {
                String str = "";
                for (int per_number : row) {
                    str = per_number + "\t";
                    bw.write(str.getBytes());
                }
                bw.write("\n".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //将稀疏数组还原为二维数组并输入
    public static void createChessArrayFromFile() {
        int[][] chessArray = null;
        StringBuilder finalStr = new StringBuilder();
        try {
            BufferedInputStream br = new BufferedInputStream(new FileInputStream("C:\\Users\\10926\\IdeaProjects\\JavaDataStructure\\DataStructures\\src\\com\\futurebytedance\\sparsearray\\student\\map.data"));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = br.read(buffer)) != -1) {
                String str = new String(buffer, 0, len);
                finalStr.append(str.trim());
            }
            String[] arrayNumber = finalStr.toString().split("\t");
            chessArray = new int[Integer.parseInt(arrayNumber[0])][Integer.parseInt(arrayNumber[1])];
            for (int i = 3; i < arrayNumber.length; i += 3) {
                chessArray[Integer.parseInt(arrayNumber[i].trim())][Integer.parseInt(arrayNumber[i + 1].trim())] = Integer.parseInt(arrayNumber[i + 2].trim());
            }
            showArrays(chessArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
