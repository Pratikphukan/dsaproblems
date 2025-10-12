package com.dsaproblems.DSAProblems.ll.threads01;

public class ExecutionSchedulingDemo {

	public static void main(String[] args) throws InterruptedException {
		VegetableChopper barron = new VegetableChopper("Barron");
		VegetableChopper olivia = new VegetableChopper("Olivia");

		Thread barronThread = new Thread(barron);
		barronThread.start();

		Thread oliviaThread = new Thread(olivia);
		oliviaThread.start();

		Thread.sleep(30); // continue chopping for 1 sec
		VegetableChopper.chopping = false;

		barronThread.join();// wait for this thread to die

		oliviaThread.join();// wait for this thread to die

		System.out.format("\nBarron chopped %d vegetables.\n", barron.getVegetableCount());
		System.out.format("\nOlivia chopped %d vegetables.\n", olivia.getVegetableCount());
	}

}
