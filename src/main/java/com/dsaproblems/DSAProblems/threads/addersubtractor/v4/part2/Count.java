package com.dsaproblems.DSAProblems.threads.addersubtractor.v4.part2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Count {

	private Integer value = 0;

	Count(Integer value) {
		this.value = value;
	}

	public synchronized void incrementValue(Integer offset) {
		this.value += offset;
	}

}
