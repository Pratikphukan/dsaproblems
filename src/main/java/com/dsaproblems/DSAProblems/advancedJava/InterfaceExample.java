package com.dsaproblems.DSAProblems.advancedJava;

import java.util.Arrays;
import java.util.List;

interface A {
    void methodA();
}

interface B {
    void methodB();
}

interface C extends A, B {
    void methodC();
}

public class InterfaceExample {

    public static void main(String[] args) {
        int sum = 0;
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        //numbers.forEach(n -> sum += n);
        System.out.println(sum);
    }
}
