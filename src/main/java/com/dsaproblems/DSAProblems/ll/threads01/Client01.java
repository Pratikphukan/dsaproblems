package com.dsaproblems.DSAProblems.ll.threads01;

public class Client01 {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Thread name " + Thread.currentThread().getName());
		VegetableChopper01 barron = new VegetableChopper01("Barron");
		VegetableChopper01 olivia = new VegetableChopper01("Olivia");

		barron.start();// Barron start chopping
		olivia.start();// Olivia start chopping

		Runtime rt = Runtime.getRuntime();
		long usedKB = (rt.totalMemory() - rt.freeMemory()) / 1024;
		System.out.format("\nProcess ID: %d\n", ProcessHandle.current().pid());
		System.out.format("\nThread Count: %d\n", Thread.activeCount());
		System.out.format("\nMemory Usage: %d KB\n", usedKB);

		Thread.sleep(1000); // continue chopping for 1 second
		VegetableChopper01.chopping = false; // stop chopping

		barron.join();
		olivia.join();

		System.out.format("\nBarron chopped %d vegetables.\n", barron.getVegetableCount());
		System.out.format("\nOlivia chopped %d vegetables.\n", olivia.getVegetableCount());
	}

}
