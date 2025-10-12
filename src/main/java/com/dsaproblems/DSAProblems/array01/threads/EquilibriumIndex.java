package com.dsaproblems.DSAProblems.array01.threads;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class EquilibriumIndex extends Thread {

	private List<Integer> input;

	@Override
	public void run() {
		log.info("Thread name :: {}", Thread.currentThread().getName());
		log.info("Answer is :: {}", getEqulibriumIndex());
	}

	public int getEqulibriumIndex() {
		int len = input.size();
		if (len == 1) {
			return 0;
		}
		List<Integer> prefix = new ArrayList<>();
		prefix.add(input.get(0));
		for (int i = 1; i < len; i++) {
			prefix.add(prefix.get(i - 1) + input.get(i));
		}
		int left = 0;
		int right = 0;
		for (int i = 0; i < input.size(); i++) {
			left = i == 0 ? 0 : prefix.get(i - 1);
			right = i == len - 1 ? 0 : prefix.get(len - 1) - prefix.get(i);
			if (left == right) {
				return i;
			}
		}
		return -1;
	}

}
