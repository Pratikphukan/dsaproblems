package com.dsaproblems.DSAProblems.greedy;

import java.util.Comparator;

public class EndTimeCompare implements Comparator<StartFinishPairII> {

	@Override
	public int compare(StartFinishPairII obj1, StartFinishPairII obj2) {
		return obj1.getEndTime() - obj2.getEndTime();
	}

}
