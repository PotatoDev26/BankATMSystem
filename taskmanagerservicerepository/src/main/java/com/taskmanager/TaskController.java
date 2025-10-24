package com.taskmanager;

import java.util.Scanner;

public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    public void RunApplication() throws Exception {
        Execute();
    }

    public void Execute() throws Exception {
        Main();
    }

    public void Main() throws Exception {
        System.out.println("-----------TASK MANAGER APP-----------");
        System.out.println("1. Add a task\n"
                + "2. Remove a task\n"
                + "3. Display list of tasks\n"
                + "4. Mark a task as complete\n"
                + "5. Exit");
        try (Scanner sc = new Scanner(System.in)) {
            switch (sc.nextInt()) {
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
    }

    private void AddTaskSequence() throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Add task name: ");
            String name = sc.nextLine();
            System.out.println("Add a description: ");
            String description = sc.nextLine();
            service.addTask(name, description);
        }
        Main();
    }

    private void RemoveTaskSequence() throws Exception {
        Scanner sc = new Scanner(System.in);
        DisplayListTaskSequence();
        System.out.println("Remove a task by name: ");
        String taskString = sc.nextLine();
        System.out.println("Confirm deletion? (Y | N)");
        String confirmation = sc.nextLine().toUpperCase();

        switch (confirmation) {
            case "Y" -> {
                System.out.println("DELETING TASK....");
                service.removeTask(taskString);
                Main();
            }
            case "N" -> {
                System.out.println("TASK DELETION CANCELLED");
                Main();
            }
            default -> {
                System.out.println("INPUT UNRECOGNIZED....REDIRECTING");
                RemoveTaskSequence();
            }
        }
    }

    private void DisplayListTaskSequence() throws Exception {
        System.out.println("-------LIST OF TASKS--------");
        for (Task task : service.getAllTask()) {
            System.out.println("Task Name: " + task.getTaskName()
                    + "\nTask Description: " + task.getTaskDescription());
        }
        System.out.println("----------------------------");
        Main();
    }

    public static void getMain() throws Exception {
        TaskController controller = null;
        controller.Main();
    }

    private void MarkTaskCompleteSequence() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'MarkTaskCompleteSequence'");
    }

}
