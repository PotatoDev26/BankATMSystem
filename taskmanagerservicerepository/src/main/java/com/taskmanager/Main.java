package com.taskmanager;

public class Main {
    public static void main(String[] args) {
        TaskRepository repository = new TaskJSONRepository();
        TaskService services = new TaskService(repository);
        TaskController taskController = new TaskController(services);
        taskController.RunApplication();
    }
}