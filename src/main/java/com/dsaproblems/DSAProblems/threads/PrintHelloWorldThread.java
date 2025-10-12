package com.dsaproblems.DSAProblems.threads;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintHelloWorldThread extends Thread {

	@Override
	public void run() {
		super.run();
		log.info("Thread name :: {}", Thread.currentThread().getName());
		log.info("Hello World!!!");
	}

}
