package com.dsaproblems.DSAProblems.memorymanagement;

import java.util.Objects;

public class Person {

	private boolean lovesJava;
	private String yearOfBirth;
	private String name;
	private Address address;

	public boolean isLovesJava() {
		return lovesJava;
	}

	public void setLovesJava(boolean lovesJava) {
		this.lovesJava = lovesJava;
	}

	public String getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(String yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, lovesJava, name, yearOfBirth);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(address, other.address) && lovesJava == other.lovesJava
				&& Objects.equals(name, other.name) && Objects.equals(yearOfBirth, other.yearOfBirth);
	}

}
