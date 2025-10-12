package com.dsaproblems.DSAProblems.array01.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client1 {

	public static void main(String[] args) {
		log.info("Thread name :: {}", Thread.currentThread().getName());

//		List<List<Integer>> inputs = new ArrayList<>();
//
//		inputs.add(new ArrayList<>(Arrays.asList(2, 2, 2, 2, 2, 2)));
//		inputs.add(new ArrayList<>(Arrays.asList(1, 2, 2, 5, 6)));
//		inputs.add(new ArrayList<>(Arrays.asList(-7, 1, 5, 2, -4, 3, 0)));
//
//		ExecutionLogic executionLogic = null;
//		Thread thread = null;
//		for (List<Integer> input : inputs) {
//			executionLogic = new ExecutionLogic(input);
//			thread = new Thread(executionLogic);
//			thread.start();
//		}

		List<Integer> A = new ArrayList<>(Arrays.asList(2, 1, 2));
		int minOccurrences = A.size() / 2;
		// int ans = A.get(0); // atleast one element will be there
		int ans = -9;
		int currFrequency = 0;
		MajorityElementExecutionLogic majorityElementExecutionLogic = new MajorityElementExecutionLogic(minOccurrences,
				ans, currFrequency);
		Thread thread = null;
		for (Integer item : A) {
			majorityElementExecutionLogic.setItem(item);
			thread = new Thread(majorityElementExecutionLogic);
			thread.start();
		}
		System.out.println(majorityElementExecutionLogic.getAns());
	}

}
