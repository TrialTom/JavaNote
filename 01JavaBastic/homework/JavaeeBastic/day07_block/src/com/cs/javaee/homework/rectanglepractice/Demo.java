package com.cs.javaee.homework.rectanglepractice;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        /*
        需求：
        创建两个类，分别用来表示长方形和正方形。
        同时定义所需的成员变量（边长），代表长方形或者正方形的边长
        （私有化成员变量，并提供相应的Getter/Setter方法，获取以及改变长方形和正方形的边长。）
        然后在两个类中分别定义两个成员方法，用于求对应图形的面积和周长。
        最后，写代码测试一下创建对象，方法调用等。
         */

        RecTangle recTangle = new RecTangle();
        System.out.println(RecTangle.area(3, 4));
    }
}
class Shape{
    private int wide;
    private int length;

    protected static int circumference(int wide,int length){
        return wide*length;
    }

    protected static int area(int wide,int length){
        return (wide+length)*2;
    }

}
class RecTangle extends Shape{


}

class Square extends Shape{

}
