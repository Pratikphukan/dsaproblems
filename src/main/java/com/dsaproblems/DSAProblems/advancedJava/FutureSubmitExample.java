package com.dsaproblems.DSAProblems.advancedJava;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
A thread in Java goes through several states in its lifecycle:
NEW: The thread is created but not yet started.
RUNNABLE: The thread is ready to run and waiting for CPU scheduling.
BLOCKED: The thread is waiting to acquire a monitor lock to enter or re-enter a synchronized block/method.
WAITING: The thread is waiting indefinitely for another thread to perform a particular action (e.g., Object.wait()).
TIMED_WAITING: The thread is waiting for another thread to perform an action within a specified waiting time (e.g., Thread.sleep(), Object.wait(long timeout)).
TERMINATED: The thread has completed execution.
 */
class ExampleTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        return 42;
    }
}

public class FutureSubmitExample {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ExampleTask task = new ExampleTask();
        //submit() is a method of ExecutorService
        //Its purpose is to submit a task (Runnable or Callable) for asynchronous execution and
        // return a Future representing the pending result
        Future<Integer> future = executor.submit(task);

        boolean isDone = future.isDone();
        Integer result = future.get(); //retrieving the result
        System.out.println("Result: " + result);

        executor.shutdown();
    }
}
