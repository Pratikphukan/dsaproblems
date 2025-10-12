package com.dsaproblems.DSAProblems.hashing02;

import java.util.HashSet;
import java.util.Set;

public class StartAndEndInA {

	public static void main(String[] args) {
		String input = "aab";
		System.out.println(findNoOfSubstringsv1(input));
		System.out.println(findNoOfSubstringsv2(input));
	}

	private static int findNoOfSubstringsv2(String input) {
		int len = input.length();
		int count_A = 0;
		for (int i = 0; i < len; i += 1) {
			if (input.charAt(i) == 'a')
				count_A += 1;
		}
		return (count_A * (count_A + 1)) / 2;
	}

	private static int findNoOfSubstringsv1(String input) {
		int len = input.length();
		Set<Integer> indices = new HashSet<>();
		for (int i = 0; i < len; i++) {
			if (input.charAt(i) == 'a') {
				indices.add(i);
			}
		}
		int occurrences = indices.size();
		return (occurrences * (occurrences + 1)) / 2;
	}

}
