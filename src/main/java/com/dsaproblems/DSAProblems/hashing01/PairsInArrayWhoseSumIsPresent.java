package com.dsaproblems.DSAProblems.hashing01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PairsInArrayWhoseSumIsPresent {

	public static void main(String[] args) {
		/*
		 * elements in the array are distinct and positive 
		 */
		List<Integer> input = new ArrayList<>(Arrays.asList(3, 4, 2, 6, 7));
		System.out.println(findPairsInArrayWhoseSumIsPresent(input));
	}

	private static int findPairsInArrayWhoseSumIsPresent(List<Integer> input) {
		Set<Integer> uniqueElements = new HashSet<>();
		for (Integer item : input) {
			uniqueElements.add(item);
		}
		int count = 0;
		int len = input.size();
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if (uniqueElements.contains(input.get(i) + input.get(j))) {
					count += 1;
				}
			}
		}
		return count;
	}

}
