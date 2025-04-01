package com.cs.javaee.array02.yanghuitriangle;

import java.util.Arrays;

/**
 * @author Lenovo
 */
public class YangHuiTriangle {

    public final static Integer INITIAL = 2;

    public static void main(String[] args) {

        int line = 15;
        int[][] arr = yangHui(line);
        if(arr == null){
            System.out.println("输入的值不符合！");
            return;
        }
        for (int[] element : arr) {
            System.out.println(Arrays.toString(element));
        }
    }

    private static int[][] yangHui(int line) {
        int[][] dstArr = new int[line][];
        for (int i = 0; i < line; i++) {
            dstArr[i] = new int[i + 1];
        }
        if (line < 1) {
            return null;
        }
        if (line == 1) {
            dstArr[0][0] = 1;
            return dstArr;
        }
        if (line == INITIAL) {
            dstArr[0][0] = 1;
            dstArr[1][0] = 1;
            dstArr[1][1] = 1;
            return dstArr;
        }
        dstArr[0][0] = 1;
        dstArr[1][0] = 1;
        dstArr[1][1] = 1;
        for (int i = 2; i < line; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == i || j == 0) {
                    dstArr[i][j] = 1;
                    continue;
                }
                dstArr[i][j] = dstArr[i - 1][j] + dstArr[i - 1][j - 1];
            }
        }
        return dstArr;
    }
}
