package com.dsaproblems.DSAProblems.ll.threads01;

public class ChefOlivia extends Thread {

	public void run() {
		System.out.println("Olivia started & waiting for sausage to thaw...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Olivia is done cutting sausage.");
	}

}
