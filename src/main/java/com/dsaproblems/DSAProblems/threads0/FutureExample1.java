package com.dsaproblems.DSAProblems.threads0;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//The Future interface is used to represent the result of an asynchronous
// computation. It allows you to check if the task is complete, retrieve the
// result, or cancel the task.
public class FutureExample1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //ExecutorService executor = Executors.newSingleThreadExecutor();
        //ExecutorService executor = Executors.newCachedThreadPool();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            if (i == 50)
                System.out.println("Wait");
            GetNumber getNumber = new GetNumber(i);
            Future<Integer> future = executor.submit(getNumber);
            int result = future.get();
            System.out.println(result);
        }

        executor.shutdown(); // It prevents new tasks from being submitted, allowing existing tasks to complete.
    }
}
