package com.dsaproblems.DSAProblems.array01.threads;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class AddOneToNumber implements Runnable {

	private List<Integer> A;

	@Override
	public void run() {
		log.info("Thread name :: {}", Thread.currentThread().getName());
		log.info("Answer is :: {}", plusOne());
	}

	public List<Integer> plusOne() {
		Long number = 0l;
		for (Integer digit : A) {
			number = number * 10 + digit;
		}
		number += 1;
		ArrayList<Integer> ans = new ArrayList<>();
		while (number > 0) {
			ans.add(0, (int) (number % 10));
			number /= 10;
		}
		return ans;
	}

}
