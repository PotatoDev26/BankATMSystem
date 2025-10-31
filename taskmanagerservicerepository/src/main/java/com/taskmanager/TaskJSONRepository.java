package com.taskmanager;

import java.util.*;
import java.lang.reflect.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;

public class TaskJSONRepository implements TaskRepository {
    private final String filePath = "C:\\Users\\Matthew\\Desktop\\Folders\\Progamming\\Java\\recent"
            + "\\maven projects\\taskmanagerservicerepository\\src\\main\\resources\\data-repository";
    private final String fileName = "repository.json";
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final File file = new File(getFilePath(), getFileName());
    private static Task tempTask;

    @Override
    public void saveTask(Task task) throws FileNotFoundException, IOException, InterruptedException {
        ArrayList<Task> taskList = new ArrayList<>();
        if (!ifFileNotExistOrEmpty(getJSONFILE())) {
            System.out.println("FILE WAS EMPTY, THIS IS THE FIRST TASK....");
            Thread.sleep(1000);
            finalizeSaveTask(taskList, task);
        } else {
            taskList = retrieveTasks();
            finalizeSaveTask(taskList, task);
        }
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
    public void deleteTask(int taskID) throws FileNotFoundException, NullPointerException, InterruptedException {
        if (!ifFileNotExistOrEmpty(getJSONFILE())) {
            System.out.println("THERE ARE NO TASKS TO DELETE (JSON FILE IS EMPTY)...");
            return;
        } else {
            ArrayList<Task> taskList = retrieveTasks();
            finalizeDeleteTask(taskList, taskID);
        }
    }

    @Override
    public void markAsCompleteTask(int taskID, Progress state) throws Exception {
        if (!ifFileNotExistOrEmpty(getJSONFILE())) {
            System.out.println("THERE ARE NO TASKS TO DELETE (JSON FILE IS EMPTY)...");
            return;
        } else {
            ArrayList<Task> taskList = retrieveTasks();
            finalizeTaskState(taskList, taskID, state);
        }
    }

    private void finalizeDeleteTask(ArrayList<Task> map, int taskID) throws InterruptedException {
        BufferThread.buffer(500, "PROCESSING");
        boolean isEqual = false;
        int i = 0;
        for (Task obj : map) {
            if (obj.getTaskID() == taskID) {
                isEqual = true;
                break;
            }
            i++;
        }
        if (isEqual) {
            Task task = map.get(i);
            map.remove(task);
            try (FileWriter writer = new FileWriter(getJSONFILE())) {
                gson.toJson(map, writer);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(task.getTaskName().toUpperCase() + " HAS BEEN DELETED!");
        } else {
            System.out.println("TASK DOES NOT EXIST!");
        }
    }

    private void finalizeSaveTask(ArrayList<Task> map, Task task) throws InterruptedException {
        BufferThread.buffer(500, "PROCESSING");
        for (Task obj : map)
            tempTask = obj;
        task.setTaskID(tempTask.getTaskID() + 1);
        map.add(task);
        System.out.println("\n");
        try (FileWriter writer = new FileWriter(getJSONFILE())) {
            gson.toJson(map, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("TASK HAS BEEN ADDED TO JSON DATA FORMAT!");
    }

    private void finalizeTaskState(ArrayList<Task> map, int taskID, Progress state) throws InterruptedException {
        BufferThread.buffer(500, "PROCESSING");
        boolean isEqual = false;
        int i = 0;
        for (Task obj : map) {
            if (obj.getTaskID() == taskID) {
                isEqual = true;
                break;
            }
            i++;
        }
        if (isEqual) {
            Task task = map.get(i);
            // map.remove(task);
            task.setTaskState(state);
            // map.add(task);
            try (FileWriter writer = new FileWriter(getJSONFILE())) {
                gson.toJson(map, writer);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(task.getTaskName().toUpperCase() + " STATE HAS BEEN UPDATED!");
        } else {
            System.out.println("TASK DOES NOT EXIST!");
        }
    }

    private boolean ifFileNotExistOrEmpty(File file) throws FileNotFoundException {
        boolean metCondition = false;
        if (file == null || !file.exists() || file.length() == 0) {
            IO.println();
        } else {
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
        }
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

    public Gson getGson() {
        return gson;
    }

    public File getJSONFILE() {
        return file;
    }

}
