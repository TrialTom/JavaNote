package com.cs.array.dividebythefirstelement;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        /**
         *
         *
         * 定义一个double类型的数组，让数组中每个元素（包括首位元素）都除以首位元素，
         * 得到的结果过作为该位置上的新元素。请在原先数组数组上操作，并打印新数组。
         * 例如数组[ 2.0 , 4.0 , 6.0 , 4.0 ]
         * 经过方法运算得到新数组 [ 1.0 ,2.0 , 3.0 ,2.0 ]
         */
        double[] numDouble = {2, 4, 6, 4};
        divideByTheFirstElement(numDouble);
        for (int i = 0; i < numDouble.length; i++) {
            System.out.println(numDouble[i]);
        }
    }

    private static void divideByTheFirstElement(double[] numDouble) {
        for (int i = numDouble.length - 1; i >= 0; i--) {
            numDouble[i] /= numDouble[0];
        }
    }
}
