package com.cs.javaee.staticsnum;

/**
 * @author ：TrialCat
 * @description： "peter picked a peck of pickled peppers;"
 * 统计这段绕口令有多少个以p开头的单词
 * @date ：2024/02/29 20:28
 */

public class Demo01 {
    public static void main(String[] args) {
        String s = "peter picked a peck of pickled peppers;";
        System.out.println("staticsCase(s) = " + staticsCase(s,'p'));
    }

    public static int staticsCase(String s, char dest) {
        char[] charArray = s.toCharArray();
        int count = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] != dest) {
                continue;
            }
            count++;
        }
        return count;
    }
}
