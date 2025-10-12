package com.dsaproblems.DSAProblems.greedy;

public class Car implements Comparable<Car> {

	private Integer discountValidity;

	private Integer price;

	public Car(Integer discountValidity, Integer price) {
		super();
		this.discountValidity = discountValidity;
		this.price = price;
	}

	public Integer getDiscountValidity() {
		return discountValidity;
	}

	public void setDiscountValidity(Integer discountValidity) {
		this.discountValidity = discountValidity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public int compareTo(Car car) {
		return this.discountValidity - car.discountValidity;
	}

	@Override
	public String toString() {
		return "[v=" + discountValidity + ", p=" + price + "]";
	}

}
