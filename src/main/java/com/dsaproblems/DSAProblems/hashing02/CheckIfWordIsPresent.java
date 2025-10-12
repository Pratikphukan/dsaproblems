package com.dsaproblems.DSAProblems.hashing02;

import java.util.Objects;

public class CheckIfWordIsPresent {

	public static void main(String[] args) {
		String A = "abcbagdda";
		String search = "bagd";

		System.out.println(checkIfWordIsPresent(A, search));
	}

	// TC can touch O(n*n) in the worst case
	private static boolean checkIfWordIsPresent(String input, String search) {
		int B = search.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			sb.append(input.charAt(i));
			if ((i - B + 1) >= 0) {
				if (Objects.equals(sb.toString(), search)) {
					return true;
				} else {
					sb.deleteCharAt(0);
				}
			}
		}
		return false;
	}

}
