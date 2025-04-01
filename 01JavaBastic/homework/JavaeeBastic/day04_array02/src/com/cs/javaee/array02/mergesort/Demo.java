package com.cs.javaee.array02.mergesort;

import java.util.Arrays;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        int[] arr01 = {1, 4, 5, 7, 8, 90, 234};
        int[] arr02 = {2, 34, 55, 67, 89, 123, 456, 765};

        int[] mergeArr = mergeSort(arr01, arr02);
        System.out.println(Arrays.toString(mergeArr));
    }

    private static int[] mergeSort(int[] arr01, int[] arr02) {
        int[] arrOfMerge = new int[(arr01.length + arr02.length)];
        for (int i = 0, j = 0, k = 0; i < arrOfMerge.length; i++) {
            if (j == arr01.length) {
                arrOfMerge[i] = arr02[k];
                k++;
                continue;
            }
            if (k == arr02.length) {
                arrOfMerge[i] = arr01[j];
                j++;
                continue;
            }
            if (arr01[j] < arr02[k]) {
                arrOfMerge[i] = arr01[j];
                j++;
                continue;
            }
            arrOfMerge[i] = arr02[k];
            k++;
        }
        return arrOfMerge;
    }
}
