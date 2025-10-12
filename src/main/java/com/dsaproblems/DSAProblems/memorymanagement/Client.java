package com.dsaproblems.DSAProblems.memorymanagement;

public class Client {

	public static void main(String[] args) {
		Methods methods = new Methods();
		int x = 1;
		methods.method1(x);

		Person person = new Person();
		Address address = new Address();

		address.setCity("Chennai");
		address.setCountry("India");
		address.setNumber("42");
		address.setStreeName("Intech Systems Road");
		address.setZipCode("600032");

		person.setLovesJava(true);
		person.setYearOfBirth("1997");
		person.setName("Pratik");
		person.setAddress(address); // pass by reference

		System.out.println(person.getAddress().getCity());
		address.setCity("Madurai");
		System.out.println(person.getAddress().getCity());

		Person p1 = new Person();
		Person p2 = new Person();
		System.out.println(p1 == p2);// compare object references
	}

}
