package com.dsaproblems.DSAProblems.bitmanipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleNumber {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 1));
        System.out.println(findUniqueNumber(input));
    }

    private static int findUniqueNumber(List<Integer> input) {
        int num = 0;
        for (Integer element : input) {
            num = num ^ element;
        }
        return num;
    }

}
