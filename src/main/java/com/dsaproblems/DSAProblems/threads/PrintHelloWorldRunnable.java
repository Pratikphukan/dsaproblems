package com.dsaproblems.DSAProblems.threads;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintHelloWorldRunnable implements Runnable {

	@Override
	public void run() {
		log.info("Thread name :: {}", Thread.currentThread().getName());
		log.info("Hello World!!!");
	}

}
