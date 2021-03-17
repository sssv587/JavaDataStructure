package com.futurebytedance.daynamicprogramming.student;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/17 - 22:59
 * @Description 暴力手段解决字符串匹配问题
 */
public class ViolenceMatchString {
    public static void main(String[] args) {
        String str1 = "回到过去周杰伦夜曲周杰伦晴天呀周杰伦";
        String str2 = "晴天呀周杰伦";
        int index = violenceMatchString(str1, str2);
        System.out.println("index=" + index);
    }

    public static int violenceMatchString(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Length = s1.length;
        int s2Length = s2.length;

        int i = 0;
        int j = 0;

        while (i < s1Length && j < s2Length) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
            }
        }

        if (j == s2Length) {
            return i - j;
        } else {
            return -1;
        }
    }
}
