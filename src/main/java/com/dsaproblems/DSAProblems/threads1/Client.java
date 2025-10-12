package com.dsaproblems.DSAProblems.threads1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Executor executor = Executors.newCachedThreadPool();

//		for (int i = 1; i <= 20; i++) {
//			PrintNumber printNumber = new PrintNumber(i);
//			executor.execute(printNumber);
//		}

//		PrintNumbers printNumbers1 = new PrintNumbers();
//		executor.execute(printNumbers1);
//
//		PrintNumbers printNumbers2 = new PrintNumbers();
//		executor.execute(printNumbers2);

		List<Integer> nums = new ArrayList<>(Arrays.asList(23, 21, 27, 19, 44, 33));
		int length = nums.size();
		if (length <= 1) {
			log.info("Final ans :: {}", nums);
		}
		int mid = length / 2;
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		for (int i = 0; i < mid; i++) {
			left.add(nums.get(i));
		}
		for (int i = mid; i < length; i++) {
			right.add(nums.get(i));
		}
		SorterService sorterService = new SorterService();
		List<Integer> ans = sorterService.sortArray(left, right);
		System.out.println(ans);
	}

}
