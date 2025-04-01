package com.cs.javaee.extendspractice.test;

/**
 * @author Lenovo
 */
public class Test{
    public static void main(String[] args){
        Father f1 = new Son(1000);
        Father f2 = new Father();
        Son s = new Son(1000);
    }
}
class Father {
    int i = 10;
    public Father() {
        System.out.println(getI());
    }
    public int getI() {
        return i;
    }
}

class Son extends Father {
    int i = 100;
    public Son(int i) {
        this.i = i;
    }
    @Override
    public int getI() {
        return i;
    }
}