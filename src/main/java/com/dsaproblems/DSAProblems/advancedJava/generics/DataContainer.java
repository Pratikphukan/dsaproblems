package com.dsaproblems.DSAProblems.advancedJava.generics;

public class DataContainer<T> {

    private T value;

    public DataContainer(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
