package com.dsaproblems.DSAProblems.designpattern.prototype01;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

	private Integer age;
	private String name;
	private String batch;
	private Double averageBatchPsp;
	private Double studentPsp;

	Student() {
		super();
	}

	Student(int age, String name, String batch, double averageBatchPsp, double studentPsp) {
		this();
		this.age = age;
		this.name = name;
		this.batch = batch;
		this.averageBatchPsp = averageBatchPsp;
		this.studentPsp = studentPsp;
	}

	Student(Student student) {
		this(student.getAge(), student.getName(), student.getBatch(), student.getAverageBatchPsp(),
				student.getStudentPsp());
	}

	public Student copy() {
		return new Student(this);
	}

}
