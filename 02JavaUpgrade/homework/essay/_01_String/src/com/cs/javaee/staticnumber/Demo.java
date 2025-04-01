package com.cs.javaee.staticnumber;

import jdk.internal.dynalink.beans.StaticClass;

import java.util.Arrays;
import java.util.Random;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/01 10:20
 */

public class Demo {
    public static final int COUNT = 2;

    public static void main(String[] args) {
        String[] str = new String[100];
        str = randForStr(str);
        System.out.println("Arrays.toString(str) = " + Arrays.toString(str));
        Arrays.sort(str);
        System.out.println("--------------------------------------------");
        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < 20 && i < str.length; j++) {
                System.out.print(str[i++] + "  ");
            }
            System.out.println();
        }
        // 统计重复出现的次数
        statisticNum(str);

    }

    private static void statisticNum(String[] str) {
        int[] count = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            for (int j = i; j < str.length; j++) {
                if (str[i] != null && str[i].equals(str[j])) {
                    count[i] += 1;
                    if (count[i] > 1) {
                        str[j] = null;
                    }
                }
            }
        }

        int tag = 0;
        for (int i = 0; i < str.length; i++) {
            if (count[i] > 1 && str[i] != null) {
                System.out.format("%s出现%d次%n", str[i], count[i]);
                tag++;
            }
        }
        if (tag == 0) {
            System.out.println("无重复出现！");
        }
    }

    private static String[] randForStr(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            strings[i] = randomCast();
        }
        return strings;
    }

    private static String randomCast() {
        String s = "";
        String result = "";
        Random random = new Random();
        for (char i = 0; i < 128; i++) {
            if (i >= '0' && i <= '9') {
                s += i;
            }
            if (i >= 'a' && i <= 'z') {
                s += i;
            }
            if (i >= 'A' && i <= 'Z') {
                s += i;
            }
        }
        for (int i = 0; i < COUNT; i++) {
            int rand = random.nextInt(61);
            result += s.charAt(rand);
        }
        return result;
    }
}