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
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------TASK MANAGER APP-----------");
        System.out.println("1. Add a task\n"
                + "2. Remove a task\n"
                + "3. Display list of tasks\n"
                + "4. Update task state\n"
                + "5. Exit");
        try {
            String choice = sc.nextLine();
            switch (choice) {
                case "" -> System.out.println("INPUT IS BLANK");
                case "1" -> {
                    BufferThread.buffer(500, "LOADING");
                    AddTaskSequence();
                    BufferThread.buffer(500, "GOING BACK TO MENU");
                }
                case "2" -> {
                    BufferThread.buffer(500, "LOADING");
                    RemoveTaskSequence();
                    BufferThread.buffer(500, "GOING BACK TO MENU");
                }
                case "3" -> {
                    BufferThread.buffer(500, "LOADING");
                    DisplayListTaskSequence();
                    BufferThread.buffer(500, "GOING BACK TO MENU");
                    Main();
                }
                case "4" -> UpdateTaskState();
                case "5" -> {
                    BufferThread.buffer(500, "SHUTTING DOWN");
                    System.out.println("PROGRAM SHUTDOWN");
                    System.exit(0);
                }
                default -> {
                    BufferThread.buffer(500, "LOADING");
                    System.out.println(new Exception("INVALID INPUT!"));
                    Main();
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("INVALID INPUT!");
            Main();
        }
    }

    private void AddTaskSequence() throws Exception {
        System.out.println("-------ADD A TASK--------");
        Scanner sc = new Scanner(System.in);
        System.out.println("Add task name: ");
        String name = sc.nextLine().toUpperCase();
        System.out.println("Add a description: ");
        String description = sc.nextLine().toUpperCase();
        service.addTask(name, description, Progress.IN_PROGRESS);
        Main();
    }

    private void RemoveTaskSequence() throws Exception {
        System.out.println("-------REMOVE A TASK--------");
        Scanner sc = new Scanner(System.in);
        DisplayListTaskSequence();
        System.out.println("Remove a task by ID: ");
        int taskID = sc.nextInt();
        confirmInput(taskID, sc);
    }

    private void DisplayListTaskSequence() throws Exception {
        System.out.println("----------------------------");
        for (Task task : service.getAllTask()) {
            System.out.println("Task ID: " + task.getTaskID()
                    + "\nTask Name: " + task.getTaskName()
                    + "\nTask Description: " + task.getTaskDescription()
                    + "\nTask State: " + task.getTaskState());

            System.out.println("\n----------------------------");
        }
        System.out.println("----------------------------");
    }

    private void UpdateTaskState() throws Exception {
        System.out.println("-------MARK A TASK--------");
        DisplayListTaskSequence();
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose the task by ID to modify state: ");
        int taskID = sc.nextInt();
        System.out.println("Mark task progress state: \n"
                + "1. Set to Complete\n"
                + "2. Set to On Hold\n"
                + "3. Set to Default (Progress)\n"
                + "4. Back to Menu");
        int choice = sc.nextInt();
        try {
            switch (choice) {
                case 1 -> confirmInput(taskID, sc, Progress.COMPLETE);
                case 2 -> confirmInput(taskID, sc, Progress.ON_HOLD);
                case 3 -> confirmInput(taskID, sc, Progress.IN_PROGRESS);
                case 4 -> Main();
                default -> {
                    System.out.println("INPUT UNRECOGNIZED....REDIRECTING");
                    UpdateTaskState();
                }
            }
        } catch (InputMismatchException ex) {
            ex.printStackTrace();
        }
    }

    private void confirmInput(int taskID, Scanner sc) throws Exception {
        System.out.println("Confirm Deletion? (Y | N)");
        String confirmation = sc.next().toUpperCase();
        switch (confirmation) {
            case "Y" -> {
                System.out.println("CHECKING IF THE TASK EXISTS....");
                service.removeTask(taskID);
                System.out.println("-------------------------");
                Main();
            }
            case "N" -> {
                System.out.println("TASK DELETION CANCELLED");
                System.out.println("-------------------------");
                Main();
            }
            default -> {
                System.out.println("INPUT UNRECOGNIZED....REDIRECTING");
                RemoveTaskSequence();
            }
        }
        sc.close();
    }

    private void confirmInput(int taskID, Scanner sc, Progress state) throws Exception {
        System.out.println("Confirm Request? (Y | N)");
        String confirmation = sc.next().toUpperCase();
        switch (confirmation) {
            case "Y" -> {
                System.out.println("CHECKING IF THE TASK EXISTS....");
                service.markTask(taskID, state);
                Main();
            }
            case "N" -> {
                System.out.println("MARK TASK STATE CANCELLED");
                Main();
            }
            default -> {
                System.out.println("INPUT UNRECOGNIZED....REDIRECTING");
                UpdateTaskState();
            }
        }
        sc.close();
    }
}
