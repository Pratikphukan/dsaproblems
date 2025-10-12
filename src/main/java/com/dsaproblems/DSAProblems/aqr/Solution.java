package com.dsaproblems.DSAProblems.aqr;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		int[] a = { 1, 3, 5 };
		int d = 7;
		System.out.println(hackerCards(a, d));
		for (int item : hackerCards(a, d)) {
			System.out.println(item);
		}
	}

	static int[] hackerCards(int[] collection, int d) {
		List<Integer> result = new ArrayList<>();
		int s, e;
		for (int idx = 0; idx <= collection.length; idx++) {
			if (idx == 0)
				s = 1;
			else
				s = collection[idx - 1] + 1;
			if (idx != collection.length)
				e = collection[idx];
			else
				e = Integer.MAX_VALUE;
			if (d < s)
				break;
			for (int k = s; k < e; k++) {
				if (k <= d) {
					result.add(k);
					d -= k;
				} else {
					break;
				}
			}
		}

		return result.stream().mapToInt(i -> i).toArray();
	}

}
