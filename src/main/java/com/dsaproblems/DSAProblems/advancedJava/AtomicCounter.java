package com.dsaproblems.DSAProblems.advancedJava;

import java.util.concurrent.atomic.AtomicInteger;

/*
An atomic integer is a thread-safe integer provided by the java.util.concurrent.atomic.AtomicInteger class.
It allows atomic operations like increment, decrement, and update without using explicit synchronization,
preventing race conditions in concurrent environments. Operations on an atomic integer are performed using
low-level CPU instructions, ensuring visibility and atomicity across threads
 */
public class AtomicCounter {

    private AtomicInteger number = new AtomicInteger(0);

    private void incrementCount() {
        number.incrementAndGet();
    }

    private int getCounter() {
        return number.get();
    }

    public static void main(String[] args) {

        AtomicCounter counter = new AtomicCounter();

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.incrementCount();
                }
            });
            t.start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        int finalCounterValue = counter.getCounter();
        System.out.println(finalCounterValue);
    }
}
