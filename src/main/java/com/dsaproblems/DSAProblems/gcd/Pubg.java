package com.dsaproblems.DSAProblems.gcd;

import java.util.ArrayList;

public class Pubg {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int solve(ArrayList<Integer> A) {
		int ans = A.get(0);
		for (int i = 1; i < A.size(); i++) {
			ans = gcd(ans, A.get(i));
		}
		return ans;
	}

	public Integer gcd(Integer a, Integer b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

}
