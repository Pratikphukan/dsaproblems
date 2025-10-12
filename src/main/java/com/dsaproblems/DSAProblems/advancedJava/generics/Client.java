package com.dsaproblems.DSAProblems.advancedJava.generics;

public class Client {

    public static void main(String[] args) {

        Pair<String> stringPair = new Pair<>("Hello", "World");
        System.out.println("First: " + stringPair.getFirst());
        System.out.println("Second: " + stringPair.getSecond());

        Pair<Integer> integerPair = new Pair<>(1, 2);
        System.out.println("First: " + integerPair.getFirst());
        System.out.println("Second: " + integerPair.getSecond());
    }
}
