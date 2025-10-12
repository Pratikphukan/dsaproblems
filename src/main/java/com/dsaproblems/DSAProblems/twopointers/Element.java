package com.dsaproblems.DSAProblems.twopointers;

public class Element {

	private Integer value;

	private boolean isfromFirstArray;

	public Element(Integer value, boolean isfromFirstArray) {
		super();
		this.value = value;
		this.isfromFirstArray = isfromFirstArray;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public boolean isIsfromFirstArray() {
		return isfromFirstArray;
	}

	public void setIsfromFirstArray(boolean isfromFirstArray) {
		this.isfromFirstArray = isfromFirstArray;
	}
}
