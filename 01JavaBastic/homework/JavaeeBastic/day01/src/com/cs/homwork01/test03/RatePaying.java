package com.cs.homwork01.test03;

import java.util.Scanner;

public class RatePaying {
    public static void main(String[] args) {
        while (true) {
            System.out.println("please input salary:");
            Scanner sc = new Scanner(System.in);
            int salary = sc.nextInt();
            System.out.println("you need to rate paying:");
            if (salary <= 500)
                System.out.println("0");
            else if (salary > 500 && salary <= 2000)
                System.out.println((salary - 500) * 0.1);
            else if (salary > 2000 && salary <= 5000)
                System.out.println((salary - 2000) * 0.15 + 125);
            else if (salary > 5000 && salary <= 20000)
                System.out.println((salary - 5000) * 0.2 + 375);
            else if (salary > 20000 && salary <= 40000)
                System.out.println((salary - 20000) * 0.25 + 1375);
            else if (salary > 40000 && salary <= 60000)
                System.out.println((salary - 40000) * 0.3 + 3375);
            else if (salary > 60000 && salary <= 80000)
                System.out.println((salary - 60000) * 0.35 + 6375);
            else if (salary > 80000 && salary <= 100000)
                System.out.println((salary - 80000) * 0.4 + 10375);
            else
                System.out.println((salary - 100000) * 0.45 + 15375);
        }
    }
}
