package com;

public interface Progress {

    public static void buffer(int milsecs, String msg) throws Exception {
        System.out.print(msg);
        for(int i = 0; i < 4; i++) {
            System.out.print(" .");
            Thread.sleep(milsecs);
        }
        System.out.println("");
    }
    public static void buffer(String msg) throws Exception {
        int milsecs = 100;
        System.out.print(msg);
        for(int i = 0; i < 4; i++) {
            System.out.print(" .");
            Thread.sleep(milsecs);
        }
        System.out.println("");
    }
    public static void buffer(int milsecs) throws Exception {
        for(int i = 0; i < 4; i++) {
            System.out.print(".");
            Thread.sleep(milsecs);
        }
    }
}
