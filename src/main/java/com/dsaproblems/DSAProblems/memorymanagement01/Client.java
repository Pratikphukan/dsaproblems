package com.dsaproblems.DSAProblems.memorymanagement01;

import com.dsaproblems.DSAProblems.memorymanagement.Address;
import com.dsaproblems.DSAProblems.memorymanagement.Person;

public class Client {

	public static void main(String[] args) {

		// in stack memory only
		int x = 5;
		double y = 3;
		boolean b = false;
		final int xy = 3;

		// in stack and heap
		Person p = new Person();
		Address a = new Address();

		// in heap memory
		a.setCity("Chennai"); // invoking a method will add to the stack memory
		a.setCountry("India");
		a.setNumber("8638335821");
		a.setStreeName("1st Main Road");
		a.setZipCode("600089");

		p.setLovesJava(true);
		p.setYearOfBirth("1997");
		p.setName("Pratik");

		// connect two objects in the heap memory
		p.setAddress(a);

		System.out.println(p.getYearOfBirth());
		p.setYearOfBirth("1998");
		System.out.println(p.getYearOfBirth());

		System.out.println(p.getAddress().getCity());
		System.out.println(a.getCity());

		a.setCity("Guwahati");
		System.out.println(p.getAddress().getCity());
		System.out.println(a.getCity());

		p.getAddress().setCountry("USA");
		System.out.println(p.getAddress().getCountry());
		System.out.println(a.getCountry());

		// StackOverflowExample stackOverflowExample = new StackOverflowExample();
		// OutOfMemoryExample outOfMemoryExample = new OutOfMemoryExample();

		Address a1 = new Address();
		Address a2 = a1;
		System.out.println(a1 == a2);
		System.out.println(a1.equals(a2));

		Address a3 = new Address();
		System.out.println(a3.getClass());
		a3.setCity(a.getCity()); // invoking a method will add to the stack memory
		a3.setCountry(a.getCountry());
		a3.setNumber(a.getNumber());
		a3.setStreeName(a.getStreeName());
		a3.setZipCode(a.getZipCode());
		System.out.println(a.equals(a3));

		Person p1 = new Person();
		p1.setAddress(p.getAddress());
		p1.setLovesJava(p.isLovesJava());
		p1.setYearOfBirth(p.getYearOfBirth());
		p1.setName(p.getName());
		System.out.println(p1.equals(p));
	}

}
