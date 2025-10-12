package com.dsaproblems.DSAProblems.threads.addersubtractor.v3.part1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.dsaproblems.DSAProblems.threads.addersubtractor.Count;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {

	public static void main(String[] args) {
		Count count = new Count(10);

		Lock lock = new ReentrantLock(); // lock is a blocking call

		Adder adder = new Adder(count, lock);

		Subtractor subtractor = new Subtractor(count, lock);

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
