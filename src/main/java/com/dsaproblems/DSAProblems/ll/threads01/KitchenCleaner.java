package com.dsaproblems.DSAProblems.ll.threads01;

public class KitchenCleaner extends Thread {

	public void run() {
		while (true) {
			System.out.println("Olivia cleaned the kitchen.");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
