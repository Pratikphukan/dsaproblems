package com.dsaproblems.DSAProblems.threads.addersubtractor.v0.part1;

import com.dsaproblems.DSAProblems.threads.addersubtractor.Count;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * inside the for loop we have the critical section as it trying to modify the
 * shared piece of data or trying the read a value which is a dirty read race
 * condition-> more than one thread trying to enter the critical section at the
 * same time pre-emption->CPU can pause a thread even before it completes assume
 * in a single core, one thread can run at one time, there will be no problem of
 * synchronisation
 */
@AllArgsConstructor
@Slf4j
public class Adder implements Runnable {

	private Count count;

	@Override
	public void run() {
		for (int i = 1; i <= 1000; i++) {
			// log.info("Adder thread name :: {}", Thread.currentThread().getName());
			int currentValue = count.getValue();
			try {
				Thread.sleep(50);
			} catch (InterruptedException ex) {
				log.error("Something wrong happened :: {}", ex);
			}
			int nextValue = currentValue + 1;
			count.setValue(nextValue);
			// log.info("In adder thread, count value :: {}", count.getValue());
		}
	}

}
