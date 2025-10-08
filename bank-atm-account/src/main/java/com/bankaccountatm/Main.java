package com.bankaccountatm;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class Main {
    static final String jsonDir = "C:\\Users\\Matthew\\Desktop\\Folders" 
                                + "\\Progamming\\Java\\recent\\maven projects"
                                + "\\bank-atm-account\\src\\main\\resources\\data";
    static final String fileName = "accounts-records.json";
    static final int SERIAL_NUM = 100; 
    static Random rand = new Random();
    static int numAccQueue = 0; 
    static Scanner sc;

    private static void buffer(int milsecs, String msg) throws Exception {
        System.out.print(msg);
        for(int i = 0; i < 4; i++) {
            System.out.print(" .");
            Thread.sleep(milsecs);
        }
        System.out.println("");
    }
    private static void buffer(String msg) throws Exception {
        int milsecs = 100;
        System.out.print(msg);
        for(int i = 0; i < 4; i++) {
            System.out.print(" .");
            Thread.sleep(milsecs);
        }
        System.out.println("");
    }
    private static void buffer(int milsecs) throws Exception {
        for(int i = 0; i < 4; i++) {
            System.out.print(".");
            Thread.sleep(milsecs);
        }
    }

    private static void Execute() throws Exception {
        Menu();
    }

    private static void createAccount(int accnum, double bal, int pin) throws Exception {
        File file = new File(jsonDir, fileName);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileReader reader = new FileReader(file)) {
            Type type = new TypeToken<ArrayList<BankAccount>>(){}.getType();
            BankAccount.accounts = gson.fromJson(reader, type);   
        } 
        
        buffer(500, "FINALIZING ACCOUNT");
        BankAccount acc = new BankAccount();
        acc.setAccountNumber(accnum);
        acc.setBalance(bal);
        acc.setPinCode(pin);
        System.out.println("ACCOUNT CREATED!");
        BankAccount.showCreatedAcc(acc);

        BankAccount.accounts.add(acc);
        FileWriter record = new FileWriter(file);
        gson.toJson(BankAccount.accounts, record);
        record.close();
        Thread.sleep(1500);
        buffer(500, "RETURNING TO MENU");
        Menu();
    }
    
    static void EnterPin() throws Exception {
        sc = new Scanner(System.in);
        System.out.println("Enter PIN number: ");
        String pin = sc.nextLine();
        //fuck this shit why am I doing this man I just want money
        for(BankAccount objs : BankAccount.accounts) {
            if(Integer.valueOf(pin) != objs.getPinCode()) {
                System.out.println("");
            } else {
                System.out.println("PIN exists!");
                Success();
            }
        }    
        System.err.println("PIN DOES NOT EXIST");
        Thread.sleep(500);
        buffer(2000, "REDIRECTING");
        Menu();
    }
    static void Success() throws Exception {
        System.out.println("SUCCESS!");
        Menu();
    }

    public static void accountCreation() throws Exception {
        sc = new Scanner(System.in);
        System.out.println("Create PIN: ");
        int pin = sc.nextInt();
        System.out.println("Deposit balance: ");
        int blnc = sc.nextInt();
        buffer(500, "PROCESSING");
        buffer(500, "GENERATING ACCOUNT NUMBER");
        int firstFR = rand.nextInt(1, 4);
        numAccQueue++;
        String newAccNum = Integer.toString(SERIAL_NUM) 
                + Integer.toString(firstFR) 
                + Integer.toString(numAccQueue);
        createAccount(Integer.valueOf(newAccNum), blnc, pin);
    }

    static void Menu() throws Exception {
        sc = new Scanner(System.in);
        System.out.println("----------Bank Account-----------");
        //operations: create, deposit, withdraw, show
        System.out.println("Select operation:\n1. Enter PIN\n2. Create Account\n3. Exit");
        switch(sc.nextInt()) {
            case 1:
                EnterPin();
                break;
            case 2:
                accountCreation();
                break;
            case 3: 
                buffer(500, "EXITING PROGRAM");
                System.out.println("\nHAVE A GOOD DAY!");
                System.exit(0);
                break;
            default:
                return;
        }
    }
    public static void main(String[] args) throws Exception {
         Execute();
    }
}
