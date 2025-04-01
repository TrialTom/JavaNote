package com.cs.javaee.countcase;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/02/29 14:34
 */

public class Demo {

    @Test
    public void test01() {
        String s = "abc001ABC";
        countCast(s);
        countCast01(s);
    }

    /**
     * create by: TrialCat
     * description: 统计字符串中大写，小写，数字出现的次数
     * create time: 2024/2/29 14:35
     *
     * @return void
     */
    public void countCast01(String s) {
        if (s.isEmpty()) {
            return;
        }
        int capitalLetter = 0;
        int lowercaseLetter = 0;
        int num = 0;
        int tag = 0;
        char[] chars = s.toCharArray();
        while (true) {
            if (tag == s.length()) {
                break;
            }
            if (Character.isUpperCase(chars[tag])) {
                capitalLetter++;
                tag++;
                continue;
            }
            if (Character.isLowerCase(chars[tag])) {
                lowercaseLetter++;
                tag++;
                continue;
            }
            num++;
            tag++;
        }
        System.out.println("capitalLetter = " + capitalLetter);
        System.out.println("lowercaseLetter = " + lowercaseLetter);
        System.out.println("num = " + num);
    }

    public void countCast(String s) {
        // empty
        if (s.isEmpty()) {
            return;
        }
        int capitalLetter = 0;
        int lowercaseLetter = 0;
        int num = 0;
        int tag = 0;
        while (true) {
            char char01 = s.charAt(tag);
            // 小写数字的累加
            if (char01 >= 97 && char01 <= 123) {
                lowercaseLetter++;
            }
            // 大写数组的累加
            if (char01 >= 65 && char01 <= 96) {
                capitalLetter++;
            }

            // 数字的累加
            if (char01 >= 48 && char01 <= 58) {
                num++;
            }
            tag++;
            // 出口
            if (tag == s.length()) {
                break;
            }
        }
        System.out.println("capitalLetter = " + capitalLetter);
        System.out.println("lowercaseLetter = " + lowercaseLetter);
        System.out.println("num = " + num);
    }
}


