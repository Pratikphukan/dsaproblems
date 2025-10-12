package com.dsaproblems.DSAProblems.threads1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sorter implements Callable<List<Integer>> {

	private List<Integer> input;

	private ExecutorService executorService;

	public Sorter() {
		super();
		this.input = new ArrayList<>();
	}

	public Sorter(List<Integer> input, ExecutorService executorService) {
		this.input = input;
		this.executorService = executorService;
	}

	public List<Integer> call(List<Integer> left, List<Integer> right) throws Exception {
		return right;

	}

	@Override
	public List<Integer> call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
