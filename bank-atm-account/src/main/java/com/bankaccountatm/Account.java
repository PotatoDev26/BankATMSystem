package com.bankaccountatm;
import java.util.*;

public class Account {
    public static ArrayList<Account> accounts = new ArrayList<>();
    private int accountNumber;
    private double balance;
    private int pinCode;

    public Account(int accNum, double bal, int pin) {
        this.accountNumber = accNum;
        this.balance = bal;
        this.pinCode = pin;
    }
    public Account() {}
    //setters--------------------------------------------------------------------------------------------
    public static void addToHash(Account account) {
        Account.accounts.add(account);
    }
    //add limitations #
    public void setAccountNumber(int accnum) throws Exception {
        this.accountNumber = accnum;
    }
    //add input validation #
    public void setBalance(double balance) throws Exception {
        this.balance = balance;
    }
    //add limitiations #
    public void setPinCode(int pin) throws Exception {
        this.pinCode = pin;
    }
    public static boolean isFourDigits(int num) {
        String strPin = Integer.toString(num);
        if(strPin.length() > 4) {
            return false;
        }
        return true;
    }
    public static boolean balanceIsNotZeroOrNegative(double num) {
        if(num <= 0) {
            return false;
        }
        return true;
    }
    //--------------------------------------------------------------------------------------------
    //getters--------------------------------------------------------------------------------------------
    public int getAccountNum() {
        return this.accountNumber;
    }
    public double getBalance() {
        return this.balance;
    }
    public int getPinCode() {
        return this.pinCode;
    }
    public static void showObjectList() {
        for(Account obj : accounts) {
            System.out.println(obj.getPinCode());
        }
    }
    //--------------------------------------------------------------------------------------------
    public static void showCreatedAcc(Account acc) {
        System.out.println("-------------------------------------------------------");
        System.out.println("Account Number: " + acc.getAccountNum() 
            + "\nAccount Balance: " + acc.getBalance() 
            + "\nPIN Code: " + acc.getPinCode());
        System.out.println("-------------------------------------------------------");
        Account.accounts.add(acc);
    }
}

