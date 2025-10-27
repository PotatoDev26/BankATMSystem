package com.taskmanager;

import java.util.InputMismatchException;
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

        try {
            Scanner sc = new Scanner(System.in);
            String choice = Integer.toString(sc.nextInt());
            switch (choice) {
                case "" -> System.out.println("INPUT IS BLANK");
                case "1" -> AddTaskSequence();
                case "2" -> RemoveTaskSequence();
                case "3" -> {
                    DisplayListTaskSequence();
                    Main();
                }
                case "4" -> MarkTaskCompleteSequence();
                case "5" -> {
                    System.out.println("EXIT PROGRAM");
                    System.exit(0);
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("INVALID INPUT!");
            Main();
        }
    }

    private void AddTaskSequence() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add task name: ");
        String name = sc.nextLine();
        System.out.println("Add a description: ");
        String description = sc.nextLine();
        service.addTask(name, description);

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
                System.out.println("CHECKING IF THE TASK EXISTS....");
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
    }

    public static void getMain(TaskController controller, boolean mainSwitch) throws Exception {
        TaskController control = controller;
        if (mainSwitch) {
            control.Main();
        }
    }

    private void MarkTaskCompleteSequence() throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'MarkTaskCompleteSequence'");
    }

}
