package com.dsaproblems.DSAProblems.designpattern.factorymethod;

public class NoSQLQuery implements Query {

	@Override
	public void execute() {
		System.out.println("NoSQL query executed");
	}

}
