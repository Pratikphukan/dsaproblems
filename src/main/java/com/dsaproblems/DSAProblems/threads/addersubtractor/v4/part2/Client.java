package com.dsaproblems.DSAProblems.threads.addersubtractor.v4.part2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {

	public static void main(String[] args) {
		Count count = new Count(30);

		Adder adder = new Adder(count);

		Subtractor subtractor = new Subtractor(count);

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
