package com.dsaproblems.DSAProblems.threads.addersubtractor.v3;

import com.dsaproblems.DSAProblems.threads.addersubtractor.Count;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Client {

    public static void main(String[] args) {

        Count count = new Count(10);
        Lock lock = new ReentrantLock(); // lock is a blocking call, same lock is used by both adder and subtractor

        Thread adder = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                lock.lock();
                log.info("Adder has got the lock for :: {}", i);
                count.setValue(count.getValue() + 1);
                log.info("Adder has released the lock for :: {}", i);
                lock.unlock();
                try {
                    Thread.sleep(10);
                } catch (Exception ex) {
                    log.error("Something wrong happened :: {}", ex);
                }
            }
        });

        Thread subtractor = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                lock.lock();
                log.info("Subtractor has got the lock for :: {}", i);
                count.setValue(count.getValue() - 1);
                log.info("Subtractor has released the lock for :: {}", i);
                lock.unlock();
                try {
                    Thread.sleep(10);
                } catch (Exception ex) {
                    log.error("Something wrong happened :: {}", ex);
                }
            }
        });


        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(adder);
        executorService.execute(subtractor);

        executorService.shutdown();

        try {
            executorService.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            log.error("Something wrong happened :: {}", ex);
        }

        log.info("Current value of count :: {}", count.getValue());
    }

}
