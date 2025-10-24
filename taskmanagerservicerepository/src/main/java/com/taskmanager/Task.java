package com.taskmanager;

public class Task {
    private String taskName;
    private String taskDescription;
    // private int taskID;
    // private Progress state;

    public Task(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        // this.taskID = taskID;
        // this.state = state;
    }
    // Setters

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    // public void setTaskID(int taskID) {
    // this.taskID = taskID;
    // }

    // public void setTaskIsCompletes(Progress state) {
    // this.state = state;
    // }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    // public int getTaskID() {
    // return taskID;
    // }

    // public Progress isTaskIsCompletes() {
    // return this.state;
    // }

}

enum Progress {
    COMPLETE,
    REMOVED,
    IN_PROGRESS;
}
