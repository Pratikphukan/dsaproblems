package com.dsaproblems.DSAProblems.java8;

public class Item implements Foldable, Washable {

	private String name;

	public Item(String name) {
		this.name = name;
	}

	public Item() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void fold() {
		System.out.println("Folding " + this.name);
	}

	@Override
	public void wash() {
		System.out.println("Washing " + this.name);
	}

}
