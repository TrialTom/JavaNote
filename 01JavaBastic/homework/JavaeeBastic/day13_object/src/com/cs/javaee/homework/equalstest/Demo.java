package com.cs.javaee.homework.equalstest;

import com.sun.org.apache.xpath.internal.operations.Equals;
import javafx.scene.media.EqualizerBand;

import javax.lang.model.element.VariableElement;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/02/28 15:44
 */

public class Demo {
    public static void main(String[] args) {
        Animal animal01 = new Animal(2, "狒狒", 200);
        Animal animal02 = new Animal(2, "狒狒", 200);
        Animal animal03 = new Animal(2, "狒狒1号", 200);
        System.out.println(animal01.equals(animal02));
        System.out.println(animal01.equals(animal03));
    }
}

/**
 * create by: TrialCat
 * description: 定义一个Animal类
 * 成员变量：
 * int age,String name,double price
 * 手写它的equals方法，比较getClass和instanceof的区别
 * create time: 2024/2/28 15:45
 *
 * @param
 * @return
 */
class Animal {
    int age;
    String name;
    double price;

    public Animal(int age, String name, double price) {
        this.age = age;
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null ) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        if (this.age != ((Animal) obj).age) {
            return false;
        }
        if (this.name != ((Animal) obj).name) {
            return false;
        }
        return this.price == ((Animal) obj).price;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}