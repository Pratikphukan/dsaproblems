package com.dsaproblems.DSAProblems.memorymanagement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	private String name;

	private Address address;

	private SensitiveObject sensitiveObject;

	public User(String name, Address address, SensitiveObject sensitiveObject) {
		this.name = name;
		this.address = address;
		this.sensitiveObject = sensitiveObject;
	}
}
