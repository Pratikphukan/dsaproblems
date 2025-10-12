package com.dsaproblems.DSAProblems.hashing01.threads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class DistinctNumbersInWindow implements Runnable {

	private List<Integer> input;

	private int B;

	@Override
	public void run() {
		log.info("Thread name :: {}", Thread.currentThread().getName());
		ArrayList<Integer> countList = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		Integer item = null;
		Integer excludedElement = null;
		for (int i = 0; i < input.size(); i++) {
			item = input.get(i);
			map.put(item, map.getOrDefault(item, 0) + 1);
			if ((i - B + 1) >= 0) {
				countList.add(map.size());
				excludedElement = input.get(i - B + 1);
				map.put(excludedElement, map.get(excludedElement) - 1);
				if (map.get(excludedElement) == 0) {
					map.remove(excludedElement);
				}
			}
		}
		log.info("Current task done and ans is :: {}", countList);
	}

}
