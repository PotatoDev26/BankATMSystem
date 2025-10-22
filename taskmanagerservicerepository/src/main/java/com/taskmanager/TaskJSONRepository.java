package com.taskmanager;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class TaskJSONRepository implements TaskRepository {
    private final String filePath = "C:\\Users\\Matthew\\Desktop\\Folders\\Progamming\\Java\\recent"
            + "\\maven projects\\taskmanagerservicerepository\\src\\main\\resources\\data-repository";
    private final String fileName = "repository.json";
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final File file = new File(getFilePath(), getFileName());
    private ArrayList<Task> objectTask;

    @Override
    public void saveTask(Task task) {
        objectTask = new ArrayList<>();
        objectTask.add(task);
        try (FileWriter writer = new FileWriter(getFile())) {
            gson.toJson(getTasks(), writer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Task> retrieveTask() {
        // ArrayList<Task> tasker = new ArrayList<>();
        // if (getFile() == null || !getFile().exists() || getFile().length() == 0) {
        // System.out.println("FUCK NO FILES? GGWP");
        // }
        // try (BufferedReader bfr = new BufferedReader(new FileReader(getFile()))) {
        // int ch;
        // while ((ch = bfr.read()) != -1) {
        // if (!Character.isWhitespace(ch)) {
        // tasker.add();
        // }
        // }
        // }
        // return;
        throw new UnsupportedOperationException("Unimplemented method 'retrieveTask'");
    }

    @Override
    public void removeTask(Task task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeTask'");
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public static Gson getGson() {
        return gson;
    }

    public File getFile() {
        return file;
    }

    public ArrayList<Task> getTasks() {
        return objectTask;
    }

}
