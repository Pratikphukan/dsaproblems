package com.dsaproblems.DSAProblems.primenumbers;

import java.util.ArrayList;
import java.util.HashSet;

public class LuckyNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(luckyNumbers(12));
	}

	private static ArrayList<Integer> smallestPrimeFactors(int n) {
//		isPrime[0] = isPrime[1] = false;
//        for (int i = 2; i <= n; i++)
//            isPrime[i] = true;
//     
//        for (int p = 2; p * p <= n; p++){
//            if (isPrime[p] == true){
//                for (int i = p * p; i <= n; i += p)
//                    isPrime[i] = false;
//            }
//        }
		ArrayList<Integer> spf = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			spf.add(i);
		}
		for (int i = 2; i * i <= n; i++) {
			if (spf.get(i) == i) { // this means i is prime
				for (int j = i * i; j <= n; j = j + i) {
					if (spf.get(j) == j) {
						spf.set(j, i);
					}
				}
			}

		}
		System.out.println(spf);
		return spf;
	}

	private static int luckyNumbers(int n) {
		ArrayList<Integer> spf = smallestPrimeFactors(n);
		int count = 0;
		for (int i = 6; i <= n; i++) {
			HashSet<Integer> set = new HashSet<>();
			int num = i;
			while (num > 1) {
				int x = spf.get(num);
				set.add(x);
				num = num / x;
			}
			if (set.size() == 2) {
				count++;
			}
		}
		return count;
	}
}
