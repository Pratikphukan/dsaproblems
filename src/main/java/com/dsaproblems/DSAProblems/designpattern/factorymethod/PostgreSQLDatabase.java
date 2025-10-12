package com.dsaproblems.DSAProblems.designpattern.factorymethod;

public class PostgreSQLDatabase implements Database {

	@Override
	public Query createQuery(User user) {
		return new SQLQuery();
	}

}
