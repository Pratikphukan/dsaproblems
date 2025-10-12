package com.dsaproblems.DSAProblems.threads;

public class ThreadPause {

    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            System.out.println("Main thread is printing: " + i);
            if (i == 5) {
                System.out.println("Pausing main thread for 4 seconds...");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
