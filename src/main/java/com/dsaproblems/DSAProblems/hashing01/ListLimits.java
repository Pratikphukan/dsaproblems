package com.dsaproblems.DSAProblems.hashing01;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ListLimits {

	private Integer startIdx;

	private Integer endIdx;

	public ListLimits(Integer startIdx, Integer endIdx) {
		this.startIdx = startIdx;
		this.endIdx = endIdx;
	}

}
