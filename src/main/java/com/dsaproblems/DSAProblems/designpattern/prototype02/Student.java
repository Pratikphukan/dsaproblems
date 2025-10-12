package com.dsaproblems.DSAProblems.designpattern.prototype02;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	private Integer age;
	private String name;
	private String batch;
	private Double averageBatchPsp;
	private Double studentPsp;

	public Student(Student st) {
		this.age = st.getAge();
		this.name = st.getName();
		this.batch = st.getBatch();
		this.averageBatchPsp = st.getAverageBatchPsp();
		this.studentPsp = st.getStudentPsp();
	}

	public Student copy() {
		return new Student(this);
	}

}
