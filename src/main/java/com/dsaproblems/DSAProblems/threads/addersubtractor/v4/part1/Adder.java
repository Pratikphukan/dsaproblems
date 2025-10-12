package com.dsaproblems.DSAProblems.threads.addersubtractor.v4.part1;

import com.dsaproblems.DSAProblems.threads.addersubtractor.Count;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class Adder implements Runnable {

	private Count count;

	Adder(Count count) {
		this.count = count;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
			synchronized (count) {
				log.info("Adder has got the lock for :: {}", i);
				int currentValue = count.getValue();
				int nextValue = currentValue + 1;
				count.setValue(nextValue);
				log.info("Adder has released the lock for :: {}", i);
				try {
					Thread.sleep(10);
				} catch (Exception ex) {
					log.error("Something wrong happened :: {}", ex);
				}
			}
		}
	}

}
