package com.dsaproblems.DSAProblems.aqr;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class RunnableImplement implements Runnable {

    @Override
    public void run() {
        System.out.println("Hello from " + Thread.currentThread().getName());
    }
}

public class ExecutorsExample {

    public static void main(String[] args) {
        System.out.println("Inside : " + Thread.currentThread().getName());
        System.out.println("Creating Executor Service...");
        ExecutorService excecutorService = Executors.newFixedThreadPool(2);
        System.out.println("Creating a Runnable...");

        Runnable runnable1 = new RunnableImplement();
        Runnable runnable2 = new RunnableImplement();
        Runnable runnable3 = new RunnableImplement();

        System.out.println("Submit the task specified by the runnable to the executor service.");
        excecutorService.submit(runnable1);
        excecutorService.submit(runnable2);
        excecutorService.submit(runnable3);

        excecutorService.shutdown();
    }
}
