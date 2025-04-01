package com.cs.javaee.homework.recursionpractice;


/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        /*
         使用递归，把十进制正整数（N>=0）转换成二进制数
         */

        int num = 65535;
        String s = "";
        System.out.println(num + "的二进制为：" + binaryConversion(num, s));
    }

    private static String binaryConversion(int num, String s) {
        if (num == 0) {
            return s;
        }
        s = (num % 2) + s;
        return binaryConversion(num / 2, s);
    }
}
