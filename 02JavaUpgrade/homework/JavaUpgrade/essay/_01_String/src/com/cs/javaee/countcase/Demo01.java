package com.cs.javaee.countcase;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/02/29 19:30
 */

public class Demo01 {
    public static void main(String[] args) {
        String result = upperCase("i want to a bing dun dun.");
        System.out.println("result = " + result);
    }

    /**
     * create by: TrialCat
     * description: 首字母大写
     * create time: 2024/2/29 19:30
     *
     * @return
     */
    public static String upperCase(String s) {
        String[] result = s.split(" ");
        String consequence = "";
        for (int i = 0; i < result.length; i++) {
            String head = result[i].substring(0, 1);
            consequence += head.toUpperCase().concat(result[i].substring(1) + " ");
        }
        return consequence.trim();
    }
}


