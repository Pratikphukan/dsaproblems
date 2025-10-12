package com.dsaproblems.DSAProblems.threads0.v3;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class PrintNumber implements Runnable {

    int number;

    PrintNumber(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("Number :: " + number + " " + Thread.currentThread().getName());
    }
}

public class Client {

    public static void main(String[] args) {

        System.out.println("Thread name :: " + Thread.currentThread().getName());

        Executor executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            if (i == 50)
                System.out.println("Wait");
            PrintNumber printNumber = new PrintNumber(i);
            executor.execute(printNumber);
        }
    }

}
