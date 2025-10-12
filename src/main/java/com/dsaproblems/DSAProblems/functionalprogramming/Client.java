package com.dsaproblems.DSAProblems.functionalprogramming;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/*
 * Collection.forEach() uses the collection’s iterator (if one is specified), 
 * so the processing order of the items is defined. In contrast, the processing 
 * order of Collection.stream().forEach() is undefined.
 */

/*
 * Parallel streams allow us to execute the stream in multiple threads, and in such 
 * situations, the execution order is undefined. Java only requires all threads to 
 * finish before any terminal operation, such as Collectors.toList(), is called
 */
public class Client {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("A", "B", "C", "D");

		// way1
		for (String item : list) {
			System.out.println(item);
		}

		// way2
		list.forEach(item -> System.out.println(item));

		// way2 alternate
		Consumer<String> consumer = item -> System.out.println(item);
		list.forEach(consumer);

		// way3
		list.stream().forEach(consumer);

		list.parallelStream().forEach(consumer);
	}

}
