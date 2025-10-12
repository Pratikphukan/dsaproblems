package com.dsaproblems.DSAProblems.intermediate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ElementsHavingTwoGreaterElements {

    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(11, 17, 100, 5);
        System.out.println(findElementsHavingTwoGreaterElements(input));
        System.out.println(findElementsHavingTwoGreaterElementsv1(input));
        System.out.println(findElementsHavingTwoGreaterElementsv2(input));
        System.out.println(findElementsHavingTwoGreaterElementsv3(input));
    }

    private static ArrayList<Integer> findElementsHavingTwoGreaterElementsv3(List<Integer> input) {
        Comparator<Integer> integerComparator = Integer::compare;
        Integer firstLargest = input.stream().max(integerComparator).orElse(null);
        Integer secondLargest = input.stream().filter(num -> num < firstLargest).max(integerComparator).orElse(1000000000);
        ArrayList<Integer> ans = new ArrayList<>();
        input.stream().filter(num -> num < secondLargest).forEach(ans::add);
        return ans;
    }

    private static List<Integer> findElementsHavingTwoGreaterElementsv2(List<Integer> input) {
        int first = input.get(0) > input.get(1) ? input.get(0) : input.get(1);
        int second = input.get(0) > input.get(1) ? input.get(1) : input.get(0);
        for (int i = 2; i < input.size(); i++) {
            if (input.get(i) > first) {
                second = first;
                first = input.get(i);
            }
            if (input.get(i) > second && input.get(i) != first) {
                second = input.get(i);
            }
        }
        final int secondLargest = second;
        return new ArrayList<>(input.parallelStream().filter(num -> num < secondLargest).collect(Collectors.toList()));
    }

    // working-> 9000ms
    private static List<Integer> findElementsHavingTwoGreaterElementsv1(List<Integer> input) {
        int first, second;
        if (input.get(0) > input.get(1)) {
            first = input.get(0);
            second = input.get(1);
        } else {
            first = input.get(1);
            second = input.get(0);
        }
        for (int i = 2; i < input.size(); i++) {
            if (input.get(i) > first) {
                second = first;
                first = input.get(i);
            }
            if (input.get(i) > second && input.get(i) != first) {
                second = input.get(i);
            }
        }
        final int secondLargest = second;
        return new ArrayList<>(input.stream().filter(num -> num < secondLargest).collect(Collectors.toList()));
    }

    // working-> 9000ms
    private static ArrayList<Integer> findElementsHavingTwoGreaterElements(List<Integer> A) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int a = 0;
        for (int i = 0; i < A.size(); i++) {
            a = A.get(i);
            if (a > first) {
                second = first;
                first = a;
            }
            if (a > second && a != first) {
                second = a;
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (Integer num : A) {
            if (num < second) {
                ans.add(num);
            }
        }
        return ans;
    }

}
