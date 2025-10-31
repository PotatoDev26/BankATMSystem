package com.taskmanager;

import java.util.ArrayList;

public interface TaskRepository {
    public void saveTask(Task task) throws Exception;

    public ArrayList<Task> retrieveTasks() throws Exception;

    public void deleteTask(int ID) throws Exception;

    public void markAsCompleteTask(int ID, Progress state) throws Exception;
}
