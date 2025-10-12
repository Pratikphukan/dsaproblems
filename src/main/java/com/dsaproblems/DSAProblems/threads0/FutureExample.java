package com.dsaproblems.DSAProblems.threads0;

import java.util.concurrent.*;

class GetNumber implements Callable<Integer> {

    int number;

    GetNumber(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Number :: " + number + " " + Thread.currentThread().getName());
        return this.number;
    }
}

public class FutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //a task is submitted to an ExecutorService, and its result is retrieved using Future
        ExecutorService executor = Executors.newSingleThreadExecutor();
        GetNumber getNumber = new GetNumber(100);
        Future<Integer> future = executor.submit(getNumber);
        System.out.println("Task submitted, waiting for result...");
        int result = future.get();
        System.out.println("Result: " + result);
        executor.shutdown();
    }
}
