package com.dsaproblems.DSAProblems.ll.threads02;

public class MutualExclusionDemo {

	public static void main(String[] args) throws InterruptedException {

		Thread barron = new Shopper1();
		Thread olivia = new Shopper1();

		long startTime = System.currentTimeMillis();

		barron.start();
		olivia.start();

		barron.join();
		olivia.join();

		System.out.println("We should buy " + Shopper1.garlicCount + " garlic.");
		System.out.println(System.currentTimeMillis() - startTime);
	}

}
