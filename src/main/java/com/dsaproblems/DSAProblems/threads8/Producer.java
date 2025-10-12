package com.dsaproblems.DSAProblems.threads8;

import java.util.Queue;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class Producer implements Runnable {

	private static final Integer sizeOfStore = 20;

	private Queue<Review> store;

	private String name;

	@Override
	public void run() {
		while (true) {
			synchronized (store) {
				if (store.size() < sizeOfStore) {
					store.add(new Review());
					log.info("Producer name :: {}, Shirt count :: {}", this.name, this.store.size());
				}
			}
		}
	}

}
