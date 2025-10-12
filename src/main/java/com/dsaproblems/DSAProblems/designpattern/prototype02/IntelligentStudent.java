package com.dsaproblems.DSAProblems.designpattern.prototype02;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IntelligentStudent extends Student {

	private Integer iq;

	public IntelligentStudent(IntelligentStudent intelligentStudent) {
		super(intelligentStudent);
		this.iq = intelligentStudent.getIq();
	}

	@Override
	public IntelligentStudent copy() {
		return new IntelligentStudent(this);
	}
}
