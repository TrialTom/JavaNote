package com.cs.javaee.homework.scannertools;


import java.util.Scanner;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        ScannerUtils.setStr();
        String s = ScannerUtils.getStr();
        System.out.println(s);
    }
}

class ScannerUtils {
    private static String str;

    Scanner sc = new Scanner(System.in);


    public static String getStr() {
        return ScannerUtils.str;
    }

    public static void setStr(){
        new ScannerUtils();
    }

    private ScannerUtils() {
        System.out.println("Input:");
        ScannerUtils.str = sc.nextLine();
    }
}
