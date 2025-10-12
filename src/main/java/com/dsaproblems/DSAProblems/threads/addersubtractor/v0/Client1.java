package com.dsaproblems.DSAProblems.threads.addersubtractor.v0;

import com.dsaproblems.DSAProblems.threads.addersubtractor.Count;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client1 {

    public static void main(String[] args) throws InterruptedException {
        log.info("Thread name :: {}", Thread.currentThread().getName());
        Count count = new Count(20);

        Thread adder = new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                count.setValue(count.getValue() + i);
                log.info("Adder thread name :: {}, count value :: {}", Thread.currentThread().getName(), count.getValue());
            }
        });
        adder.start();

        Thread subtractor = new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                count.setValue(count.getValue() - i);
                log.info("Subtractor thread name :: {}, count value :: {}", Thread.currentThread().getName(), count.getValue());
            }
        });
        subtractor.start();

        adder.join();
        subtractor.join();

        log.info("Value of count :: {}", count.getValue());
    }

}
