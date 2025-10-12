package com.dsaproblems.DSAProblems.threads;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {

    public static void main(String[] args) {
        log.info("Thread name :: {}", Thread.currentThread().getName());
        log.info("Hello World!!");

        //ways to create a thread using Thread class
        //way1
        PrintHelloWorldThread printHelloWorldThread1 = new PrintHelloWorldThread();
        printHelloWorldThread1.run();//this will not execute the thread, it will run in the main thread
        printHelloWorldThread1.start();

        //way2
        Thread printHelloWorldThread2 = new PrintHelloWorldThread();
        printHelloWorldThread2.start();

        //way3
        PrintHelloWorldThread printHelloWorldThread3 = new PrintHelloWorldThread();
        Thread thread1 = new Thread(printHelloWorldThread3);
        thread1.start();

        //ways to create a thread using Runnable interface
        //way1
        PrintHelloWorldRunnable printHelloWorldRunnable1 = new PrintHelloWorldRunnable();
        Thread thread2 = new Thread(printHelloWorldRunnable1);
        thread2.start();

        //way2
        Runnable printHelloWorldRunnable2 = new PrintHelloWorldRunnable();
        Thread thread3 = new Thread(printHelloWorldRunnable2);
        thread3.start();

        PrintNumbersThread printNumbersThread1 = new PrintNumbersThread();
        Thread thread4 = new Thread(printNumbersThread1);
        thread4.start();

        PrintNumbersThread printNumbersThread2 = new PrintNumbersThread();
        printNumbersThread2.start();

        PrintNumbersRunnable printNumbersRunnable1 = new PrintNumbersRunnable();
        Thread thread5 = new Thread(printNumbersRunnable1);
        thread5.start();

    }

}
