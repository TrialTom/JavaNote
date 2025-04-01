package com.cs.javaee.homework.lambdatest.computer;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        ComputeTool.calc((a,b)->a+b,1,3);
        ComputeTool.calc((a,b)->a*b,1,3);
        ComputeTool.calc((a,b)->a/b,1,3);
    }
}


/**
 * 需要提供一个使用功能接口的方法完成需求
 */
class ComputeTool {
    private ComputeTool() {
    }

    public static void calc(Compute com, double a, double b) {
        System.out.println(com.compute(a, b));
    }
}
@FunctionalInterface
interface Compute{
    double compute(double a,double b);
}