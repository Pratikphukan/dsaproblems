package com.dsaproblems.DSAProblems.ll.threads02;

import java.util.concurrent.atomic.AtomicInteger;

public class Shopper2 extends Thread {

	static AtomicInteger garlicCount = new AtomicInteger(0);

	public void run() {
		for (int i = 0; i < 10_000_000; i++) {
			garlicCount.incrementAndGet();
		}
	}
}
