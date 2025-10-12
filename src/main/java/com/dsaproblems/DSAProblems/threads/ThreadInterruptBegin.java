package com.dsaproblems.DSAProblems.threads;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadInterruptBegin {

    public static void main(String[] args) {
        Thread printNumbersRunnable = new Thread(new PrintNumbersRunnable());
        printNumbersRunnable.start();
        try {
            log.info("Thread name :: {}", Thread.currentThread().getName());
            Thread.sleep(3000); //main thread waits for 3 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        printNumbersRunnable.interrupt();// main thread will not wait till the printNumbersRunnable thread completes its execution
        System.out.println(printNumbersRunnable.isInterrupted());
    }
}
