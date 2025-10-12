package com.dsaproblems.DSAProblems.hashing01.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client4 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		log.info("Thread name :: {}", Thread.currentThread().getName());
		List<List<Integer>> inputs = new ArrayList<>();
		inputs.add(new ArrayList<>(Arrays.asList(3, 3, 4, -5, -2, 2, 1, -3, 3, -1, 5, -4, -1)));
		inputs.add(new ArrayList<>(Arrays.asList(1, 2, -3)));
		inputs.add(new ArrayList<>(Arrays.asList(2, 2, 1, -3, 4, 3, 1, -2, -3, 2)));
		inputs.add(new ArrayList<>(
				Arrays.asList(-9, -13, 6, -28, 27, -5, -27, 17, 15, -17, -25, 6, -8, 2, -13, -13, -23, 21)));
		inputs.add(new ArrayList<>(Arrays.asList(-4, 22, -9, -10, 0, -28, -11, 8, 8, 17)));
		inputs.add(new ArrayList<>(Arrays.asList(3, 3, 4, -5, -2, 2, 1, -3, 3, -1, 5, -4, -1)));

		ExecutorService executor = Executors.newCachedThreadPool();

		LongestContinuousSequenceWithSum0 longestContinuousSequenceWithSum0 = null;
		Future<List<Integer>> ansFuture = null;
		List<List<Integer>> ans = new ArrayList<>();
		for (List<Integer> input : inputs) {
			longestContinuousSequenceWithSum0 = new LongestContinuousSequenceWithSum0(input);
			ansFuture = executor.submit(longestContinuousSequenceWithSum0);
			ans.add(ansFuture.get());
		}

		System.out.println(ans);
	}

}
