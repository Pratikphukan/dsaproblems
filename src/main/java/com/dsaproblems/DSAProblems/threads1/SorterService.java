package com.dsaproblems.DSAProblems.threads1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SorterService {

	public List<Integer> sortArray(List<Integer> left, List<Integer> right)
			throws InterruptedException, ExecutionException {

		List<Integer> ans = new ArrayList<>();
		ExecutorService executorService = Executors.newCachedThreadPool();
		Sorter leftSorter = new Sorter(left, executorService);
		Sorter rightSorter = new Sorter(right, executorService);
		Future<List<Integer>> leftSortedFuture = executorService.submit(leftSorter);
		Future<List<Integer>> rightSortedFuture = executorService.submit(rightSorter);
		List<Integer> leftSorted = leftSortedFuture.get();
		List<Integer> rightSorted = rightSortedFuture.get();
		int i = 0;
		int j = 0;
		while (i < leftSorted.size() && j < rightSorted.size()) {
			if (leftSorted.get(i) <= rightSorted.get(j)) {
				ans.add(leftSorted.get(i));
				++i;
			} else {
				ans.add(rightSorted.get(j));
				++j;
			}
		}
		while (i < leftSorted.size()) {
			ans.add(leftSorted.get(i));
			i++;
		}
		while (j < rightSorted.size()) {
			ans.add(rightSorted.get(j));
			j++;
		}
		return ans;
	}

}
