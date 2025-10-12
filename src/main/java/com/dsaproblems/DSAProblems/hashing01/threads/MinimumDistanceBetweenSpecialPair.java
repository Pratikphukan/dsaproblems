package com.dsaproblems.DSAProblems.hashing01.threads;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class MinimumDistanceBetweenSpecialPair implements Runnable {

	private List<Integer> input;

	@Override
	public void run() {
		log.info("Thread name :: {}", Thread.currentThread().getName());
		if (input.size() == 1) {
			log.info("Current task done and ans is :: {}", -1);
		}
		int minLength = input.size();
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < input.size(); i++) { // don't use minLength as the original length, as it is getting updated
			int key = input.get(i);
			if (map.containsKey(key)) {
				minLength = Math.min(minLength, i - map.get(key));
			}
			map.put(key, i);
		}
		log.info("Current task done and ans is :: {}", (minLength == input.size()) ? -1 : minLength);
	}

}
