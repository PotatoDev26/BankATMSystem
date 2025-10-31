package com.taskmanager;

public class Main {
    void main() throws Exception {
        TaskRepository repository = new TaskJSONRepository();
        TaskController taskController = new TaskController(new TaskService(repository));
        taskController.RunApplication();
    }
}