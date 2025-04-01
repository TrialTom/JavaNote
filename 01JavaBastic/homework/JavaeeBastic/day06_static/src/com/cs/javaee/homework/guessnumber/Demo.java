package com.cs.javaee.homework.guessnumber;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        /*
            先生成一个随机数（1~100之间的整数），再键盘输入猜测的数
            如果猜的数大了或者小了，给出提示，继续猜，直到猜中为止。
         */
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        while (true) {
            int r = random.nextInt(100) + 1;
            System.out.println("是否开始游戏？按任意键游戏开始（Y/N）");
            String answer = sc.nextLine();
            if (answer.equals("N")) {
                System.out.println("欢迎下次光临！");
                break;
            }
            System.out.println("游戏开始！");
            while (true) {
                System.out.println("请输入你要猜的数字:");
                String num = sc.nextLine();
                int n = Integer.parseInt(num);
                if (n == r) {
                    System.out.println("Game success!");
                    break;
                }
                if (n > r) {
                    System.out.println("你输入的数字偏大！");
                    continue;
                }
                System.out.println("你输入的数字偏小！");
            }

        }
    }
}
