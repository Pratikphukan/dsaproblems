package com.dsaproblems.DSAProblems.queue;

public interface QueueService {

	void enqueue(Integer val);

	Integer dequeue();

	Integer peek();
}
