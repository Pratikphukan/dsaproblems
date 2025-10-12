package com.dsaproblems.DSAProblems.memorymanagement06;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dsaproblems.DSAProblems.memorymanagement.User;

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

	public List<Product> getProducts() throws CloneNotSupportedException {
		List<Product> copy = new ArrayList<>();
		for (Product product : products) {
			copy.add((Product) product.clone());
		}
		return copy;
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
