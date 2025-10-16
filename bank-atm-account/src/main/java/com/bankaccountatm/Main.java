package com.bankaccountatm;
import java.util.*;
import com.Progress;
import com.helpers.DataHelper;

public class Main extends UserRequests implements Progress {
    private static final int INI_SERIAL_NUM = 10000; 
    private static Account accountObj;
    private static Scanner sc;
    //program switch function DO NOT TOUCH!!!-----------------------------------------------------------
    private static void Execute() throws Exception {
        Menu();
    }
    //--------------------------------------------------------------------------------------------------
    private static void setAccountObj(Account account) {
        accountObj = account;
    }
    private static Account getAccountObj() {
        return accountObj;
    }
    //Bank account creation function--------------------------------------------------------------------
    protected static void createAccount(int accnum, double bal, int pin) throws Exception {
        DataHelper.checkIfFileHasNoContent(new Account(INI_SERIAL_NUM, 0.0, 0));
        for(Account account : DataHelper.retrieveDataFromJSON()) setAccountObj(account);
        int number = getAccountObj().getAccountNum() + 1;
        Progress.buffer(300, "PROCESSING");
        if(!Account.isFourDigits(pin)) {
            Progress.buffer("INPUT DIGIT MUST NOT BE MORE THAN 4\nREDIRECTING");
            return;                
        }
        if(!Account.balanceIsNotZeroOrNegative(bal)) {
            Progress.buffer("BALANCE MUST NOT BE IN NEGATIVES or ZERO");
            Main.accountCreation();
        }
        Progress.buffer(300, "GENERATING ACCOUNT NUMBER");
        Progress.buffer(300, "FINALIZING ACCOUNT");
        System.out.println("ACCOUNT CREATED!");
        Account acc = new Account(number, bal, pin);
        Account.showCreatedAcc(acc);
        DataHelper.storeDataToJSON(Account.accounts);
        Thread.sleep(1000);
        Progress.buffer(300, "RETURNING TO MENU");
        Menu();
    }
    //--------------------------------------------------------------------------------------------------
    protected static void accountCreation() throws Exception {
        sc = new Scanner(System.in);
        System.out.println("Create PIN: ");
        int pin = sc.nextInt();
        System.out.println("Deposit balance: ");
        int blnc = sc.nextInt();
        createAccount(pin, blnc, pin);
    }
    protected static void confirmedPin(Account account, ArrayList<Account> accountList) throws Exception {
        Progress.buffer(300, "CHECKING IF PIN CODE AND ACCOUNT EXISTS");
        System.out.println("PIN EXISTS!");
        SelectOperation(account, accountList);
    }
    //--------------------------------------------------------------------------------------------------
    protected static void EnterPinToLog() throws Exception {
        ArrayList<Account> accountList = DataHelper.retrieveDataFromJSON();
        sc = new Scanner(System.in);
        boolean confirmPin = false;
        System.out.println("Enter PIN number: ");
        String pin = sc.nextLine();
        for(Account accs : accountList) {
            if(Integer.valueOf(pin) == accs.getPinCode()) {
                setAccountObj(accs);
                confirmPin = true;
            }
        }
        if(!confirmPin) {
            System.err.println("PIN DOES NOT EXIST");
            Thread.sleep(500);
            Progress.buffer(300, "REDIRECTING");
            Menu();
        } else {
            confirmedPin(getAccountObj(), accountList);
        }
    }
    //--------------------------------------------------------------------------------------------------
    protected static void SelectOperation(Account account, ArrayList<Account> list) throws Exception {
        sc = new Scanner(System.in);
        System.out.println("----------Select an Operation-----------");
        System.out.println("1. Withdraw\n2. Deposit\n3. Check Balance\n4. Back");
        String select = sc.nextLine();
        switch(Integer.valueOf(select)) {
            case 1 -> UserRequests.cashWithdrawal(account, list);
            case 2 -> UserRequests.depositCash(account, list);
            case 3 -> UserRequests.getOpenBalance(account, list);
            case 4 -> Menu();
            default -> SelectOperation(account, list);
        }
    }
    //Menu user function--------------------------------------------------------------------------------
    protected static void Menu() throws Exception {
        sc = new Scanner(System.in);
        System.out.println("----------Bank Account-----------");
        //operations: create, deposit, withdraw, show
        System.out.println("Select operation:\n1. Log In Account (PIN)\n2. Create Account\n3. Exit");
        switch(sc.nextInt()) {
            case 1: EnterPinToLog(); break;
            case 2: accountCreation(); break;
            case 3: Progress.buffer(500, "EXITING PROGRAM");
                    System.out.println("\nHAVE A GOOD DAY!");
                    System.exit(0); break;
            default: 
                Progress.buffer(300, ""); 
                break;
        }
    }
    public static void main(String[] args) throws Exception {
        Execute();
    }
}