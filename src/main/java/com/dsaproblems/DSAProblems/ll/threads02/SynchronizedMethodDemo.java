package com.dsaproblems.DSAProblems.ll.threads02;

public class SynchronizedMethodDemo {

	public static void main(String[] args) throws InterruptedException {
//		Thread barron = new Shopper3();
//		Thread olivia = new Shopper3();

		Thread barron = new Shopper4();
		Thread olivia = new Shopper4();

		long startTime = System.currentTimeMillis();

		barron.start();
		olivia.start();

		barron.join();
		olivia.join();

		// System.out.println("We should buy " + Shopper3.garlicCount + " garlic.");
		System.out.println("We should buy " + Shopper4.garlicCount + " garlic.");
		System.out.println(System.currentTimeMillis() - startTime);
	}

}
