package com.dsaproblems.DSAProblems.functionalinterfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		// way1
		Greeting greeting = new HelloGreeting();
		greeting.sayHello();

		// way2
		Greeting greeting2 = new Greeting() {
			@Override
			public void sayHello() {
				System.out.println("Hello Pratik!!");
			}
		};
		greeting2.sayHello();

		// way3 using lambdas
		Greeting greeting3 = () -> {
			System.out.println("Hello Prajapati!!");
		};
		greeting3.sayHello();

		Integer[] scores = new Integer[] { 80, 66, 73, 92, 43 };
		Stream<Integer> scoresStream = Arrays.stream(scores);
		System.out.println(scoresStream);

		List<String> shoppingList = new ArrayList<>();
		shoppingList.add("coffee");
		shoppingList.add("bread");
		shoppingList.add("pineapple");
		shoppingList.add("milk");
		shoppingList.add("pasta");

		Stream<String> shoppingListStream = shoppingList.stream();
		// shoppingListStream.sorted().forEach(item -> System.out.println(item));

		shoppingListStream.sorted().map(item -> item.toUpperCase()).filter(item -> item.startsWith("P"))
				.forEach(item -> System.out.println(item));

		Stream<String> lettersStream = Stream.of("a", "b", "c");
		System.out.println(lettersStream);

		List<String> sortedList = shoppingList.stream().sorted().map(item -> item.toUpperCase())
				.filter(item -> item.startsWith("P")).collect(Collectors.toList());

		System.out.println(sortedList);
	}

}
