package com.dsaproblems.DSAProblems.array01.threads;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
@Getter
@Setter
public class MajorityElementExecutionLogic implements Runnable {

	private int minOccurrences;
	private Map<Integer, Integer> elementToFrequency;
	private int ans; // atleast one element will be there
	private int currFrequency;
	private int item;

	public MajorityElementExecutionLogic(int minOccurrences, int ans, int currFrequency) {
		super();
		this.minOccurrences = minOccurrences;
		this.elementToFrequency = new HashMap<>();
		this.ans = ans;
		this.currFrequency = 0;
	}

	@Override
	public void run() {
		log.info("Thread name at start :: {}", Thread.currentThread().getName());
		if (elementToFrequency.containsKey(item)) {
			currFrequency = elementToFrequency.get(item) + 1;
			if (currFrequency > minOccurrences) {
				ans = item;
			}
			elementToFrequency.put(item, currFrequency);
		} else {
			elementToFrequency.put(item, 1);
		}
		log.info("Thread name at end :: {}", Thread.currentThread().getName());
	}

}
