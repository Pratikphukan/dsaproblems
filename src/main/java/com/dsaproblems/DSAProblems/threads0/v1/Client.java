package com.dsaproblems.DSAProblems.threads0.v1;


import java.util.ArrayList;
import java.util.List;

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

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            if (i == 50)
                System.out.println("Wait");
            PrintNumber printNumber = new PrintNumber(i);
            Thread thread = new Thread(printNumber);
            thread.start();
            threads.add(thread);
        }
    }

}
