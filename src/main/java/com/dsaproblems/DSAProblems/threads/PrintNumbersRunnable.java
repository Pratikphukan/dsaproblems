package com.dsaproblems.DSAProblems.threads;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintNumbersRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            log.info("Number :: {}", i + " " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().isInterrupted());
                throw new RuntimeException(e);
            }
        }
    }

}
