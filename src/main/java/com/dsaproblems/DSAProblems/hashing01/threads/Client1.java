package com.dsaproblems.DSAProblems.hashing01.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client1 {

	public static void main(String[] args) throws InterruptedException {
		log.info("Thread name :: {}", Thread.currentThread().getName());
		List<List<Integer>> inputs = new ArrayList<>();
		inputs.add(new ArrayList<>(Arrays.asList(10, 5, 3, 4, 3, 5, 6)));
		inputs.add(new ArrayList<>(Arrays.asList(6, 10, 5, 4, 9, 120)));
		inputs.add(new ArrayList<>(Arrays.asList(8, 15, 1, 10, 5, 19, 19, 3, 5, 6, 6, 2, 8, 2, 12, 16, 3)));
		inputs.add(new ArrayList<>(Arrays.asList(1, 5, 3, 4, 1, 5, 6)));
		inputs.add(new ArrayList<>(Arrays.asList(9, 5, 3, 4, 3, 5, 6)));

		FirstRepeatingElement firstRepeatingElement = null;
		int i = 0;
		for (List<Integer> input : inputs) {
			// log.info("Task kicked off :: {}", ++i);
			firstRepeatingElement = new FirstRepeatingElement(input);
			firstRepeatingElement.start();
			firstRepeatingElement.join();
		}

		inputs = new ArrayList<>();
		inputs.add(new ArrayList<>(Arrays.asList(10, 5, 3, 4, 3, 5, 6)));
		inputs.add(new ArrayList<>(Arrays.asList(6, 10, 5, 4, 9, 120)));
		inputs.add(new ArrayList<>(Arrays.asList(8, 15, 1, 10, 5, 19, 19, 3, 5, 6, 6, 2, 8, 2, 12, 16, 3)));
		inputs.add(new ArrayList<>(Arrays.asList(1, 5, 3, 4, 1, 5, 6)));
		inputs.add(new ArrayList<>(Arrays.asList(7, 1, 3, 6, 7, 9, 4, 1, 7)));

		MinimumDistanceBetweenSpecialPair minimumDistanceBetweenSpecialPair = null;
		Thread thread = null;
		for (List<Integer> input : inputs) {
			minimumDistanceBetweenSpecialPair = new MinimumDistanceBetweenSpecialPair(input);
			thread = new Thread(minimumDistanceBetweenSpecialPair);
			thread.start();
		}
	}

}
