package com.dsaproblems.DSAProblems.gcd;

public class LargestCoprimeDivisor {

	public static void main(String[] args) {
		// (2,3),(30,12)
		int A = 2;
		int B = 3;
		System.out.println(findLargestCoprimeDivisor(A, B));
	}

	private static int findLargestCoprimeDivisor(int A, int B) {
		int ans = 1;
		for (int x = 2; x <= Math.sqrt(A); x++) {
			if (A % x == 0) {
				if (A == x * x && GCD(x, B) == 1) {
					ans = Math.max(ans, x);
				}
				if (A != x * x) {
					if (GCD(x, B) == 1) {
						ans = Math.max(ans, x);
					}
					if (GCD(A / x, B) == 1) {
						ans = Math.max(ans, A / x);
					}
				}
			}
		}
		return ans;
	}

	private static int GCD(int a, int b) {
		if (b == 0) {
			return a;
		}
		return GCD(b, a % b);
	}

}
