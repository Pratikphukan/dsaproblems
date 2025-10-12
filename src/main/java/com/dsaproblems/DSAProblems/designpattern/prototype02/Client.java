package com.dsaproblems.DSAProblems.designpattern.prototype02;

public class Client {

	public static void main(String[] args) {
		Student st = new Student(22, "Pratik", "April_2022", 81.29, 93.56);
		System.out.println(st.toString());
		Student stcopy = st.copy();
		System.out.println(stcopy.toString());
		System.out.println(st == stcopy);

		IntelligentStudent ist = new IntelligentStudent(65);
		System.out.println(ist.toString());
		IntelligentStudent istcopy = ist.copy();
		System.out.println(istcopy.toString());
		System.out.println(ist == istcopy);
	}

}
