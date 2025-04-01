package com.cs.javaee.countcase;

/**
 * @author ：TrialCat
 * @description： 把字符串"lengendary"中字符，
 * 改成间隔大小写
 * @date ：2024/02/29 20:42
 */

public class Demo02 {
    public static final int INTERVALOFNUM = 2;

    public static void main(String[] args) {
        String s = "lengendary";
        System.out.println("changeOfUpperCase(s) = " + changeOfUpperCase(s));
    }

    private static String changeOfUpperCase(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i+=INTERVALOFNUM) {
            chars[i] = Character.toUpperCase(chars[i]);
        }
        return String.valueOf(chars);
    }
}
