package com.dsaproblems.DSAProblems.hashing01.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Sorter implements Callable<List<Integer>> {

	private List<Integer> input;

	@Override
	public List<Integer> call() throws Exception {
		int len = input.size();
		if (len <= 1)
			return input;
		int mid = len / 2;
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		int i;
		for (i = 0; i < mid; i++) {
			left.add(input.get(i));
		}
		for (i = mid; i < len; i++) {
			right.add(input.get(i));
		}
		Sorter leftSorter = new Sorter(left);
		Sorter rightSorter = new Sorter(right);
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<List<Integer>> leftSortedFuture = executorService.submit(leftSorter);
		Future<List<Integer>> rightSortedFuture = executorService.submit(rightSorter);
		List<Integer> leftSorted = leftSortedFuture.get();
		List<Integer> rightSorted = rightSortedFuture.get();
		return null;
	}

}
