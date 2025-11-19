package com.dsaproblems.DSAProblems.advancedJava;

import java.util.ArrayList;
import java.util.List;

public class ConsistentHashingClient {

    public static void main(String[] args) {
        ArrayList<String> A1 = new ArrayList<>(List.of("ADD", "ASSIGN", "ADD", "ASSIGN", "REMOVE", "ASSIGN"));
        ArrayList<String> B1 = new ArrayList<>(List.of("INDIA", "NWFJ", "RUSSIA", "OYVL", "INDIA", "IGAX"));
        ArrayList<Integer> C1 = new ArrayList<>(List.of(7, 3, 5, 13, -1, 17));
        System.out.println(solvev1(A1, B1, C1));
    }

    private static ArrayList<Integer> solvev1(ArrayList<String> A, ArrayList<String> B, ArrayList<Integer> C) {
        ConsistentHashing consistentHashing = new ConsistentHashing();
        for (int i = 0; i < A.size(); i++) {
            consistentHashing.performOperation(A.get(i), B.get(i), C.get(i));
        }
        return consistentHashing.getAnswers();
    }
}
