package com.dsaproblems.DSAProblems.queue;

public class PriorityQueueRunner {

	public static void main(String[] args) {
		PriorityQueue pq = new PriorityQueue(20);
		pq.insertDesc(12);
		pq.insertDesc(7);
		pq.insertDesc(10);
		pq.insertDesc(1);
		pq.insertDesc(8);
		pq.printPriorityQueue();

		pq.insertionSort();
		pq.printPriorityQueue();

		System.out.println(pq.remove());
		pq.printPriorityQueue();

		System.out.println(pq.remove());
		pq.printPriorityQueue();
	}
}
