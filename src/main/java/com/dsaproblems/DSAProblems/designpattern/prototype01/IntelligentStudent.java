package com.dsaproblems.DSAProblems.designpattern.prototype01;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntelligentStudent extends Student {

	private Integer iq;

	IntelligentStudent(Student student, Integer iq) {
		super(student);
		this.iq = iq;
	}

	@Override
	public IntelligentStudent copy() {
		return new IntelligentStudent(this, iq);
	}

}
