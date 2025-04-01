package com.cs.javaee.array02.arraypractice;

import java.util.Arrays;

/**
 * @author Lenovo
 */
public class Demo02 {
    public static void main(String[] args) {
        /**
         *
         *2. 定义一个String数组，输出该数组的长度，并用多种方式遍历打印数组元素（常见的方式遍历即可
         */
        String[] arr = {"a", "b", "c", "d"};
        System.out.println("arr数组的长度为："+arr.length+",\narr的遍历："+Arrays.deepToString(arr));

    }
}
