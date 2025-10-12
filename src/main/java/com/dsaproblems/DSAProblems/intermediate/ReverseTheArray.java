package com.dsaproblems.DSAProblems.intermediate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class ReverseTheArray {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 1));

        Function<List<Integer>, List<Integer>> reverseArrayv1 = arr -> {
            int start = 0;
            int end = arr.size() - 1;
            while (start < end) {
                int temp = arr.get(start);
                arr.set(start, arr.get(end));
                arr.set(end, temp);
                start++;
                end--;
            }
            return arr;
        };

        System.out.println(reverseArrayv1.apply(input));
    }
}
