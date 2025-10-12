package com.dsaproblems.DSAProblems.memorymanagement01;

import java.util.Collections;
import java.util.List;

public class ImmutableCollectionHolder {

	private final List<String> items; // no provision for creating setter

	public ImmutableCollectionHolder(List<String> items) {
		this.items = Collections.unmodifiableList(items);
	}

	public List<String> getItems() {
		return items;
	}
}
