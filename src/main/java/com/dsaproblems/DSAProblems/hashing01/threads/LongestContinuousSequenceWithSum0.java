package com.dsaproblems.DSAProblems.hashing01.threads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class LongestContinuousSequenceWithSum0 implements Callable<List<Integer>> {

	private List<Integer> A;

	@Override
	public List<Integer> call() throws Exception {
		log.info("Thread name :: {}", Thread.currentThread().getName());
		List<Integer> prefix = new ArrayList<>();
		int sum = 0;
		for (Integer item : A) {
			sum += item;
			prefix.add(sum);
		}
		int currentMaxNoOfElements = 0;
		int s = 0;
		int e = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < prefix.size(); i++) {
			int key = prefix.get(i);
			if (key == 0) {
				if (currentMaxNoOfElements < (i + 1)) {
					currentMaxNoOfElements = i + 1;
					s = -1;
					e = i;
				}
			} else {
				if (!map.containsKey(key)) {
					map.put(key, i);
				} else {
					int firstIdx = map.get(key);
					if (currentMaxNoOfElements < (i - firstIdx)) {
						currentMaxNoOfElements = i - firstIdx;
						s = firstIdx;
						e = i;
					}
				}
			}
		}
		List<Integer> sol = new ArrayList<>();
		for (int i = s + 1; i <= e; i++) {
			sol.add(A.get(i));
		}
		log.info("Current task done and ans is :: {}", sol);
		return sol;
	}

}
