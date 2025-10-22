package com.taskmanager;

import java.util.ArrayList;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(String taskName, String taskDescription, int taskID, Progress state) {
        Task task = new Task(taskName, taskDescription, taskID, state);
        taskRepository.saveTask(task);
    }

    public ArrayList<Task> getAllTask() {
        return taskRepository.retrieveTask();
    }
}
