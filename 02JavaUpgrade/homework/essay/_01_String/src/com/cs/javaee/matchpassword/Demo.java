package com.cs.javaee.matchpassword;

import org.junit.Test;

import java.sql.SQLOutput;
import java.util.Random;

/**
 * @author ：TrialCat
 * @description：随机生成一个三位数密码，穷举匹配
 * @date ：2024/02/29 20:58
 */

public class Demo {
    public static void main(String[] args) {
        String passWord = getRandomNum(3);
        System.out.println("passWord = " + passWord);
        matchPassWord(passWord);
    }

    private static void matchPassWord(String passWord) {
        for (int i = 0; i < 1000; i++) {
            if (passWord.equals(String.valueOf(i))) {
                System.out.println("匹配正确，密码为：" + i);
                return;
            }
            System.out.println("密码为：" + i + " 匹配失败");
        }
    }

    private static String getRandomNum(int num) {
        Random random = new Random();
        String s = "";
        for (int i = 0; i < num; i++) {
            int rand = random.nextInt(10);
            s += String.valueOf(rand);
        }
        return s;
    }

}
