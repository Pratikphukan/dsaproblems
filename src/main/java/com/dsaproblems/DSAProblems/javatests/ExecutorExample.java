package com.dsaproblems.DSAProblems.javatests;

import java.util.concurrent.*;

//@FunctionalInterface
interface MyInterface {
    void myMethod(int x, int y);

    boolean equals(Object o);

    void myMethod();

    default void defaultMethod() {
        System.out.println("defaultMethod");
    }

    static void staticMethod() {
        System.out.println("staticMethod");
    }
}

class MyInterfaceImpl implements MyInterface {
    @Override
    public void myMethod(int x, int y) {
    }

    @Override
    public void myMethod() {
        System.out.println("myMethod");
    }
}

public class ExecutorExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> task1 = () -> {
            return "Task completed";
        };
        Future<String> future1 = executorService.submit(task1);
        boolean isCancelled = future1.cancel(true);


        Callable<String> task2 = () -> {
            throw new RuntimeException("Task failed");
        };
        Future<String> future2 = executorService.submit(task2);
        try {
            String result = future2.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        executorService.shutdown();


        MyInterface myInterface = new MyInterfaceImpl();
        myInterface.defaultMethod();

        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
        Callable<Integer> task3 = () -> {
            Thread.sleep(2000);
            return 42;
        };

        Future<Integer> future3 = executorService1.submit(task3);

        boolean isDone = future3.isDone();
        executorService1.shutdown();

//        int x = 10;
//        x = 12;
//        Runnable r = () -> System.out.println(x);
//        Thread t = new Thread(r);
//        t.start();
    }
}
