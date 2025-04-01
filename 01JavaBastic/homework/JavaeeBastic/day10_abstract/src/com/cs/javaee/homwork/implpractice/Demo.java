package com.cs.javaee.homwork.implpractice;

import java.util.Scanner;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        Play student = new Student();
        student.comeOn();
        Person student1 = new Student();
        student1.eat();
        student1.sleep();
    }
}

interface Play{
    void comeOn();
}
interface Enable{
    void language();
}
abstract class Person{
    int name;
    int gender;
    int age;
    abstract void eat();
    abstract void sleep();
}
class Student extends Person implements Play{
    @Override
    void eat() {
        System.out.println("吃吃吃，疯狂吃。");
    }

    @Override
    void sleep() {
        System.out.println("睡睡睡，疯狂睡。");
    }

    @Override
    public void comeOn() {
        System.out.println("玩玩玩，疯狂玩。");
    }
}
class Teacher extends Person implements Enable{
    @Override
    void eat() {
        System.out.println("细嚼慢咽");
    }

    @Override
    void sleep() {
        System.out.println("下班，睡觉。");
    }

    @Override
    public void language() {
        System.out.println("教书育人。");
    }
}