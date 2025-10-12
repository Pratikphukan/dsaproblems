package com.dsaproblems.DSAProblems.hashing01.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client2 {

	public static void main(String[] args) {
		log.info("Thread name :: {}", Thread.currentThread().getName());
		List<List<Integer>> inputs = new ArrayList<>();
		inputs.add(new ArrayList<>(Arrays.asList(10, 5, 3, 4, 3, 5, 6)));
		inputs.add(new ArrayList<>(Arrays.asList(6, 10, 5, 4, 9, 120)));
		inputs.add(new ArrayList<>(Arrays.asList(8, 15, 1, 10, 5, 19, 19, 3, 5, 6, 6, 2, 8, 2, 12, 16, 3)));
		inputs.add(new ArrayList<>(Arrays.asList(1, 5, 3, 4, 1, 5, 6)));
		inputs.add(new ArrayList<>(Arrays.asList(9, 5, 3, 4, 3, 5, 6)));
		inputs.add(new ArrayList<>(Arrays.asList(31, 51, 31, 51, 31, 31, 31, 31, 31, 31, 51, 31, 31)));
		inputs.add(new ArrayList<>(Arrays.asList(1, 2, 1, 3, 4, 3)));

//		DistinctNumbersInWindow distinctNumbersInWindow = null;
//		Thread thread = null;
//		Random random = new Random();
//		for (List<Integer> input : inputs) {
//			distinctNumbersInWindow = new DistinctNumbersInWindow(input, random.nextInt(4) + 1);
//			thread = new Thread(distinctNumbersInWindow);
//			thread.start();
//		}

		Executor executor = Executors.newCachedThreadPool();

		DistinctNumbersInWindow distinctNumbersInWindow = null;
		Random random = new Random();
		for (List<Integer> input : inputs) {
			distinctNumbersInWindow = new DistinctNumbersInWindow(input, random.nextInt(4) + 1);
			executor.execute(distinctNumbersInWindow);
		}

	}

}
