package com.dsaproblems.DSAProblems.ll.threads02;

public class AtomicOperationDemo {

	public static void main(String[] args) throws InterruptedException {

		Thread barron = new Shopper2();
		Thread olivia = new Shopper2();

		barron.start();
		olivia.start();
		barron.join();
		olivia.join();

		System.out.println("We should buy " + Shopper2.garlicCount + " garlic.");
	}

}
