package com.dsaproblems.DSAProblems.hashing02;

import java.util.HashMap;
import java.util.Map;

public class CheckIfReorderingIsPossible {

	public static void main(String[] args) {
		String A = "bbaabb";
		Integer noOfStrings = 2;

		System.out.println(checkIfPossibleToGetSimilarString(noOfStrings, A));
	}

	private static boolean checkIfPossibleToGetSimilarString(Integer noOfStrings, String input) {
		Map<Character, Integer> map = new HashMap<>();
		for (Character item : input.toCharArray()) {
			map.put(item, map.getOrDefault(item, 0) + 1);
		}
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			if (entry.getValue() % noOfStrings != 0)
				return false;
		}
		return true;
	}

}
