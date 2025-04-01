package com.cs.javaee.sinulatelogin;

import java.util.Scanner;

/**
 * @author ：TrialCat
 * @description：练习
 * @date ：2024/02/29 8:50
 */

public class Demo {

    public static final String USER = "root";
    public static final String PASSWORD = "12345";
    public static final int NUMBEROFJUDGE = 3;

    public static void main(String[] args) {
        // 键入用户名
        int count = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("please input username:");
        String s = sc.nextLine();
        // 用户名判断
        while (true) {
            if (USER.equals(s)) {
                break;
            }
            count++;
            if(count == NUMBEROFJUDGE)
            {
                break;
            }
            System.out.println("please input again.");
            s = sc.nextLine();
        }
        if (count == NUMBEROFJUDGE) {
            System.out.println("login is fail!");
            return;
        }
        count = 0;
        // 键入密码
        System.out.println("please input password:");
        String s1 = sc.nextLine();
        // 密码判断
        while (true) {
            if (PASSWORD.equals(s1)) {
                break;
            }
            count++;
            if(count == NUMBEROFJUDGE)
            {
                break;
            }
            System.out.println("please input again:");
            s1 = sc.nextLine();
        }
        if (count == NUMBEROFJUDGE) {
            System.out.println("login fail!");
            return;
        }
        System.out.println("login success");
        // 结果

    }
}
