package com.dsaproblems.DSAProblems.greedy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StartFinishPairI implements Comparable<StartFinishPairI> {

	private Integer startTime;

	private Integer endTime;

	public StartFinishPairI(Integer startTime, Integer endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public int compareTo(StartFinishPairI startFinishPair) { // only one chance to implement the compareTo()
		return this.endTime - startFinishPair.endTime;
	}

}
