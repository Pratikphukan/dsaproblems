package com.dsaproblems.DSAProblems.advancedJava;

@FunctionalInterface
interface MyInterface {

    void myMethod(int x, int y);

    //Although equals is an override from Object, it still counts
    // towards the abstract methods in an interface unless marked as default or static.
    boolean equals(Object obj);

    default void defaultMethod() {
        System.out.println("Default method implementation");
    }

    static void staticMethod() {
        System.out.println("Static method implementation");
    }
}

class ExampleClass implements MyInterface {

    @Override
    public void myMethod(int x, int y) {
        System.out.println(x + " " + y);
    }

//    @Override
//    public void defaultMethod() {
//        MyInterface.super.defaultMethod();
//    }
}

class InterfaceTest {
    public static void main(String[] args) {
        ExampleClass exampleClass = new ExampleClass();
        exampleClass.myMethod(3, 8);
        exampleClass.defaultMethod();
        MyInterface.staticMethod();
    }
}
