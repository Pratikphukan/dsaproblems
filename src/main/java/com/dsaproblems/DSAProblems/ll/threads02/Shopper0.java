package com.dsaproblems.DSAProblems.ll.threads02;

public class Shopper0 extends Thread implements Runnable {

	static int garlicCount = 0; // shared counter variable

	@Override
	public void run() {
		for (int i = 0; i < 10_000_000; i++)
			garlicCount++;
	}

}
