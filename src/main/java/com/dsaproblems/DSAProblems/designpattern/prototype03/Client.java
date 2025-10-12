package com.dsaproblems.DSAProblems.designpattern.prototype03;

public class Client {

	public static void main(String[] args) {
		StudentRegistry studentRegistry = new StudentRegistry();
		fillRegistry(studentRegistry);

		Student siddharth = studentRegistry.get("april21Batch").copy();
		siddharth.setName("Siddharth");
		siddharth.setAge(24);
		siddharth.setStudentPsp(91.0);

		IntelligentStudent aprBatchIntelligentStudent = new IntelligentStudent();
		aprBatchIntelligentStudent.setIq(180);
		aprBatchIntelligentStudent.setBatch("April 21");
		aprBatchIntelligentStudent.setAverageBatchPsp(89.0);

		studentRegistry.register("aprBatchIntelligentStudent", aprBatchIntelligentStudent);

	}

	private static void fillRegistry(StudentRegistry studentRegistry) {
		Student aprBatchStudent = new Student();
		aprBatchStudent.setBatch("April 21");
		aprBatchStudent.setAverageBatchPsp(89.0);

		studentRegistry.register("april21Batch", aprBatchStudent);
	}

}
