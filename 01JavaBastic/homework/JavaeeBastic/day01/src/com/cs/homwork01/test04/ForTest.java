package com.cs.homwork01.test04;

/**
 * @author Lenovo
 */
public class ForTest {
    public static void main(String[] args) {
        //计算 2 + 4 + 6 + … + 100 的值
        int sum = 0;
        for (int i = 2; i <= 100; i += 2) {
            sum += i;
        }
        System.out.println(sum);

        //天数
        int year = 1000;
        int day = 0;
        while (year < 2008) {
            if (year % 4 == 0 && year % 100 != 0) {
                day += 365;
            } else if (year % 400 == 0) {
                day += 365;
            } else {
                day += 366;
            }
            year++;
        }
        System.out.println("day="+day);

        //循环输入一个不为0的数进行累加，直到输入的数字为0，结束循环并最后输出累加的结果。

    }
}

