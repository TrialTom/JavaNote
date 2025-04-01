package com.cs.javaee.array02.test;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        System.out.println(sum(25000));
    }

    public static int sum(int num) {
        if (num == 0) {
            return 0;
        }
        return sum(num - 1) + num;
    }
}
