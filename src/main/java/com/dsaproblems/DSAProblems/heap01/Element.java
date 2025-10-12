package com.dsaproblems.DSAProblems.heap01;

public class Element implements Comparable<Element> {

	private int originalValue;

	private int modifiedValue;

	public Element(int originalValue, int modifiedValue) {
		super();
		this.originalValue = originalValue;
		this.modifiedValue = modifiedValue;
	}

	public int getOriginalValue() {
		return originalValue;
	}

	public void setOriginalValue(int originalValue) {
		this.originalValue = originalValue;
	}

	public int getModifiedValue() {
		return modifiedValue;
	}

	public void setModifiedValue(int modifiedValue) {
		this.modifiedValue = modifiedValue;
	}

	@Override
	public int compareTo(Element element) {
		if (this.modifiedValue == element.modifiedValue) {
			return this.originalValue - element.originalValue;
		}
		return this.modifiedValue - element.modifiedValue;
	}
}
