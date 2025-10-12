package com.dsaproblems.DSAProblems.advancedJava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelStreamExample {

    //A parallel stream in Java allows stream operations to be executed
    // concurrently using multiple threads, leveraging multicore processors
    // for faster processing of large data sets.
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int sum = numbers.parallelStream()
                .mapToInt(item -> item.intValue())
                .sum();


        System.out.println("Sum: " + sum);

        //parallelStream() splits largeList into chunks.
        //Each chunk is processed by a separate thread.
        //The filter operation runs concurrently on different parts of the list.
        //The results are combined to get the final count.
        List<Integer> largeList = IntStream.rangeClosed(1, 1_000_000)
                .boxed()
                .collect(Collectors.toList());

        long evenCount = largeList
                .parallelStream()
                .filter(num -> num % 2 == 0)
                .count();

        System.out.println("Number of even numbers: " + evenCount);
    }
}
