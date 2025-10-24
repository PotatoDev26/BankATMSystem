package com.taskmanager;

import java.util.ArrayList;
import java.lang.reflect.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;

public class TaskJSONRepository implements TaskRepository {
    private final String filePath = "C:\\Users\\Matthew\\Desktop\\Folders\\Progamming\\Java\\recent"
            + "\\maven projects\\taskmanagerservicerepository\\src\\main\\resources\\data-repository";
    private final String fileName = "repository.json";
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final File file = new File(getFilePath(), getFileName());

    @Override
    public void saveTask(Task task) throws Exception {
        if (ifFileNotExistOrEmpty(getJSONFILE())) {
            TaskController.getMain();
        }
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);
        try (FileWriter writer = new FileWriter(getJSONFILE())) {
            gson.toJson(taskList, writer);
        }
        System.out.println("TASK HAS BEEN ADDED TO JSON DATA FORMAT!");
    }

    @Override
    public ArrayList<Task> retrieveTasks() throws FileNotFoundException {
        ArrayList<Task> retriever = new ArrayList<>();
        try (FileReader reader = new FileReader(getJSONFILE())) {
            Type type = new TypeToken<ArrayList<Task>>() {
            }.getType();
            retriever = gson.fromJson(reader, type);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return retriever;
    }

    @Override
    public void deleteTask(String stringTask) throws FileNotFoundException, NullPointerException {
        if (ifFileNotExistOrEmpty(getJSONFILE())) {
            System.out.println("THERE ARE NO TASKS TO DELETE (JSON FILE IS EMPTY)...");
            try {
                TaskController.getMain();
            } catch (Exception e) {
                System.out.println("ATTEMPT TO RETURN TO MAIN CONTROLLER INTERCEPTED AN " + e.getMessage());
            }
        }
        ArrayList<Task> taskList = retrieveTasks();
        for (Task task : taskList) {
            if (task.getTaskName().equals(stringTask)) {
                taskList.remove(task);
                try (FileWriter writer = new FileWriter(getJSONFILE())) {
                    gson.toJson(taskList, writer);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private boolean ifFileNotExistOrEmpty(File file) throws FileNotFoundException {
        boolean metCondition = false;
        if (file == null || !file.exists() || file.length() == 0) {
            System.out.println("F**K NO FILES? GGWP");
        }
        try (BufferedReader bfr = new BufferedReader(new FileReader(file))) {
            int ch;
            while ((ch = bfr.read()) != -1) {
                if (!Character.isWhitespace(ch)) {
                    metCondition = true;
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // conditional asking if boolean is true
        if (metCondition == true) {
            // returns true
            return metCondition;
        } else {
            // returns false
            return metCondition;
        }
    }

    // VARIABLE GETTERS
    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public static Gson getGson() {
        return gson;
    }

    public File getJSONFILE() {
        return file;
    }

}
