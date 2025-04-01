package com.cs.javaee.homework.staticpractice;

import java.util.Scanner;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {

        System.out.println("-------momo银行账户--------");
        Account account = new Account(1000);
        Scanner sc = new Scanner(System.in);
        System.out.println("please input password: ");
        account.password = sc.nextLine();
        account.print();
    }
}

class Account {
    static int count = 0;
    int accountID = 10001;
    String password = "000000";
    double balance = 0;
    static double annualInterestRate = 0.3;


    public Account(int balance) {
        this.balance = balance;
        this.accountID += Account.count;
        Account.count++;
    }

    public Account(String password, double balance) {
        this.accountID += Account.count;
        this.password = password;
        this.balance = balance;
        Account.count++;
    }

    public void print() {
        System.out.println(
                "Account Name: " + this.accountID + "\n"
                        + "Account Password: " + this.password + "\n"
                        + "Account Balance: " + this.balance + "\n"
                        + "Account Rate: " + Account.annualInterestRate
        );
    }
}