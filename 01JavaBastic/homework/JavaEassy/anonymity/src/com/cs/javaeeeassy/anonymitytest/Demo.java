package com.cs.javaeeeassy.anonymitytest;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        Outer.method().show();
    }
}

interface Inter{

    void show();
}

class Outer{

    public static Inter method(){
       return new Inter(){
            @Override
            public void show() {
                System.out.println("Hello World!");
            }
        };
    }
}


