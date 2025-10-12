package com.dsaproblems.DSAProblems.memorymanagement05;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dsaproblems.DSAProblems.memorymanagement.Address;
import com.dsaproblems.DSAProblems.memorymanagement.SensitiveObject;
import com.dsaproblems.DSAProblems.memorymanagement.User;
import com.dsaproblems.DSAProblems.memorymanagement04.Product;

public class Client {

	public static Order createOrder(User user, List<Product> products) {
		return new Order(LocalDate.now(), products, user);
	}

	public static void processProducts(List<Product> products) {
		for (Product product : products) {
			System.out.println(product);
		}
	}

	public static void main(String[] args) {
		User user = new User("Pratik", new Address(), new SensitiveObject());

		List<Product> products = new ArrayList<>();
		products.add(new Product("Java", "Learn Java", 89.76));

		// create an order
		Order order = createOrder(user, products);

		System.out.println("----original----");
		processProducts(order.getProducts());

		List<Product> productsCopy = order.getProducts();
		System.out.println("----copy----");
		processProducts(productsCopy);

		System.out.println("----modifying----");
		// products.get(0).setName("Javascript"); // shallow clone
		productsCopy.add(new Product("Python", "Learn Python", 23.45));

		System.out.println("----copy----");
		processProducts(productsCopy);

		System.out.println("----original----");
		processProducts(order.getProducts());
	}

}
