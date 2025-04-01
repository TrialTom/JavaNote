package com.cs.javaee.array02.elementofarraydelete;

import java.util.Arrays;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        /**
         *
         *对于某个String类型数组，将其中的某个元素全部去掉，得到一个新数组，并统计去掉了几个元素。
         *
         * 举例，对于String数组["abc", "123", "123", "123", "666", "777"]
         * 将其中的元素"123"全部去掉，就得到了新数组["abc","666", "777"]，一共去掉了3个元素
         */

        String[] arr = {"abc", "123", "123", "123", "666", "777"};
        String[] arr01 = elementDelete(arr, "123");
        System.out.println(Arrays.toString(arr01));
        System.out.println("删除" + (arr.length - arr01.length) + "个元素");

    }

    private static String[] elementDelete(String[] arr, String s) {
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == s) {
                continue;
            }
            arr[temp] = arr[i];
            temp++;
        }
        String[] arr01 = new String[temp];
        for (int i = 0; i < temp; i++) {
            arr01[i] = arr[i];
        }
        return arr01;
    }

}
