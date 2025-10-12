package com.dsaproblems.DSAProblems.memorymanagement05;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dsaproblems.DSAProblems.memorymanagement.User;
import com.dsaproblems.DSAProblems.memorymanagement04.Product;

public class Order {

	private LocalDate orderDate;

	private List<Product> products;

	private User user;

	public Order(LocalDate orderDate, List<Product> products, User user) {
		this.orderDate = orderDate;
		this.products = new ArrayList<>(products);
		this.user = user;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public List<Product> getProducts() {
		// return List.copyOf(products);
		return Collections.unmodifiableList(products);
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
