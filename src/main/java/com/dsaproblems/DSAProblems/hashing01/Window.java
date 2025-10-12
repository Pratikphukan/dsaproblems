package com.dsaproblems.DSAProblems.hashing01;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;

@Getter
public class Window {

	private Integer size;

	private List<Integer> itemsInWindow;

	private Set<Integer> uniqueItemsInWindow;

	public Window(Integer size) {
		this.size = size;
		this.itemsInWindow = new ArrayList<>();
		uniqueItemsInWindow = new HashSet<>();
	}

	public void addItemToWindow(Integer item) {
		this.itemsInWindow.add(item);
	}

}
