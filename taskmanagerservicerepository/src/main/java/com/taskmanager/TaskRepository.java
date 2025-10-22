package com.taskmanager;

import java.util.ArrayList;

public interface TaskRepository {
    public void saveTask(Task task);

    public ArrayList<Task> retrieveTask();

    public void removeTask(Task task);
}
