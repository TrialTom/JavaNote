package com.cs.javaee.homework.innerpractice;

/**
 * @author Lenovo
 */
public class Demo {

    public static void main(String[] args) {
        Dog dog = new Dog(12, "xiacongming", "red");
        dog.show();
    }
}

/**
 * 定义一个类Dog
 * 属性：age,name
 * 除此之外,Dog类中需要定义一个成员内部类Body,Body类中有属性color
 * 请私有化该成员内部类,然后将该成员内部类对象作为外围类的成员变量私有化。
 * 然后在Dog类提供一个方法,展示Dog类的全部属性（包括成员内部类中的）。
 * 最后在一个外部类中，创建Dog对象，让外界感知不到成员内部类的存在。
 * 思考一下如何做这个需求？
 */


class Dog {
    private int age;
    private String name;

    Body body = new Body();

    private class Body {
        String color;

        public void setColor(String color) {
            this.color = color;
        }
    }

    public Dog(int age, String name, String color) {
        this.age = age;
        this.name = name;
        body.setColor(color);
    }

    public void show(){
        System.out.println(this.age);
        System.out.println(this.name);
        System.out.println(this.body.color);
    }
}