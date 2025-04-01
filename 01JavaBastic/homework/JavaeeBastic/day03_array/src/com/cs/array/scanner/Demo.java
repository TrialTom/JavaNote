package com.cs.array.scanner;

import java.util.Scanner;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {

        differenceTypeOfCalculation();
    }

    /**
     * 使用Scanner依次接收以下三种数据类型的值：
     * 1. int类型
     * 2. String类型
     * 3. double类型
     * 接收三个值后，再计算：
     * 1. 求和：int + double
     * 2. 拼接字符串：int + double + String
     * 分别接收计算结果后，输出该计算结果
     */
    public static void differenceTypeOfCalculation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("int类型数据输入：");
        String numInt = sc.nextLine();
        System.out.println("String类型数据输入：");
        String numString = sc.nextLine();
        System.out.println("double类型数据输入：");
        String numDouble = sc.nextLine();

        System.out.println("int类型和double类型数据相加得：" + (Double.parseDouble(numInt) + Double.parseDouble(numDouble)));

        System.out.println("拼接字符串结果为：" + numInt + numString + numDouble);

    }

}
