package com.helpers;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;
import com.bankaccountatm.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class DataHelper {
    static final String jsonDir = "C:\\Users\\Matthew\\Desktop\\Folders" 
                                + "\\Progamming\\Java\\recent\\maven projects"
                                + "\\bank-atm-account\\src\\main\\resources\\data";
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static final String fileName = "accounts-records.json";
    private static final File file = new File(jsonDir, fileName);
    //-----------------------------------------------------------------------------------
    public static ArrayList<Account> retrieveDataFromJSON() throws Exception {
        ArrayList<Account> accs = new ArrayList<>();
        try (FileReader reader = new FileReader(file)) {
            Type type = new TypeToken<ArrayList<Account>>(){}.getType();
            accs = gson.fromJson(reader, type);   
        } 
        return accs;
    }
    //------------------------------------------------------------------------------------
    public static void storeDataToJSON(ArrayList<Account> account) throws Exception{
        FileWriter record = new FileWriter(file);
        gson.toJson(account, record);
        record.close();
    }
    //-------------------------------------------------------------------------------------
    static void readSequence(File file, boolean hasData) throws Exception{
        if (file.length() > 0) {
            try(FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr)) { 
                int ch;
                while ((ch = br.read()) != -1) {
                    if (!Character.isWhitespace(ch)) {
                        break;
                    }
                }
            }
        }
    }
    //--------------------------------------------------------------------------------------
    public static void checkIfFileHasNoContent(Account account) throws Exception {
        File file = getFile();
        boolean fileHasData = false;
        if (file != null && file.exists()) {
            readSequence(file, fileHasData);
        }
        if(!fileHasData) {
            Account.accounts.add(account);
        } else {
            Account.accounts = DataHelper.retrieveDataFromJSON();
        }
    }
    public static File getFile() {
        return file;
    }
}
