package com.taskmanager;

public class Main {
    void main() throws Exception {
        TaskRepository repository = new TaskJSONRepository();
        TaskService services = new TaskService(repository);
        TaskController taskController = new TaskController(services);
        taskController.RunApplication();
    }
}