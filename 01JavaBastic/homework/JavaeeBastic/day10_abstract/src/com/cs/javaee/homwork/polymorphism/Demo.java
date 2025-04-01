package com.cs.javaee.homwork.polymorphism;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        Person person = new Person();
        person.eat();

        Person southPerson = new SouthPerson();
        southPerson.eat();

        Person northPerson = new NorthPerson();
        northPerson.eat();
        NorthPerson northPerson1 = (NorthPerson) northPerson;
        northPerson1.drink();
    }
}

class Person{
    String name;
    int age;

    public void eat(){
        System.out.println("我要吃饭！");
    }
}

class SouthPerson extends Person{
    double salary;

    @Override
    public void eat() {
        System.out.println("我喜欢吃面食。");
    }
    public void swim(){
        System.out.println("I like swim");
    }
}

class NorthPerson extends Person{
    double height;

    @Override
    public void eat() {
        System.out.println("我喜欢吃米饭。");
    }
    public void drink(){
        System.out.println("I like drink orange.");
    }
}