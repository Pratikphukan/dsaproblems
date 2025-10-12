package com.dsaproblems.DSAProblems.designpattern.factorymethod;

public class SQLQuery implements Query {

	@Override
	public void execute() {
		System.out.println("SQL query is executed");
	}

}
