package com.dsaproblems.DSAProblems.ll.threads03;

import java.util.concurrent.locks.ReentrantLock;

public class Shopper01 extends Thread {

	static int garlicCount, potatoCount = 0;
	static ReentrantLock pencil = new ReentrantLock();

	public void run() {
		for (int i = 0; i < 10_000; i++) {
			addGarlic();
			addPotato();
		}
	}

	private void addPotato() {
		pencil.lock();
		potatoCount++;
		addGarlic();
		pencil.unlock();
	}

	private void addGarlic() {
		pencil.lock();
		System.out.println("Hold count: " + pencil.getHoldCount());
		garlicCount++;
		pencil.unlock();
	}
}
