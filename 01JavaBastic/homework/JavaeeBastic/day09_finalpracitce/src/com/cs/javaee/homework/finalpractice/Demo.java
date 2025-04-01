package com.cs.javaee.homework.finalpractice;

/**
 * @author Lenovo
 */
public class Demo {

    public static void main(String[] args) {
        /*
        自定义一个类，类中定义三个成员变量a，b，c，用final修饰这三个成员变量
        再定义两个静态成员变量staticA和staticB，也用final修饰这两个静态成员变量
        然后：
	1，请用三种不同的方式，分别为成员变量a，b，c赋值
	2，请用两种不同的方式，分别为静态成员变量staticA和staticB赋值
         */
    }
}

class Student{

    final int a =1;
    final int b;
    final int c;

    {
        b =10;
    }

    final static int A =1;
    final static int B;
    static {
        B =10;
    }

    public Student(){
        c = 10;
    }
}
