package com.dsaproblems.DSAProblems.math;

public class NthMagicNumber {

	public static void main(String[] args) {
		int A = 10;
		System.out.println(findNthMagicNumberv1(A));
		System.out.println(findNthMagicNumberv2(A));
	}

	private static int findNthMagicNumberv2(int input) {
		int ans = 0;
		int msb = (int) (Math.log(input) / Math.log(2)) + 1;
		for (int i = 0; i < msb; i++) {
			if (((input >> i) & 1) == 1) {
				ans += Math.pow(5, i + 1);
			}
		}
		return ans;
	}

	private static int findNthMagicNumberv1(int input) {
		int ans = 0;
		int msb = (int) (Math.log(input) / Math.log(2)) + 1;
		for (int i = 0; i < msb; i++) {
			if ((input & 1) == 1) {
				ans += Math.pow(5, i + 1);
			}
			input = input >> 1;
		}
		return ans;
	}

}
