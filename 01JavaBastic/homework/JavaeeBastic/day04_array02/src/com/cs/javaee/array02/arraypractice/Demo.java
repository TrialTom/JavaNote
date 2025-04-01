package com.cs.javaee.array02.arraypractice;

import java.util.Scanner;

/**
 * @author Lenovo
 */
public class Demo {
    public final static Integer STUDENTNUM = 10;

    public static void main(String[] args) {

        /**
         *
         *
         * 1. 定义一个double数组用来存放学生成绩，然后键盘录入10位同学的成绩并存入数组，求这10位同学成绩的平均值。
         *
         */
        double[] grades = new double[STUDENTNUM];

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < STUDENTNUM; i++) {
            System.out.println("请输入第" + (i + 1) + "位学生的成绩:");
            double temp = sc.nextDouble();
            grades[i] = temp;
        }
        System.out.println("学生平均成绩为：" + average(grades));

    }

    private static double average(double[] arr) {
        double sum = 0;
        for (double grades : arr) {
            sum += grades;
        }
        return sum / STUDENTNUM;
    }

}
