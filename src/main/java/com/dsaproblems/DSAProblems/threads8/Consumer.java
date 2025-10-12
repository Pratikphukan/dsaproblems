package com.dsaproblems.DSAProblems.threads8;

import java.util.Queue;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class Consumer implements Runnable {

	private Queue<Review> store;

	private String name;

	@Override
	public void run() {
		while (true) {
			synchronized (store) {
				if (this.store.size() > 0) {
					store.remove();
					log.info("Consumer name :: {}, Shirt count :: {}", this.name, this.store.size());
				}
			}
		}
	}

}
