package com.helpers;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;
import com.bankaccountatm.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class DataHelper {
    private static final String jsonDir = "C:\\Users\\Matthew\\Desktop\\Folders" 
                                + "\\Progamming\\Java\\recent\\maven projects"
                                + "\\bank-atm-account\\src\\main\\resources\\data";
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String fileName = "accounts-records.json";
    private static final File file = new File(jsonDir, fileName);

    public static ArrayList<Account> retrieveDataFromJSON() throws Exception {
        ArrayList<Account> accs = new ArrayList<>();
        try (FileReader reader = new FileReader(file)) {
            Type type = new TypeToken<ArrayList<Account>>(){}.getType();
            accs = gson.fromJson(reader, type);   
        } 
        return accs;
    }
    public static void storeDataToJSON(ArrayList<Account> account) throws Exception{
        FileWriter record = new FileWriter(file);
        gson.toJson(account, record);
        record.close();
    }
    //
    public static void checkIfFileHasNoContent(Account account) throws Exception {
        File file = getFile();
        // Quick checks that avoid nested blocks
        if (file == null || !file.exists() || file.length() == 0) {
            Account.accounts.add(account);
            return;
        }
        boolean fileHasData = false;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int ch;
            while ((ch = br.read()) != -1) {
                if (!Character.isWhitespace(ch)) {
                    fileHasData = true;
                    break;
                }
            }
        }
        if (!fileHasData) {
            Account.accounts.add(account);
        } else {
            Account.accounts = retrieveDataFromJSON();
        }
    }
// ...existing code...
    public static File getFile() {
        return file;
    }
}
