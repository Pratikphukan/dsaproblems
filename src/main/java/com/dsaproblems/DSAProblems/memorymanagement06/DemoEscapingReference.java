package com.dsaproblems.DSAProblems.memorymanagement06;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dsaproblems.DSAProblems.memorymanagement.Address;
import com.dsaproblems.DSAProblems.memorymanagement.SensitiveObject;
import com.dsaproblems.DSAProblems.memorymanagement.User;

public class DemoEscapingReference {

	public static void sendOrder(Order order) throws CloneNotSupportedException {
		System.out.println("Sending order!!");
		System.out.println("Date :: " + order.getOrderDate());
		System.out.println("User :: " + order.getUser().getName());
		System.out.println("Products :: ");
		for (Product product : order.getProducts()) {
			System.out.println("- " + product.getName());
		}
		order.getUser().setSensitiveObject(new SensitiveObject());
	}

	public static void main(String[] args) throws CloneNotSupportedException {

		SensitiveObject sensitiveObject = new SensitiveObject();
		sensitiveObject.setSecretCode(12345);

		User user = new User("Kishok", new Address(), sensitiveObject);
		List<Product> products = new ArrayList<>();
		products.add(new Product("Java", "Learn Java", 89.76));

		Order order = new Order(LocalDate.now(), products, user);
		sendOrder(order);

		System.out.println(order.getUser().getSensitiveObject().getSecretCode());
	}

}
