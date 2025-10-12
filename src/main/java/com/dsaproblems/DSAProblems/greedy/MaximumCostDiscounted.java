package com.dsaproblems.DSAProblems.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumCostDiscounted {

	public static void main(String[] args) {
		// 1,5,5,5,3,3,3
		// 3, 1, 3, 2, 3
		List<Integer> discountValidity = new ArrayList<>(Arrays.asList(1, 5, 5, 5, 3, 3, 3));
		// 5,4,3,8,2,7,1
		// 6, 5, 3, 1, 9
		List<Integer> carCosts = new ArrayList<>(Arrays.asList(5, 4, 3, 8, 20, 7, 10));
		System.out.println(maximumCostDiscounted(discountValidity, carCosts));
		System.out.println(maximumCostDiscountedv1(discountValidity, carCosts));
		System.out.println(maximumCostDiscountedv2(discountValidity, carCosts));
		System.out.println(maximumCostDiscountedv3(discountValidity, carCosts));
	}

	private static int maximumCostDiscountedv3(List<Integer> discountValidity, List<Integer> carCosts) {
		int noOfCars = carCosts.size();
		List<Car> cars = new ArrayList<>();
		for (int i = 0; i < noOfCars; i++) {
			cars.add(new Car(discountValidity.get(i), carCosts.get(i)));
		}
		Collections.sort(cars, new ValidityPriceCompare());
		System.out.println(cars);
		int currTime = 0;
		int totalCost = 0;
		int currMinCost = Integer.MAX_VALUE;
		Car car = null;
		for (int i = 0; i < noOfCars; i++) {
			car = cars.get(i);
			if (currTime < car.getDiscountValidity()) {
				totalCost += car.getPrice();
				currTime++;
				currMinCost = Math.min(currMinCost, car.getPrice());
			} else {
				if (currMinCost < car.getPrice()) {
					totalCost += (car.getPrice() - currMinCost);
				}
			}
		}
		return totalCost;
	}

	// working code
	private static int maximumCostDiscountedv2(List<Integer> discountValidity, List<Integer> carCosts) {
		int noOfCars = carCosts.size();
		List<Car> cars = new ArrayList<>();
		for (int i = 0; i < noOfCars; i++) {
			cars.add(new Car(discountValidity.get(i), carCosts.get(i)));
		}
		Collections.sort(cars);
		System.out.println(cars);
		int currTime = 0;
		Car car = null;
		Queue<Integer> minheap = new PriorityQueue<>();
		for (int i = 0; i < noOfCars; i++) {
			car = cars.get(i);
			if (currTime < car.getDiscountValidity()) {
				minheap.add(car.getPrice());
				currTime++;
			} else {
				if (minheap.peek() < car.getPrice()) {
					minheap.poll();
					minheap.add(car.getPrice());
				}
			}
		}
		int totalCost = 0;
		while (!minheap.isEmpty()) {
			totalCost += minheap.poll();
		}
		return totalCost;
	}

	// working code
	private static int maximumCostDiscountedv1(List<Integer> discountValidity, List<Integer> carCosts) {
		int noOfCars = carCosts.size();
		int totalDuration = 0;
		List<Car> cars = new ArrayList<>();
		for (int i = 0; i < noOfCars; i++) {
			cars.add(new Car(discountValidity.get(i), carCosts.get(i)));
			totalDuration = Math.max(totalDuration, discountValidity.get(i));
		}
		Collections.sort(cars);
		System.out.println(cars);
		int totalCost = 0;
		Car car = null;
		Queue<Integer> minheap = new PriorityQueue<>();
		int time = 0;
		int idx = 0;
		while (time <= totalDuration && idx < noOfCars) {
			car = cars.get(idx);
			if (car.getDiscountValidity() >= time + 1) {
				totalCost += car.getPrice();
				minheap.add(car.getPrice());
				time++;
			} else {
				if (minheap.peek() < car.getPrice()) {
					totalCost += (car.getPrice() - minheap.poll());
					minheap.add(car.getPrice());
				}
			}
			idx++;
		}
		return totalCost;
	}

	// working code
	private static int maximumCostDiscounted(List<Integer> discountValidity, List<Integer> carCosts) {
		int noOfCars = carCosts.size();
		List<Car> cars = new ArrayList<>();
		for (int i = 0; i < noOfCars; i++) {
			cars.add(new Car(discountValidity.get(i), carCosts.get(i)));
		}
		Collections.sort(cars);
		System.out.println(cars);
		int totalCost = 0;
		int currTime = 0;
		Car car = null;
		Queue<Integer> minheap = new PriorityQueue<>();
		for (int i = 0; i < noOfCars; i++) {
			car = cars.get(i);
			if (currTime < car.getDiscountValidity()) {
				totalCost += car.getPrice();
				minheap.add(car.getPrice());
				currTime++;
			} else {
				if (minheap.peek() < car.getPrice()) {
					totalCost += (car.getPrice() - minheap.poll());
					minheap.add(car.getPrice());
				}
			}
		}
		return totalCost;
	}

}
