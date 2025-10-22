package com;

public class InterruptRequests {
    private String device;
    private int queue;
    InterruptRequests() {}
    InterruptRequests(String device, int queue) {
       this.device = device;
       this.queue = queue;
    }
    public String getDevice() {
        return this.device;
    }
    public int getQueue() {
        return this.queue;
    }
}
