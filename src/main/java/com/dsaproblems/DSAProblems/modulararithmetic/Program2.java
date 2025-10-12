package com.dsaproblems.DSAProblems.modulararithmetic;

import java.util.ArrayList;
import java.util.List;

public class Program2 {

	public static void main(String[] args) {
		List<Integer> a = List.of(24, 16, 8, 7, 17, 23, 35, 20, 26, 28, 34, 21);
		ArrayList<Integer> A = new ArrayList<>(a);
		// (13,14,22,3,32,19,16), m=4
		// (17,2,5,4,6,23,13,26,14,18,15,30,35), m=10
		// ans->0,3|0,5|1,2|4,6
		// (31,24,16,8,7,17,23,35,20,26,28,34,21), m=6
		//
		int m = 6;
		int count = 0;
		for (int i = 0; i < A.size(); i++) {
			for (int j = i + 1; j < A.size(); j++) {
				if ((A.get(i) + A.get(j)) % m == 0) {
					count++;
				}
			}
		}
		System.out.println(count);
//		for(int i = 0; i < A.size(); i++) {
//			A.set(i, A.get(i)%m);
//		}
//		System.out.println(A);
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for (int i = 0; i < m; i++) {
			ans.add(0);
		}
		System.out.println(ans);
		System.out.println(A);
		for (int i = 0; i < A.size(); i++) {
			int j = A.get(i) % m;
			ans.set(j, ans.get(j) + 1);
		}
		System.out.println(ans);
		int answer = 0;
		answer += (ans.get(0) * (ans.get(0) - 1)) / 2;
		int i = 1, j = A.size() - 1;
		while (i < j) {
			answer += ans.get(i) * ans.get(j);
			i++;
			j--;
		}
		if (m % 2 == 0) {
			answer += (ans.get(m / 2) * (ans.get(m / 2) - 1)) / 2;
		}
		System.out.println(answer);
	}

}
