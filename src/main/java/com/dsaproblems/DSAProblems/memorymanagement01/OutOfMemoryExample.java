package com.dsaproblems.DSAProblems.memorymanagement01;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OutOfMemoryExample {

	private Map<Integer, String> map;

	public OutOfMemoryExample() {
		this.map = new HashMap<>();
		Random random = new Random();
		while (true) {
			map.put(random.nextInt(), "soRandom");
		}
	}

}
