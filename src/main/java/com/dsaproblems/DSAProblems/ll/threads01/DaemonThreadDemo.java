package com.dsaproblems.DSAProblems.ll.threads01;

public class DaemonThreadDemo {

	public static void main(String[] args) throws InterruptedException {
		Thread olivia = new KitchenCleaner();
		olivia.setDaemon(true);
		olivia.start();

		System.out.println("Barron is cooking...");
		Thread.sleep(600);
		System.out.println("Barron is cooking...");
		Thread.sleep(600);
		System.out.println("Barron is cooking...");
		Thread.sleep(600);
		System.out.println("Barron is done!");
	}

}
