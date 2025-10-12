package com.dsaproblems.DSAProblems.memorymanagement07;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
public class SensitiveObject implements Cloneable {

	private int secretCode;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();

	}

}
