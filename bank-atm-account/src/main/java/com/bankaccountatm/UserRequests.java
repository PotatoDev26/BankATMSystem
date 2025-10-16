package com.bankaccountatm;
import java.util.*;
import com.Progress;
import com.helpers.DataHelper;
import java.time.*;
import java.time.format.*;

public class UserRequests {
    private static Scanner sc;
    
    public static void getOpenBalance(Account account, ArrayList<Account> accounts) {
        DateTimeFormatter format  = DateTimeFormatter.ofPattern("yyyy-mm-dd | HH:mm:ss");
        System.out.println("--------WITHDRAWAL RECEIPT---------");
        System.out.println("ACCOUNT NO.     " + account.getAccountNum() 
                         + "\nNEW BAL.      " + account.getBalance()
                         + "\nDate of Req.  " + LocalDateTime.now().format(format));
    }

    public static void depositCash(Account account, ArrayList<Account> list) throws Exception {
        sc = new Scanner(System.in);
        System.out.println("----------DEPOSIT CASH-----------");
        System.out.println("Enter deposit amount: ");
        double amount = sc.nextDouble();
        if(amount <= 0) {
            System.err.println("INVALID INPUT REQUEST");
            Progress.buffer(300, "redirecting");
        } else {
            double oldbal = account.getBalance();
            double newbal = oldbal + amount;
            account.setBalance(newbal);
            DataHelper.storeDataToJSON(list);
            Progress.buffer(300, "DEPOSIT REQUEST SUCESSFUL!");
            list = DataHelper.retrieveDataFromJSON();
            depositReceipt(oldbal, account, list); 
        }
        Thread.sleep(1000);
        Progress.buffer(300, "RETURNING TO MENU");
    }

    public static void cashWithdrawal(Account account, ArrayList<Account> list) throws Exception {
        sc = new Scanner(System.in);
        System.out.println("----------CASH WITHDRAW-----------");
        System.out.println("Enter withdraw amount: ");
        double amount = sc.nextDouble();
        if(amount > account.getBalance()) {
            System.out.println("WITHDRAWAL AMOUNT REQUEST EXCEEDS YOUR ACCOUNT BALANCE!");
            Progress.buffer(300,"redirecting");
        } else {
            double oldbal = account.getBalance();
            double newbal = oldbal - amount;
            account.setBalance(newbal);
            DataHelper.storeDataToJSON(list);
            Progress.buffer(300, "WITHDRAWAL REQUEST SUCESSFUL!");
            list = DataHelper.retrieveDataFromJSON();
            withdrawalReceipt(oldbal, account, list); 
        }
        Thread.sleep(1000);
        Progress.buffer(300, "RETURNING TO MENU");
    }

    public static void withdrawalReceipt(double temp, Account account, ArrayList<Account> list) {
        System.out.println("--------WITHDRAWAL RECEIPT---------");
        System.out.println("ACCOUNT NO.   " + account.getAccountNum() 
                         + "\nOLD BAL.      " + temp 
                         + "\nNEW BAL.      " + account.getBalance()
                         + "\nPIN CODE      " + PinEncrypt.pinToHash(account.getPinCode()));
    }
    
    public static void depositReceipt(double oldbal, Account account, ArrayList<Account> list) {
        System.out.println("--------WITHDRAWAL RECEIPT---------");
        System.out.println("ACCOUNT NO.   " + account.getAccountNum() 
                         + "\nOLD BAL.      " + oldbal 
                         + "\nNEW BAL.      " + account.getBalance()
                         + "\nPIN CODE      " + PinEncrypt.pinToHash(account.getPinCode()));
    }
}