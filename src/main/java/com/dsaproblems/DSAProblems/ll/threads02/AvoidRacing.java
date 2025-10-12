package com.dsaproblems.DSAProblems.ll.threads02;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AvoidRacing extends Thread {

	static int count0 = 0;

	static int count1 = 0;
	static Lock lock = new ReentrantLock();

	static AtomicInteger count2 = new AtomicInteger(0);

	static int count3 = 0;

	static Integer count4 = 0;

	public void run() {
		for (int i = 0; i < 10_000_000; i++) {
			count0++;
		}

		for (int i = 0; i < 10_000_000; i++) {
			lock.lock();
			count1++;
			lock.unlock();
		}

		for (int i = 0; i < 10_000_000; i++) {
			count2.incrementAndGet();
		}

		for (int i = 0; i < 10_000_000; i++) {
			increaseCount();
		}

		for (int i = 0; i < 10_000_000; i++) {
			synchronized (AvoidRacing.class) {
				count4++;
			}
		}

//		for (int i = 0; i < 10_000_000; i++) {
//			synchronized (count4) {
//				count4++;
//			}
//		}

//		for (int i = 0; i < 10_000_000; i++) {
//			synchronized (this) {
//				count4++;
//			}
//		}

	}

	/*
	 * When a thread invokes a synchronized method, it automatically acquires the
	 * intrinsic lock for that method's object. That will prevent other threads that
	 * invoke a synchronized method on the same object from interleaving execution.
	 * When a thread tries to invoke a synchronized method while another thread is
	 * executing a synchronized method for that same object, the second thread will
	 * suspend execution until the first thread is done and exits.
	 */
	/*
	 * Now an important thing to note in this example is that I marked the
	 * increaseCount() method as static so that it's associated with the AvoidRacing
	 * class and not a specific instance of a AvoidRacing. By doing so, when either
	 * thread invokes the synchronized addGarlic method, it will acquire the
	 * intrinsic lock that's associated with the class object. If I remove the
	 * static keyword on line nine, then each of the two shopper threads will invoke
	 * their own instance of the increaseCount() method which is associated with
	 * their own object's intrinsic lock. If I run the program now, it does not work
	 * correctly. The threads are no longer using the same intrinsic lock so I end
	 * up with a data race.
	 */
	private static synchronized void increaseCount() {
		count3++;
	}

}
