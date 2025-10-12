package com.dsaproblems.DSAProblems.primenumbers;

public class SumOfMinNoPrimes {

	public static void main(String[] args) {
		System.out.println(getMinCountPrimeSum(100));
	}

	private static int getMinCountPrimeSum(int N) {
		int minCount;
		if (isPrime(N)) {
			minCount = 1;
		} else if (N % 2 == 0) {
			minCount = 2;
		} else {
			if (isPrime(N - 2)) {
				minCount = 2;
			} else {
				minCount = 3;
			}
		}
		return minCount;
	}

	private static boolean isPrime(int n) {
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

}
