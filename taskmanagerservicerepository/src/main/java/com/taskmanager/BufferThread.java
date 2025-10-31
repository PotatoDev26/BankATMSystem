package com.taskmanager;

public class BufferThread {
    public static void buffer(int milsecs, String msg) throws InterruptedException {
        System.out.print(msg);
        for (int i = 0; i < 4; i++) {
            System.out.print(" .");
            Thread.sleep(milsecs);
        }
        System.out.println("");
    }

    public static void buffer(int milsecs) throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            System.out.print(".");
            Thread.sleep(milsecs);
        }
        System.out.println("");
    }
}
