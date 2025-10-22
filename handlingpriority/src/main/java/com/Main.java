package com;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] device = {"Keyboard", "Printer", "Mouse"};
        Comparator<InterruptRequests> comp = (a, b) -> { return Integer.compare(a.getQueue(), b.getQueue()); };
        PriorityQueue<InterruptRequests> requests = new PriorityQueue<>(comp);
        for(int i = 0; i < device.length; i++) {
             requests.add(new InterruptRequests(device[i], i+1));
        }
        System.out.println("CPU is idle...\n");
        for (InterruptRequests obj : requests) {
            System.out.println("Handling interrupt from: " + obj.getDevice() + ": " + obj.getQueue() + "\n");
            Thread.sleep(1000);
        }
        System.out.println("All interrupts handled. CPU resuming normal task.");
    }   
}
