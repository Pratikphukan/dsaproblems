package com.dsaproblems.DSAProblems.hashing01.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Client {

	public static void main(String[] args) {

		List<Integer> A = new ArrayList<>(Arrays.asList(1, 2, 2, 1));

		List<Integer> B = new ArrayList<>(Arrays.asList(2, 3, 1, 2, 2));

		Executor executor = Executors.newCachedThreadPool();

		List<Integer> ans = new ArrayList<Integer>();

		CommonElements commonElements = null;
		Thread thread = null;
		for (int i = 0; i < A.size(); i++) {
			for (int j = 0; j < B.size(); j++) {
				commonElements = new CommonElements(A.get(i), B.get(i), ans, B, j);
				thread = new Thread(commonElements);
				thread.start();
			}
		}

		System.out.println(ans);
		System.out.println(ans);
		System.out.println(ans);
	}

}
