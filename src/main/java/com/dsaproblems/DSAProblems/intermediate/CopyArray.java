package com.dsaproblems.DSAProblems.intermediate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CopyArray {

    public static void main(String[] args) {
        ArrayList<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 1));
        int B = 3;
        System.out.println(getFinalArrayv1(input, B));
        System.out.println(getFinalArrayv2(input, B));
    }

    private static ArrayList<Integer> getFinalArrayv2(ArrayList<Integer> input, int B) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (Integer item : input) {
            ans.add(item + B);
        }
        return ans;
    }

    private static ArrayList<Integer> getFinalArrayv1(ArrayList<Integer> input, int B) {
        Function<Integer, Integer> adder = x -> x + B;
        return input.stream().map(adder).collect(Collectors.toCollection(ArrayList::new));
    }
}
