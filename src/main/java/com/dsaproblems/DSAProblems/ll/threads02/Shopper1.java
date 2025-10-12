package com.dsaproblems.DSAProblems.ll.threads02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Shopper1 extends Thread {

	static int garlicCount = 0;

	static Lock pencil = new ReentrantLock();

	// way 1
	public void run() {
		pencil.lock();
		for (int i = 0; i < 10000; i++) {
			garlicCount++;
			System.out.println(Thread.currentThread().getName() + " is thinking");
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException ex) {
//				ex.printStackTrace();
//			}
		}
		pencil.unlock();
	}

	// way 2
//	public void run() {
//		for (int i = 0; i < 10000; i++) {
//			pencil.lock();
//			garlicCount++;
//			pencil.unlock();
//			System.out.println(Thread.currentThread().getName() + " is thinking");
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException ex) {
//				ex.printStackTrace();
//			}
//		}
//	}
}
