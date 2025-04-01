package com.cs.bean;

/**
 * @author ：TrialCat
 * @description：实体类
 * @date ：2024/05/02 10:48
 */

public class Clazz {

    int id;
    String name;

    public Clazz(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
