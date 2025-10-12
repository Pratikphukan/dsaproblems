package com.dsaproblems.DSAProblems.primenumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DistinctPrimes {

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(10, 21, 33, 47));
		System.out.println(getDistinctPrimesv1(input));
		// System.out.println(getDistinctPrimesv2(input));
	}

	private static char[] getDistinctPrimesv2(List<Integer> input) {
		Set<Integer> uniqueElements = new HashSet<>();
		for (int num : input) {
			for (int i = 2; i * i <= num; i++) {
				if (num % i == 0) {
					uniqueElements.add(i);
					while (num % i == 0) {

					}
				}
			}
		}
		return null;
	}

	private static int getDistinctPrimesv1(List<Integer> a) {
		HashSet<Integer> m = new HashSet<Integer>();

		// iterate over every element of array
		for (int i = 0; i < a.size(); i++) {
			int sq = (int) Math.sqrt(a.get(i));

			// from 2 to square root of number
			// run a loop and check the numbers
			// which are factors.
			for (int j = 2; j <= sq; j++) {
				if (a.get(i) % j == 0) {

					// if j is a factor store
					// it in the set
					m.add(j);

					// divide the number with j
					// till it is divisible so
					// that only prime factors
					// are stored
					while (a.get(i) % j == 0) {
						a.set(i, a.get(i) / j);
					}
				}
			}

			// if the number is still greater
			// than 1 then it is a prime factor,
			// insert in set
			if (a.get(i) > 1) {
				m.add(a.get(i));
			}
		}

		// the number of unique prime
		// factors will the size of the set
		return m.size();
	}

}
