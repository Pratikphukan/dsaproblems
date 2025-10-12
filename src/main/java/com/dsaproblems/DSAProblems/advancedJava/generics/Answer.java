package com.dsaproblems.DSAProblems.advancedJava.generics;

public class Answer {

    static boolean showExpectedResult = false;
    static boolean showHints = false;

    public static DataContainer<String> createsStringContainer() {
        return new DataContainer<>("Hello");
    }
}
