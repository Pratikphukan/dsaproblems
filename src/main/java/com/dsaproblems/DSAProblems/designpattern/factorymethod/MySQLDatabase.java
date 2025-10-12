package com.dsaproblems.DSAProblems.designpattern.factorymethod;

public class MySQLDatabase implements Database {

	@Override
	public Query createQuery(User user) {
		return new SQLQuery();
	}

}
