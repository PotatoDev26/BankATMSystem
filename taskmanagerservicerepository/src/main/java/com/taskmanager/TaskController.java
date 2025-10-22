package com.taskmanager;

import java.util.Scanner;

public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    public void RunApplication() {
        Execute();
    }

    public void Execute() {
        Main();
    }

    public void Main() {
        System.out.println("-----------TASK MANAGER APP-----------");
        System.out.println("1. Add a task\n"
                + "2. Remove a task\n"
                + "3. Display list of tasks\n"
                + "4. Mark a task as complete\n"
                + "5. Exit");
        switch (new Scanner(System.in).nextInt()) {
            case 1 -> AddTaskSequence();
            case 2 -> RemoveTaskSequence();
            case 3 -> DisplayListTaskSequence();
            case 4 -> MarkTaskCompleteSequence();
            case 5 -> {
                System.out.println("EXIT PROGRAM");
                System.exit(0);
            }
            default -> {
                System.out.println("WRONG!");
                Main();
            }
        }
    }

    private void AddTaskSequence() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add task name: ");
        String name = sc.nextLine();
        System.out.println("Add a description: ");
        String description = sc.nextLine();

        // ADD TASK SERVICE FUNCTION
    }

    private void RemoveTaskSequence() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add task name: ");
        String name = sc.nextLine();
        System.out.println("Add a description: ");
        String description = sc.nextLine();
        // ADD TASK SERVICE FUNCTION
    }

    private void DisplayListTaskSequence() {
        // TODO Auto-generated method stub
    }

    private void MarkTaskCompleteSequence() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'MarkTaskCompleteSequence'");
    }

}
