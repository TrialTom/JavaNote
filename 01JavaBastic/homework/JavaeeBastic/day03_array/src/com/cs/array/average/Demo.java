package com.cs.array.average;

import java.util.Scanner;

/**
 * @author Lenovo
 */
public class Demo {
    /**
     * 现在你去参加歌唱比赛。有10个评委打分，
     * 规则是去掉最高分和最低分，求平均分是最终成绩。
     * 请编码计算出你的最终成绩~
     */
    public static void main(String[] args) {
        System.out.println("请输入十位评委的打分（中间用一个空格分隔）");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        String[] strings = s.split(" ");
        int[] fraction = new int[10];
        for (int i = 0; i < fraction.length; i++) {
            fraction[i] = Integer.parseInt(strings[i]);
        }
        //最大值
        int max = getMax(fraction);
        //最小值
        int min = getMin(fraction);
        //求和
        int sum = getSum(fraction);
        System.out.println("平均值为:" + (sum - max - min) / 8);
    }

    private static int getSum(int[] fraction) {
        int sum = 0;
        for (int i = 0; i < fraction.length; i++) {
            sum += fraction[i];
        }
        return sum;
    }

    private static int getMin(int[] fraction) {
        int min = fraction[0];
        for (int i = 1; i < fraction.length; i++) {
            if (min > fraction[i]) {
                min = fraction[i];
            }
        }
        return min;
    }

    private static int getMax(int[] fraction) {
        int max = fraction[0];
        for (int i = 1; i < fraction.length; i++) {
            if (max < fraction[i]) {
                max = fraction[i];
            }
        }
        return max;
    }


}
