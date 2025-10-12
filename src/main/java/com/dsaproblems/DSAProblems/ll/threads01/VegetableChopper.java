package com.dsaproblems.DSAProblems.ll.threads01;

public class VegetableChopper implements Runnable {

	private String name;

	private int vegetableCount;

	static boolean chopping = true;

	public VegetableChopper(String name) {
		this.name = name;
		this.vegetableCount = 0;
	}

	@Override
	public void run() {
		while (chopping) {
			System.out.println(this.getName() + " chopped a vegetable!");
			vegetableCount++;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVegetableCount() {
		return vegetableCount;
	}
}
