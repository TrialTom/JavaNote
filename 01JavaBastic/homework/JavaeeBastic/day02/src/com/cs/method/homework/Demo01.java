package com.cs.method.homework;


import org.testng.annotations.Test;

import java.util.Scanner;

/**
 * @author Lenovo
 */
public class Demo01 {

    /**
     * 使用for循环遍历n以内的奇数，并计算所有的奇数的和并输出。
     */
    @Test
    public void oddAnd() {
        System.out.println("请输入n的值：");
        Scanner Odd = new Scanner(System.in);
        int n = Odd.nextInt();
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 != 0) {
                sum += i;
            }
        }
        System.out.println("所有的奇数的和为:" + sum);

    }

    /**
     * 打印倒三角形，并且行数（line）是可控制的。比如我想打印6行的倒三角形，则如下图
     */
    @Test
    public void invertedTriangle() {
        System.out.println("请输入行数：");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        for (int i = num; i > 0; i--) {
            for (int j = num; j >= num - i + 1; j--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * 根据键盘录入打印nn乘法表，n是可变的。
     */
    @Test
    public void multiplicationTable() {
        System.out.println("请输入行数：");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        for (int i = 1; i <= num; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + (i * j) + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 键盘录入一个int数字，判断它是奇数还是偶数。
     */
    @Test
    public void oddAndEvenNumber() {
        System.out.println("请输入数：");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        if (num % 2 == 0) {
            System.out.println("偶数");
            return;
        }
        System.out.println("奇数");
    }

    /**
     * 根据键盘录入打印n行m列井号，n和m是可变的
     */
    @Test
    public void print() {
        System.out.println("请输入行数：");
        Scanner sc = new Scanner(System.in);
        int col = sc.nextInt();
        System.out.println("请输入列数：");
        int row = sc.nextInt();
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                System.out.print("#");
            }
            System.out.println();
        }
    }

    /**
     * 输出前n个数当中的所有素数，并统计个数
     */
    @Test
    public void statisticalComposites() {
        System.out.println("请输入数：");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int sum = 0;
        for (int i = 1; i <= num; i++) {
            int pow = (int) Math.pow(i, 0.5);
            for (int j = 2; j <= pow; j++) {
                if (i % j == 0) {
                    sum += 1;
                }
            }
        }
        System.out.println("素数个数为：" + sum);
    }
}
