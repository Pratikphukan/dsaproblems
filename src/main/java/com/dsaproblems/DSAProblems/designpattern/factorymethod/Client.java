package com.dsaproblems.DSAProblems.designpattern.factorymethod;

public class Client {

	public static void main(String[] args) {
		Database db = new PostgreSQLDatabase();
		// Database db = new MongoDBDatabase();
		UserService userService = new UserService(db);
		User user = new User();
		userService.createUser(user);

	}

}
