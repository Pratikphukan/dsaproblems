package com.dsaproblems.DSAProblems.functionalprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Client1 {

	public static void main(String[] args) {

		List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

		Function<Integer, Integer> timesTwo = (x) -> x * 2;
		List<Integer> doubled = listOfIntegers.stream().map(timesTwo).collect(Collectors.toList());
		System.out.println(doubled);

		Predicate<Integer> isEven = (x) -> x % 2 == 0;
		List<Integer> evens = listOfIntegers.stream().filter(isEven).collect(Collectors.toList());
		System.out.println(evens);

		List<String> words = new ArrayList<>(Arrays.asList("hello", "functional", "programming", "is", "cool"));

		Function<Integer, Predicate<String>> createLengthTest = (minLength) -> {
			return (word) -> word.length() > minLength;
		};
		Predicate<String> isLongerThan3 = createLengthTest.apply(3);
		Predicate<String> isLongerThan10 = createLengthTest.apply(10);
		List<String> wordsLongerThan3 = words.stream().filter(isLongerThan3).collect(Collectors.toList());
		System.out.println(wordsLongerThan3);
		List<String> wordsLongerThan10 = words.stream().filter(isLongerThan10).collect(Collectors.toList());
		System.out.println(wordsLongerThan10);

		BinaryOperator<Integer> getSum = (acc, x) -> {
			int result = acc + x;
			System.out.println(acc + ", " + x + ", " + result);
			return result;
		};
		int sum = listOfIntegers.stream().reduce(0, getSum);
		System.out.println(sum);

		Map<Boolean, List<String>> wordLengthMap = words.stream()
				.collect(Collectors.partitioningBy((word) -> word.length() > 5));
		System.out.println(wordLengthMap);

		List<String> processedWords = words.parallelStream().map(word -> {
			System.out.println("Uppercasing " + word);
			return word.toUpperCase();
		}).map(word -> {
			System.out.println("Lowercasing " + word);
			return word.toLowerCase();
		}).collect(Collectors.toList());
		System.out.println(processedWords);

		List<String> processedWords1 = words.parallelStream().map(word -> {
			System.out.println("Lowercasing " + word);
			return word.toLowerCase();
		}).map(word -> {
			System.out.println("Uppercasing " + word);
			return word.toUpperCase();
		}).collect(Collectors.toList());
		System.out.println(processedWords1);

	}

}
