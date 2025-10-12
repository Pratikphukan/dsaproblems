package com.dsaproblems.DSAProblems.primenumbers;

import java.util.ArrayList;

public class Program2 {

	public static void main(String[] args) {
		System.out.println(smallestPrimeFactors(25));
		System.out.println(totalFactors(360));
	}

	private static int totalFactors(int n) {
		ArrayList<Integer> spf = smallestPrimeFactors(n);
		int total = 1;
		while (n > 1) {
			int p = spf.get(n);
			int c = 0;
			while (n % p == 0) {
				c += 1;
				n = n / p;
			}
			total *= (c + 1);
		}
		return total;
	}

	private static ArrayList<Integer> smallestPrimeFactors(int n) {
		ArrayList<Integer> spf = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			spf.add(i);
		}
		// System.out.println(spf);
		for (int i = 2; i * i <= n; i++) {
			if (spf.get(i) == i) { // this means i is prime
				for (int j = i * i; j <= n; j = j + i) {
					if (spf.get(j) == j) {
						spf.set(j, i);
					}
				}
			}

		}
		// System.out.println(spf);
		return spf;
	}

}
