package com.dsaproblems.DSAProblems.memorymanagement01;

import java.util.ArrayList;
import java.util.List;

public class Answer {

	// Create a new ImmutableCollectionHolder with a list
	static ImmutableCollectionHolder createImmutableCollectionHolder(List<String> strings) {
		return new ImmutableCollectionHolder(strings);
	}

	static MemoryLeakGenerator createMemoryLeakGenerator() {
		return new MemoryLeakGenerator();
	}

	public static void main(String[] args) {
		List<String> items = new ArrayList<>();
		items.add("Pratik");
		items.add("Prajapati");
		ImmutableCollectionHolder immutableCollectionHolder = createImmutableCollectionHolder(items);
		// System.out.println(immutableCollectionHolder.getItems().add("Kishok"));
		System.out.println(immutableCollectionHolder.getItems());

		MemoryLeakGenerator memoryLeakGenerator = createMemoryLeakGenerator();
	}
}
