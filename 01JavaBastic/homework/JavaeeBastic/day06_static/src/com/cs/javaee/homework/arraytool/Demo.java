package com.cs.javaee.homework.arraytool;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        ArrayTool.traverse(arr);
        ArrayTool.reverse(arr);
        ArrayTool.traverse(arr);
        System.out.println("MaxNumber of Array = " + ArrayTool.getMax(arr));
        System.out.println("MinNumber of Array = " + ArrayTool.getMin(arr));
    }
}

class ArrayTool {
    /**
     * 数组的遍历
     *
     * @param arr
     */
    public static void traverse(int[] arr) {
        System.out.print("[");
        for (int i : arr) {
            System.out.print(i + " ,");
        }
        System.out.println("\b]");
    }

    /**
     * 数组逆置
     *
     * @param arr
     */
    public static void reverse(int[] arr) {
        for (int i = 0; i < (arr.length / 2); i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - (i + 1)];
            arr[arr.length - (i + 1)] = temp;
        }
    }

    /**
     * 求数组最大值
     *
     * @param arr
     * @return
     */
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    /**
     * 求数组最小值
     *
     * @param arr
     * @return
     */
    public static int getMin(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

}
