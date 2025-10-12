package com.dsaproblems.DSAProblems.memorymanagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Client1 {

	public static void main(String[] args) {
		User user = new User("Pratik", new Address(), new SensitiveObject());

		List<String> products = new ArrayList<>();
		products.add("product1");

		Order o = createOrder(user, products);

		System.out.println("-----original-----");
		processProducts(o.getProducts());

		List<String> copy = o.getProducts();
		System.out.println("-----copy-----");
		processProducts(copy);

		copy.add("product2");

		System.out.println("-----original-----");
		processProducts(o.getProducts());
		System.out.println("-----copy-----");
		processProducts(copy);

	}

	private static void processProducts(List<String> products) {
		for (String product : products) {
			System.out.println(product);
		}
	}

	private static Order createOrder(User user, List<String> products) {
		return new Order(LocalDate.now(), products, user);
	}

}
