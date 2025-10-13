package com.dsaproblems.DSAProblems.advancedJava;

import lombok.Getter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    @Getter
    private int counter = 0;
    private Lock lock = new ReentrantLock();

    public void incrementCounter() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    example.incrementCounter();
                }
            }).start();
        }

        int finalValue = example.getCounter();
        System.out.println(finalValue);
    }
}
