package com.dsaproblems.DSAProblems.threads;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintNumbersThread extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            log.info("Number :: {}", i + " " + Thread.currentThread().getName());
        }
    }
}
