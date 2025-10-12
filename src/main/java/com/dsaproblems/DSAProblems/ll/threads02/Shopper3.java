package com.dsaproblems.DSAProblems.ll.threads02;

/*
 * Now an important thing to note in this example is that we marked the addGarlic method 
 * as static so that it's associated with the Shopper class and not a specific instance 
 * of a Shopper. By doing so, when either thread invokes the synchronized addGarlic method, 
 * it will acquire the intrinsic lock that's associated with the class object. If I remove 
 * the static keyword on line 20, then each of the two shopper threads will invoke their 
 * own instance of the addGarlic method which is associated with their own object's intrinsic lock. If I run the program now, it does not work correctly. The threads are no longer using the same intrinsic lock so I end up with a data race.
 */
public class Shopper3 extends Thread {

	static int garlicCount = 0;

	public void run() {
		for (int i = 0; i < 10_000_000; i++)
			addGarlic();
	}

	private static synchronized void addGarlic() {
		garlicCount++;
	}
}
