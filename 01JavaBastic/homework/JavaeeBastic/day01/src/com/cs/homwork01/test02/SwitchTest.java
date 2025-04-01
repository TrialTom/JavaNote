package com.cs.homwork01.test02;

import java.util.Scanner;

public class SwitchTest {
    public static void main(String[] args) {

        while (true){
            System.out.println("please input month:");
            Scanner sc = new Scanner(System.in);
            int month = sc.nextInt();

            switch (month) {
                case 1:
                case 2:
                case 12:
                    System.out.println("冬季");
                    break;
                case 3:
                case 4:
                case 5:
                    System.out.println("春季");
                    break;
                case 6:
                case 7:
                case 8:
                    System.out.println("夏季");
                    break;
                case 9:
                case 10:
                case 11:
                    System.out.println("秋季");
                    break;
                default:
                    System.out.println("请输入正确的月份!");
                    break;
            }
        }

    }
}
