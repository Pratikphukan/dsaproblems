package com.dsaproblems.DSAProblems.threads;

public class Marks implements Runnable {

    int[] marks;

    public Marks(int[] marks) {
        this.marks = marks;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            double points = Math.random() * 100;
            marks[i] = (int) points;
            System.out.println("Thread name: " + Thread.currentThread().getName() + " - Entered marks for subject " + (i + 1) + ": " + marks[i]);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
