package com.dsaproblems.DSAProblems.advancedJava;

public class VolatileVisibility {

    //The volatile keyword in Java ensures that changes to a variable are
    // immediately visible to all threads. It prevents threads from caching
    // the variable locally, forcing them to always read its value from
    // main memory. This is useful for simple flags or state variables
    // shared between threads, ensuring visibility but not atomicity.
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("Waiting for the flag to become true...");
            while (!flag) {
                //busy waiting
            }
            System.out.println("Flag is now true");
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        flag = true;
    }
}
