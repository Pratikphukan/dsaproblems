package com.dsaproblems.DSAProblems.ll.threads02;

public class DataRaceDemo {

	public static void main(String[] args) throws InterruptedException {

		Thread barron = new Shopper0();
		Thread olivia = new Shopper0();

		barron.start();
		olivia.start();
		barron.join(); // wait till barron completes
		olivia.join(); // wait till olivia completes

		System.out.println("We should buy " + Shopper0.garlicCount + " garlic.");
	}

}
