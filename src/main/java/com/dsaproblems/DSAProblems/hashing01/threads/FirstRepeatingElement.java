package com.dsaproblems.DSAProblems.hashing01.threads;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class FirstRepeatingElement extends Thread {

	private List<Integer> input;

	@Override
	public void run() {
		log.info("Thread name :: {}", Thread.currentThread().getName());
		Map<Integer, Integer> map = new HashMap<>();
		for (Integer num : input) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		Integer ans = null;
		for (Integer num : input) {
			if (map.get(num) > 1 && ans == null) {
				ans = num;
			}
		}
		ans = ans == null ? -1 : ans;
		log.info("Current task done and ans is :: {}", ans);
	}
}
