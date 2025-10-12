package com.dsaproblems.DSAProblems.advancedJava;

import lombok.Getter;
import lombok.Setter;

class Test {
    int x, y;
}

class Test2 implements Cloneable {
    int a;
    int b;
    Test c = new Test();

    @Override
    public Test2 clone() throws CloneNotSupportedException {
        return (Test2) super.clone();
    }
}

public class CloneExample {
}
