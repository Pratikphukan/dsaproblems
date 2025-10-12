package com.dsaproblems.DSAProblems.threads.addersubtractor.v1;

import com.dsaproblems.DSAProblems.threads.addersubtractor.Count;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Client {

    public static void main(String[] args) throws InterruptedException {
        log.info("Thread name :: {}", Thread.currentThread().getName());

        Count count = new Count(20);

        Thread adder = new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                count.setValue(count.getValue() + i);
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("Adder thread name :: {}, count value :: {}", Thread.currentThread().getName(), count.getValue());
            }
        });

        Thread subtractor = new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                count.setValue(count.getValue() - i);
                log.info("Subtractor thread name :: {}, count value :: {}", Thread.currentThread().getName(), count.getValue());
            }
        });

        ExecutorService executor = Executors.newCachedThreadPool();

        executor.execute(adder);
        executor.execute(subtractor);

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        log.info("Value of count :: {}", count.getValue());
    }
}
