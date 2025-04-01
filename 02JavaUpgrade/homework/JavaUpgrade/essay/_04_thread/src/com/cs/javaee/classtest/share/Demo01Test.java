package com.cs.javaee.classtest.share;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/15 20:11
 */

public class Demo01Test {
    public static void main(String[] args) {
        Tailor t1 = new Tailor();
        Tailor t2 = new Tailor();
        Tailor t3 = new Tailor();

        Thread thread01 = new Thread(t1);
        Thread thread02 = new Thread(t2);
        Thread thread03 = new Thread(t3);

        thread01.start();
        System.out.println(11111);
        thread02.start();
        System.out.println(2222222);
        thread03.start();
        System.out.println(333333);
    }
}

class Tailor implements Runnable{
    static int ticket = 100;

    @Override
    public void run() {
        while( true){
            if(ticket <= 0){
                break;
            }
            ticket--;
            System.out.println("ticket = " + ticket);
        }
    }
}
