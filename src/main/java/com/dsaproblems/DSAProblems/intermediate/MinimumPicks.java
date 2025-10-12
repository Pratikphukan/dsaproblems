package com.dsaproblems.DSAProblems.intermediate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/*
 * Return the difference between the maximum among all even numbers of A and
 * the minimum among all odd numbers in A.
 */
public class MinimumPicks {

    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(-98, 54, -52, 15, 23, -97, 12, -64, 52, 85);
        // -98,54,-52,15,23,-97,12,-64,52,85
        // 5, 17, 100, 1
        System.out.println(diffBetweenEvenMaxAndOddMinv1(input));
        System.out.println(diffBetweenEvenMaxAndOddMinv2(input));
        System.out.println(diffBetweenEvenMaxAndOddMinv3(input));
    }

    private static int diffBetweenEvenMaxAndOddMinv3(List<Integer> input) {
        Comparator<Integer> integerComparator = Integer::compare;
        Integer evenMax = input.stream().filter(num -> num % 2 == 0).max(integerComparator).orElse(null);
        Integer oddMin = input.stream().filter(num -> num % 2 == 1 || num % 2 == -1).min(integerComparator).orElse(null);
        if (evenMax == null || oddMin == null) {
            return 0; // Return null if either value is missing
        }
        return evenMax - oddMin;
    }

    // working, little slower than v1
    private static int diffBetweenEvenMaxAndOddMinv2(List<Integer> input) {
        Optional<Integer> evenMax = input.parallelStream().filter(num -> num % 2 == 0).max((x, y) -> x - y);
        Optional<Integer> oddMin = input.parallelStream().filter(num -> num % 2 == 1 || num % 2 == -1)
                .max((x, y) -> y - x);
        return evenMax.get() - oddMin.get();
    }

    // working
    private static int diffBetweenEvenMaxAndOddMinv1(List<Integer> A) {
        int evenMax = Integer.MIN_VALUE;
        int oddMin = Integer.MAX_VALUE;
        for (int a : A) {
            if (a % 2 == 0) {
                evenMax = Math.max(a, evenMax);
            } else {
                oddMin = Math.min(a, oddMin);
            }
        }
        return evenMax - oddMin;
    }

}
