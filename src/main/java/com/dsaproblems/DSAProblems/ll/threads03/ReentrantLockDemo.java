package com.dsaproblems.DSAProblems.ll.threads03;

public class ReentrantLockDemo {

	public static void main(String[] args) throws InterruptedException {
		Thread barron = new Shopper01();
		Thread olivia = new Shopper01();
		barron.start();
		olivia.start();
		barron.join();
		olivia.join();
		System.out.println("We should buy " + Shopper01.garlicCount + " garlic.");
		System.out.println("We should buy " + Shopper01.potatoCount + " potatoes.");
	}

}
