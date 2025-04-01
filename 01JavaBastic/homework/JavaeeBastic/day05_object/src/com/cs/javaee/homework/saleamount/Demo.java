package com.cs.javaee.homework.saleamount;

import java.util.Scanner;

/**
 * @author Lenovo
 */
public class Demo {

    public final static Integer QUARTER = 4;

    public final static Integer MONTH = 3;

    public static void main(String[] args) {

        /*
         某公司该年度，每个季度的销售额（单位：万元）如下：
          第一季度：30,66, 48
          第二季度：10, 33, 20
          第三季度: 10,99,103
          第四季度: 9,18,27
          请使用二维数组存储数据
          并计算：
          1，每个季度平均销售额
          2，年度销售总额
         */

        Scanner sc = new Scanner(System.in);
        int[][] saleAmount = new int[QUARTER][MONTH];
//        int[][] saleAmount = {{30,66, 48},{10, 33, 20},{10,99,103},{9,18,27}};
        for (int i = 0; i < QUARTER; i++) {
            for (int j = 0; j < MONTH; j++) {
                System.out.println("请输入第" + (i + 1) + "季度的销售额:");
                int temp = sc.nextInt();
                saleAmount[i][j] = temp;
            }
        }
        System.out.println("每个季度平均销售额:" + sumAmount(saleAmount)/4);
        System.out.println("年度销售总额:" + sumAmount(saleAmount));

    }

    public static int sumAmount(int[][] saleAmount) {
        if (saleAmount == null) {
            return -1;
        }
        int sum = 0;
        for (int[] ints : saleAmount) {
            for (int anInt : ints) {
                sum += anInt;
            }
        }
        return sum;
    }
}
