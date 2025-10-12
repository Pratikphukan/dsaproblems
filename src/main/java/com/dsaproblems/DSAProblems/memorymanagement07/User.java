package com.dsaproblems.DSAProblems.memorymanagement07;

import com.dsaproblems.DSAProblems.memorymanagement.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Cloneable {

	private String name;

	private Address address;

	private SensitiveObject sensitiveObject;

	public User(String name, Address address, SensitiveObject sensitiveObject) {
		this.name = name;
		this.address = address;
		this.sensitiveObject = sensitiveObject;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		User userClone = null;
		userClone = (User) super.clone();
		userClone.setSensitiveObject((SensitiveObject) sensitiveObject.clone());
		return userClone;
	}
}
