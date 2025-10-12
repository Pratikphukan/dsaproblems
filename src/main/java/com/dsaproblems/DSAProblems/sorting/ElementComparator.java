package com.dsaproblems.DSAProblems.sorting;

import java.util.Comparator;

public class ElementComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer num1, Integer num2) {
		String a = Integer.toString(num1) + Integer.toString(num2);
		String b = Integer.toString(num2) + Integer.toString(num1);
		if (a.equals(b)) {
			return 0;
		}
		return b.compareTo(a);
	}

}
