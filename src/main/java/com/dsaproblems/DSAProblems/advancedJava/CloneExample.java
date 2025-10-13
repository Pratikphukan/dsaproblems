package com.dsaproblems.DSAProblems.advancedJava;

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
    public static void main(String[] args) throws CloneNotSupportedException {
        Test2 t = new Test2();
        Test2 tClone = t.clone();
        //Both t and tClone have their own Test2 objects, but their c fields (of type Test) point to the same Test instance.
        // This is a shallow copy.
        //A deep copy would require cloning the Test object as well, so each Test2 has its own separate Test instance.
        System.out.println(t);
        System.out.println(tClone);
    }
}
