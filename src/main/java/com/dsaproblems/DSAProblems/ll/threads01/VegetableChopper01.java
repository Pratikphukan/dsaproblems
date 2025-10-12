package com.dsaproblems.DSAProblems.ll.threads01;

import lombok.Getter;

@Getter
public class VegetableChopper01 extends Thread {

	private int vegetableCount = 0;

	static boolean chopping = true;

	public VegetableChopper01(String name) {
		this.setName(name);
	}

	public void run() {
		// System.out.println(this.getName() + " chopping a vegetable!!");
		while (chopping) {
			// System.out.println(this.getName() + " chopping a vegetable!!");
			System.out.printf(this.getName() + " chopping a vegetable!!");
			vegetableCount++;
		}
	}
}
