package com.cs.javaee.homwork.abstractpractice;


import org.junit.Test;

/**
 * @author Lenovo
 */

public class Demo {
    public static void main(String[] args) {
        C c = new C();
        c.showA();
        c.showB();
        c.showC();
    }
}

abstract class AbstractA{
    int a =10;

    public abstract void showA();
}

abstract class AbstractB extends AbstractA{
    int b = 20;

    public abstract void showB();
}

class C extends AbstractB{
    int c = 30;

    @Override
    public void showA() {
        System.out.println(a);
    }

    @Override
    public void showB() {
        System.out.println(b);
    }

    public void showC(){
        System.out.println(c);
    }
}