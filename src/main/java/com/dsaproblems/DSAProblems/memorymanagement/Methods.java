package com.dsaproblems.DSAProblems.memorymanagement;

public class Methods {

	public void method1(int x) {
		System.out.println(x);
		method2(x); // pass by value
		System.out.println(x);
	}

	public void method2(int x) {
		System.out.println(x);
		x++;
		System.out.println(x);
	}

}
