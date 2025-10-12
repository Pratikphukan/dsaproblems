package com.dsaproblems.DSAProblems.heap02;

import java.util.Comparator;

public class SumCompare implements Comparator<PairSum> {

	@Override
	public int compare(PairSum pairSum1, PairSum pairSum2) {
		return pairSum2.getSum() - pairSum1.getSum();
	}

}
