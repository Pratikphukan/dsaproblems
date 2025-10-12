package com.dsaproblems.DSAProblems.java8;

import java.util.function.Predicate;

public class Client1 {

	public static void main(String[] args) {
		Foldable.printFoldInstructions();
		Item item1 = new Item();
		item1.fold();

		Item item2 = new Item("Jeans");
		item2.fold();

		Item item3 = new Item("Shirt");
		item3.fold();

		Item item4 = new Item("Bag");
		item3.wash();

		Predicate<String> predicate = new Predicate<>() {

			@Override
			public boolean test(String input) {
				return input.startsWith("M");
			}
		};

		System.out.println(predicate.test("Maya"));
	}

}
