package com.cs.javaee.essay;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.lang.model.element.VariableElement;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/03 10:27
 */

public class Demo {
    public static void main(String[] args) {
        A a = new B();
        a.print();
    }
}

class A{
    public void print(){
        System.out.println("11111");
    }
}

class B extends A{
    @Override
    public void print(){
        System.out.println("223232");
    }
}
