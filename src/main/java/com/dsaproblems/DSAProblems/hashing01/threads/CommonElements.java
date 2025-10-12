package com.dsaproblems.DSAProblems.hashing01.threads;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class CommonElements implements Runnable {

	private int item1;

	private int item2;

	private List<Integer> ans;

	private List<Integer> B;

	private int bidx;

	public CommonElements(int item1, int item2, List<Integer> ans, List<Integer> B, int bidx) {
		this.item1 = item1;
		this.item2 = item2;
		this.ans = new ArrayList<>();
		this.B = B;
		this.bidx = bidx;
	}

	@Override
	public void run() {
		log.info("Thread name at start :: {}", Thread.currentThread().getName());
		if (item1 == item2) {
			ans.add(item1);
			B.set(bidx, null);
		}
		log.info("Thread name at end :: {}", Thread.currentThread().getName());
	}

}
