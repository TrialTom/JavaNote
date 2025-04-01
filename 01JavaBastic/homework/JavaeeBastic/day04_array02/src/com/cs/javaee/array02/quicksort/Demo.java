package com.cs.javaee.array02.quicksort;

import java.util.Arrays;

/**234, 532, 23, 5, 34, 6, 6, 7, 134, 6, 3, 634, 24, 326324, 6223, 4, 5, 67, 8, 756, 23
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        int[] arr = {234,4234,5,4,35642,23423,5,75,87,99,78,9,8786,46,67,67,34,796,23,37465,875,35423,65,6556,9,935,34};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr,int low,int high) {
        if(low >= high){
            return;
        }
        int pivot = orderly(arr, low, high);
        quickSort(arr,low,pivot-1);
        quickSort(arr,pivot+1,high);
    }

    private static int orderly(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low < high) {
            while (arr[high] >= pivot && high>low) {
                high--;
            }
            arr[low] = arr[high];
            while (arr[low] < pivot && high>low){
                low++;
            }
            arr[high]=arr[low];
        }
        arr[low]=pivot;
        return low;
    }

}
