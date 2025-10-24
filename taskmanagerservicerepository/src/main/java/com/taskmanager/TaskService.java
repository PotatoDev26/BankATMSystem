package com.taskmanager;

import java.util.ArrayList;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(String taskName, String taskDescription) throws Exception {
        taskRepository.saveTask(new Task(taskName, taskDescription));
    }

    public ArrayList<Task> getAllTask() throws Exception {
        return taskRepository.retrieveTasks();
    }

    public void removeTask(String task) throws Exception {
        taskRepository.deleteTask(task);
    }
}
