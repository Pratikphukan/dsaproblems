package com.dsaproblems.DSAProblems.ll.threads02;

public class Shopper4 extends Thread {

	static int garlicCount = 0;

	public void run() {
		for (int i = 0; i < 10_000_000; i++) {
			synchronized (Shopper4.class) {
				garlicCount++;
			}
		}
	}
}
