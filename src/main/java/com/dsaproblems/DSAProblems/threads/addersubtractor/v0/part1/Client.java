package com.dsaproblems.DSAProblems.threads.addersubtractor.v0.part1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.dsaproblems.DSAProblems.threads.addersubtractor.Count;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {

	public static void main(String[] args) throws InterruptedException {
		Count count = new Count(20); // shared piece of data, the final value will not be consistent

		Adder adder = new Adder(count);

		Subtractor subtractor = new Subtractor(count);

		ExecutorService executor = Executors.newCachedThreadPool();

		executor.execute(adder);
		executor.execute(subtractor);

		executor.shutdown();
		log.info("Current count value :: {}", count.getValue());

		executor.awaitTermination(3000, TimeUnit.MILLISECONDS); // blocking call, waiting for all the threads to finish
																// execution
		log.info("Current count value :: {}", count.getValue());
	}

}
