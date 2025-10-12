package com.dsaproblems.DSAProblems.ll.threads02;

public class Client {

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new AvoidRacing();

		Thread thread2 = new AvoidRacing();

		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();

		System.out.println("We should buy " + AvoidRacing.count0 + " garlic.");
		System.out.println("We should buy " + AvoidRacing.count1 + " garlic.");
		System.out.println("We should buy " + AvoidRacing.count2 + " garlic.");
		System.out.println("We should buy " + AvoidRacing.count3 + " garlic.");
		System.out.println("We should buy " + AvoidRacing.count4 + " garlic.");
	}

}
