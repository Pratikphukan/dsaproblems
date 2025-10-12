package com.dsaproblems.DSAProblems.gcd;

public class EnumeratingGCD {

	public static void main(String[] args) {

	}

	public static String findConsecutiveGCD(String A, String B) {
		// The logic behind it is GCD of consecutive numbers is always 1, so no need to
		// take gcd of the range, but if numbers are equal return number
		if (A.equals(B))
			return A;
		else
			return "1";

	}

}
