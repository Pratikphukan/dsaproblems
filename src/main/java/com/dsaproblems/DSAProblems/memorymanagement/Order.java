package com.dsaproblems.DSAProblems.memorymanagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {

	private LocalDate orderDate;

	private List<String> products;

	private User user;

	public Order(LocalDate orderDate, List<String> products, User user) {
		this.orderDate = orderDate;
		// this.products = products;
		this.products = new ArrayList<>(products);
		this.user = user;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public List<String> getProducts() {
		// return products;
		List<String> copy = new ArrayList<>(products);
		return copy;
	}

	public void setProducts(List<String> products) {
		this.products = products;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
