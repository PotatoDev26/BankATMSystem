package com.bankaccountatm;
import java.util.*;

public class BankAccount {
    private int accountNumber;
    private double balance;
    private int pinCode;
    protected static ArrayList<BankAccount> accounts = new ArrayList<>();

    public BankAccount() {

    }

    //setters--------------------------------------------------------------------------------------------
    //add limitations #
    public void setAccountNumber(int accnum) throws Exception {
        this.accountNumber = accnum;
    }
    //add input validation #
    public void setBalance(double balance) throws Exception {
        if(balance < 0) {
            System.out.println("BALANCE MUST NOT BE IN NEGATIVES (whatever the fuck that means)");
            Main.accountCreation();
        } else {
            this.balance = balance;
        }
    }
    //add limitiations #
    public void setPinCode(int pin) throws Exception {
        String strung = Integer.toString(pin);
        if(strung.length() != 4) {
            System.out.println("BASTA ERROR");
            Main.accountCreation();
        }  else {
            this.pinCode = Integer.valueOf(strung);
        }
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
        for(BankAccount obj : accounts) {
            System.out.println(obj.getPinCode());
        }
    }
    //--------------------------------------------------------------------------------------------
    
    public static void showCreatedAcc(BankAccount acc) {
        System.out.println("-------------------------------------------------------");
        System.out.println("Account Number: " + acc.getAccountNum() 
            + "\nAccount Balance: " + acc.getBalance() 
            + "\nPIN Code: " + acc.getPinCode());
        System.out.println("-------------------------------------------------------");
    }
}

