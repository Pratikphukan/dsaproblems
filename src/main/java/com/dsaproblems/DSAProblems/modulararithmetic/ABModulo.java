package com.dsaproblems.DSAProblems.modulararithmetic;

public class ABModulo {

	public static void main(String[] args) {
		System.out.println(greatestPositiveNumber(5, 10));
		System.out.println(greatestPositiveNumber1(100, 1));
	}

	private static int greatestPositiveNumber1(int A, int B) {
		return Math.abs(A-B);
	}

	private static int greatestPositiveNumber(int A, int B) {
		if(A>B) {
			return A-B;
		}
		return B-A;
	}

}
