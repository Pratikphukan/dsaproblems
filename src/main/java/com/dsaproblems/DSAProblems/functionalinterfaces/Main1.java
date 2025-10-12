package com.dsaproblems.DSAProblems.functionalinterfaces;

import java.util.Random;
import java.util.function.IntBinaryOperator;

public class Main1 {

	public static void main(String[] args) {

		Calculator calculator = new Calculator() {

			@Override
			public int add(int x, int y) {
				return x + y;
			}
		};

		System.out.println(calculator.add(3, 7));

		Calculator calculator2 = (int x, int y) -> {
			return x + y;
		};

		System.out.println(calculator2.add(8, 9));

		Calculator calculator3 = (int x, int y) -> {
			Random random = new Random();
			int randomNumber = random.nextInt(50);
			return x * y + randomNumber;
		};

		System.out.println(calculator3.add(1, 6));

		IntBinaryOperator calculator4 = (x, y) -> {
			Random random = new Random();
			int randomNumber = random.nextInt(50);
			return x * y + randomNumber;
		};

		System.out.println(calculator4.applyAsInt(7, 2));
	}

}
