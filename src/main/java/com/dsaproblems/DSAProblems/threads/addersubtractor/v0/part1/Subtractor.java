package com.dsaproblems.DSAProblems.threads.addersubtractor.v0.part1;

import com.dsaproblems.DSAProblems.threads.addersubtractor.Count;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class Subtractor implements Runnable {

	private Count count;

	@Override
	public void run() {
		for (int i = 1; i <= 1000; i++) {
			// log.info("Subtractor thread name :: {}", Thread.currentThread().getName());
			int currentValue = count.getValue();
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				log.error("Something wrong happened :: {}", ex);
			}
			int nextValue = currentValue - 1;
			count.setValue(nextValue);
			// log.info("In subtractor thread, count value :: {}", count.getValue());
		}
	}

}
