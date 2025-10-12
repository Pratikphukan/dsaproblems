package com.dsaproblems.DSAProblems.ll.threads01;

public class VegetableChopper02 implements Runnable {

	private String name;

	private int vegetableCount = 0;

	private static boolean chopping = true;

	@Override
	public void run() {
		while (chopping) {
			System.out.println(this.name + " chopped a vegetable!");
			vegetableCount++;
		}
	}

}
