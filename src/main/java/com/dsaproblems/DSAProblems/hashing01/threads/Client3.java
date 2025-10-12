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
public class Client3 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		log.info("Thread name :: {}", Thread.currentThread().getName());
		List<List<Integer>> inputs = new ArrayList<>();
		inputs.add(new ArrayList<>(Arrays.asList(3, 6, 8, 10, 15, 50)));
		inputs.add(new ArrayList<>(Arrays.asList(3, 6, 8, 10, 15, 50)));
		inputs.add(new ArrayList<>(Arrays.asList(3, 6, 8, 10, 15, 50)));
		inputs.add(new ArrayList<>(Arrays.asList(3, 6, 8, 10, 15, 50)));
		inputs.add(new ArrayList<>(Arrays.asList(3, 6, 8, 10, 15, 50)));
		inputs.add(new ArrayList<>(Arrays.asList(3, 6, 8, 10, 15, 50)));
		inputs.add(new ArrayList<>(Arrays.asList(3, 6, 8, 10, 15, 50)));

		ExecutorService executor = Executors.newCachedThreadPool();

		CountPairsWithGivenXOR countPairsWithGivenXOR = null;
		Future<Integer> ansFuture = null;
		List<Integer> ans = new ArrayList<>();
		for (List<Integer> input : inputs) {
			countPairsWithGivenXOR = new CountPairsWithGivenXOR(input, 5);
			ansFuture = executor.submit(countPairsWithGivenXOR);
			ans.add(ansFuture.get());
		}

		System.out.println(ans);
	}

}
