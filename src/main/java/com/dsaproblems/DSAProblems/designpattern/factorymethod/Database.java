package com.dsaproblems.DSAProblems.designpattern.factorymethod;

public interface Database {

	/*
	 * the purpose of createQuery is to return a new object of corresponding query
	 * as a result of which this method is called a factory method
	 */
	Query createQuery(User user);

}
