package com.dsaproblems.DSAProblems.hashing01.threads;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class CountPairsWithGivenXOR implements Callable<Integer> {

	private List<Integer> input;

	private int value;

	@Override
	public Integer call() throws Exception {
		log.info("Thread name :: {}", Thread.currentThread().getName());
		Set<Integer> set = new HashSet<>();
		int count = 0;
		for (int num : input) {
			count += set.contains(value ^ num) ? 1 : 0;
			set.add(num);
		}
		log.info("Current task done and ans is :: {}", count);
		return count;
	}

}
