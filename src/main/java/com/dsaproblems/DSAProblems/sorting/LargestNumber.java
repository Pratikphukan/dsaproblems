package com.dsaproblems.DSAProblems.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LargestNumber {

	public static void main(String[] args) {
		List<Integer> A = new ArrayList<Integer>(Arrays.asList(3, 30, 34, 5, 9));
		Collections.sort(A, new ElementComparator());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < A.size(); i++) {
			sb.append(A.get(i));
		}
		System.out.println(sb.toString());
	}

}
