package com.taskmanager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private int taskID = Integer.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"))) * 10000;
    private String taskName;
    private String taskDescription;
    private Progress state;

    public Task(String taskName, String taskDescription, Progress state) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.state = state;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void setTaskState(Progress state) {
        this.state = state;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public int getTaskID() {
        return taskID;
    }

    public Progress getTaskState() {
        return this.state;
    }
}
