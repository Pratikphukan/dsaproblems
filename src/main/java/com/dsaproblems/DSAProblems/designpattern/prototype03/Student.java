package com.dsaproblems.DSAProblems.designpattern.prototype03;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Prototype<Student> {

	private Integer age;
	private String name;
	private String batch;
	private Double averageBatchPsp;
	private Double studentPsp;

	public Student(Student student) {
		this.age = student.getAge();
		this.name = student.getName();
		this.batch = student.getBatch();
		this.averageBatchPsp = student.getAverageBatchPsp();
		this.studentPsp = student.getStudentPsp();
	}

	@Override
	public Student copy() {
		return new Student(this);
	}

}
