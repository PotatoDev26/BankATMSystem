package com.example;

import java.util.ArrayList;

public class Main {

    private static void functionThread(String[] p) throws Exception {
        for (int i = 0; i < 2; i++) {
            System.out.println();
            for (int j = 0; j < 3; j++) {
                System.out.println("Running " + p[j] + " at PC = " + i + "\n");
                Thread.sleep(1000);
            }
        }
        System.out.println("Simulation Complete.");
    }

    public static void main(String[] args) throws Exception {
        String[] process = { "Process 1", "Process 2", "Process 3" };
        Process process1 = (p) -> {
            try {
                functionThread(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Runnable r1 = () -> process1.Run(process);
        Thread thread = new Thread(r1);
        thread.start();
    }
}