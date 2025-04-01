package com.cs.javaee.sortofchar;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * @author ：TrialCat
 * @description： 创建一个长度是8的字符串数组，使用8个长度为5的随机字符初始化这个数组
 * 对这个数组进行排序
 * @date ：2024/03/01 8:59
 */

public class Demo {
    public static final int COUNT = 1;

    public static void main(String[] args) {
        String[] str = new String[8];
        str = randOfArray(str);
        System.out.println("Arrays.toString(str) = " + Arrays.toString(str));
        randArrayOfSort(str);
        System.out.println("strSort = " + Arrays.toString(str));

    }

    private static void randArrayOfSort(String[] str) {
        String temp = "";
        for (int i = 0; i < str.length; i++) {
            for (int j = i; j < str.length; j++) {
                if (str[i].compareToIgnoreCase(str[j]) > 0) {
                    temp = str[i];
                    str[i] = str[j];
                    str[j] = temp;
                }
            }
        }
    }

    private static String[] randOfArray(String[] str) {
        for (int i = 0; i < str.length; i++) {
            str[i] = randStr();
        }
        return str;
    }

    public static String randStr() {
        Random rand = new Random();
        // 122 - 48 = 74;
        String s = "";
        for (int i = 0; i < COUNT; ) {
            char num = (char) (rand.nextInt(75) + 48);
            if (num >= 58 && num <= 64) {
                continue;
            }
            if (num >= 91 && num <= 96) {
                continue;
            }
            s += num;
            i++;
        }
        return s;
    }
}
