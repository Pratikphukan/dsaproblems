package com.dsaproblems.DSAProblems.threads;

public class Average implements Runnable {

    int[] marks;

    public Average(int[] marks) {
        this.marks = marks;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread name: " + Thread.currentThread().getName() + " - Adding marks for subject " + (i + 1) + ": " + marks[i]);
            sum += marks[i];
        }
        System.out.println("Thread name: " + Thread.currentThread().getName() + " - Average marks for student: " + (sum / 10.0));
    }
}
