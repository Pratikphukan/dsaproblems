package com.dsaproblems.DSAProblems.memorymanagement01;

import java.util.ArrayList;
import java.util.List;

public class MemoryLeakGenerator {

	private List<Object> cache = new ArrayList<>();

	public void processData(Object data) {
		cache.add(data);

		// Process data (dummy implementation)
		System.out.println("Processing: " + data);

		// Done with Object data now, no need to keep it

		// Code to fix the memory leak could go here
		cache.clear();
	}

	public int getCacheSize() {
		return cache.size();
	}

}
