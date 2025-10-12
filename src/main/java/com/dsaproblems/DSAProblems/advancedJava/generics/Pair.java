package com.dsaproblems.DSAProblems.advancedJava.generics;

import lombok.Getter;

@Getter
public class Pair<T> {
    private T first;
    private T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }
}
