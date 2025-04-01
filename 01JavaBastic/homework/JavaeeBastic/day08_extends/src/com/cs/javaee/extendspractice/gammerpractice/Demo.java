package com.cs.javaee.extendspractice.gammerpractice;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        Dog dog = new Dog(18,"CoCo","红");
    }
}

class Animal {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void behave(String sound) {
        System.out.println("会"+sound + "叫！");
    }
}

class Dog extends Animal {
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void behave(String sound) {
        super.behave(sound);
    }

    public void skill() {
        System.out.println("会看门");
    }

    public Dog() {

    }

    public Dog(int age,String name,String color){
        super.setAge(age);
        super.setName(name);
        this.setColor(color);
        System.out.println(super.getAge()+"年的"+this.color+super.getName());
        super.behave("狗");
        this.skill();
    }
}

class Cat extends Animal {
    String gender;

    @Override
    public void behave(String sound) {
        super.behave(sound);
    }

    public void skill() {
        System.out.println("捉老鼠");
    }
}