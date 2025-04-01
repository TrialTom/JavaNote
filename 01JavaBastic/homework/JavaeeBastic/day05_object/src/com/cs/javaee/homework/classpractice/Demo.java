package com.cs.javaee.homework.classpractice;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        Item item = new Item("Samsung Galaxy S21", 8000, "手机类");
        Item item01 = new Item("海尔冰箱", 3000, "家具类");
        Item item02 = new Item("海飞丝洗发水", 30, "日用品类");

        item.shipping(100000);

    }
}
class Item{
    String name;
    int price;
    String category;

    public Item() {
    }

    public Item(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    public void shipping(int money){
        if(money < price){
            System.out.println("金钱不足，购买失败！");
            return;
        }
        System.out.println(this.name+"物品被卖出。");
    }
}