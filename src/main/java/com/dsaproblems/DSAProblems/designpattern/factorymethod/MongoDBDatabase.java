package com.dsaproblems.DSAProblems.designpattern.factorymethod;

public class MongoDBDatabase implements Database {

	@Override
	public Query createQuery(User user) {
		return new NoSQLQuery();
	}

}
