package com.taskmanager;

import java.util.ArrayList;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(String taskName, String taskDescription, Progress state) throws Exception {
        taskRepository.saveTask(new Task(taskName, taskDescription, state));
    }

    public ArrayList<Task> getAllTask() throws Exception {
        return taskRepository.retrieveTasks();
    }

    public void removeTask(int ID) throws Exception {
        taskRepository.deleteTask(ID);
    }

    public void markTask(int ID, Progress state) throws Exception {
        taskRepository.markAsCompleteTask(ID, state);
    }
}
