package com.dsaproblems.DSAProblems.threads0;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCancelExample {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<Void> longTask = () -> {
            try {
                System.out.println("Task started.");
                Thread.sleep(5000);
                System.out.println("Task completed.");
            } catch (InterruptedException e) {
                System.out.println("Task was interrupted and cancelled.");
            }
            return null;
        };

        Future<Void> future = executor.submit(longTask);
        Thread.sleep(1000);
        boolean cancelled = future.cancel(true);

        System.out.println("Cancelled: " + cancelled);

        executor.shutdown();
    }
}
