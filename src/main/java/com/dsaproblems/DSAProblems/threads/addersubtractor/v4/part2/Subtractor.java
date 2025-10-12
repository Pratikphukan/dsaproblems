package com.dsaproblems.DSAProblems.threads.addersubtractor.v4.part2;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class Subtractor implements Runnable {

	private Count count;

	Subtractor(Count count) {
		this.count = count;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
			log.info("Subtractor has got the lock for :: {}", i);
			count.incrementValue(-1);
			log.info("Subtractor has released the lock for :: {}", i);
			try {
				Thread.sleep(10);
			} catch (Exception ex) {
				log.error("Something wrong happened :: {}", ex);
			}
		}
	}

}
