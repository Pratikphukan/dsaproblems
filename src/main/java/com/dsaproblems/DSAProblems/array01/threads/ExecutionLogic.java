package com.dsaproblems.DSAProblems.array01.threads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Getter
@Setter
@Slf4j
public class ExecutionLogic implements Runnable {

	private List<Integer> input;

	private int count;

	private int prev;

	public ExecutionLogic(List<Integer> input) {
		super();
		this.input = input;
		this.prev = input.get(0);
		this.count = 1;
	}

	@Override
	public void run() {
		log.info("Thread name at start :: {}", Thread.currentThread().getName());
		for (int i = 1; i < input.size(); i++) {
			if (prev % 2 != input.get(i) % 2) {
				count++;
				prev = input.get(i);
			}
		}
		log.info("Answer is :: {}", count);
		log.info("Thread name at end :: {}", Thread.currentThread().getName());
	}

}
