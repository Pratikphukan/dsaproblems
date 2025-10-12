package com.dsaproblems.DSAProblems.designpattern.factorymethod;

public class UserService {

	/*
	 * interface or abstract class depending on if it has attributes in future we
	 * can change the database easily
	 */
	private Database db;

	private Query query;

	public UserService(Database db) {
		this.db = db;
	}

	public void createUser(User user) {
		query = db.createQuery(user);
		query.execute();
	}

	public void registerUser(User user) {
		query = db.createQuery(user);
		query.execute();
	}

}
