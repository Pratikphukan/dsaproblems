package com.dsaproblems.DSAProblems.memorymanagement01;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dsaproblems.DSAProblems.memorymanagement.Address;
import com.dsaproblems.DSAProblems.memorymanagement.SensitiveObject;
import com.dsaproblems.DSAProblems.memorymanagement.User;

public class DemoEscapingProducts {

	public static Order createOrder(User user, List<String> products) {
		return new Order(LocalDate.now(), products, user);
	}

	public static void processProducts(List<String> products) {
		for (String product : products) {
			System.out.println(product);
		}
	}

	public static void main(String[] args) {
		User user = new User("Pratik", new Address(), new SensitiveObject());

		List<String> products = new ArrayList<>();
		products.add("product");

		Order order = createOrder(user, products);

		System.out.println("----original----");
		processProducts(order.getProducts());

		List<String> productsCopy = order.getProducts();
		System.out.println("----copy----");
		processProducts(productsCopy);

		System.out.println("----modifying----");
		productsCopy.add("new product");
		processProducts(productsCopy);

		System.out.println("----original----");
		processProducts(order.getProducts());
	}

}
