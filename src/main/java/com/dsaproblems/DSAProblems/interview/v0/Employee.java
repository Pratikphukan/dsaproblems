package com.dsaproblems.DSAProblems.interview.v0;

import java.util.Objects;

public class Employee {

    String name;

    int age;

    Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
