package com.dsaproblems.DSAProblems.array01;

public class Element implements Comparable<Element> {

	private Integer value;

	private Integer index;

	public Element(Integer value, Integer index) {
		super();
		this.value = value;
		this.index = index;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	@Override
	public int compareTo(Element element) {
		return this.value - element.value;
	}

	@Override
	public String toString() {
		return "{value: " + value + ", index: " + index + "}";
	}

}
