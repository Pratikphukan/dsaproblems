package com.dsaproblems.DSAProblems.backtracking;

import java.util.ArrayList;
import java.util.List;

import static com.dsaproblems.DSAProblems.backtracking.Permutations.solvev2;

public class UniquePermutations {

    public static void main(String[] args) {
        //1,2,4
        //1,1,2
        ArrayList<Integer> input = new ArrayList<>(List.of(1, 1, 2));
        System.out.println(getPermuatationsv1(input));
    }

    private static ArrayList<ArrayList<Integer>> getPermuatationsv1(ArrayList<Integer> input) {
        ArrayList<ArrayList<Integer>> output = new ArrayList<>();
        //solvev1(input, output, 0);
        solvev2(input, output, 0);
        return output;
    }

}
