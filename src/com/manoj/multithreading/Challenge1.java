package com.manoj.multithreading;

/**
 * Created by Manoj Baral on 12/3/2020.
 */


/**
 * ##################Create and start threads###########################3
 * We could have two people using a joint bank account at the same time.
 * Create and start two threads that use the same BankAccount instance and
 * an initial balance of $1000.00.Onc will deposit $300.00 inti the bank
 * account and then withdraw $50.00.The other will deposit $203.75 and then withdraw $100.00
 */
public class Challenge1 {
    private double balance;
    private String accountNumber;

    public Challenge1(double balance, String accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public void deposit(double amount){
        balance += amount;
    }

    public void withdraw(double amount){
        balance -=amount;
    }


}
