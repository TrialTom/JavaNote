package com.cs.homwork01.test01;

import java.util.Scanner;

public class SanerTest {
    public static void main(String[] args) {
        System.out.println("please input Math grade:");
        Scanner sc01 = new Scanner(System.in);
        int math = sc01.nextInt();
        System.out.println("please input Chinese grade:");
        Scanner sc02 = new Scanner(System.in);
        int chinese = sc01.nextInt();
        System.out.println("please input English grade:");
        Scanner sc03 = new Scanner(System.in);
        double english = sc01.nextInt();

        System.out.println("平均分："+(math+chinese+english)/3);
        System.out.println("最高分："+(math>chinese?(math>english?math:english):(chinese>english)?chinese:english));
        System.out.println("最低分："+(math<chinese?(math<english?math:english):(chinese<english)?chinese:english));
        System.out.println("总分："+(math+chinese+english));
    }
}
