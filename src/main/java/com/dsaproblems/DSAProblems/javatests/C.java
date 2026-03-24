package com.dsaproblems.DSAProblems.javatests;

interface X {
    default void show() {
        System.out.println("A");
    }
}

interface Y {
    default void show() {
        System.out.println("B");
    }
}

public class C implements X, Y {
    @Override
    public void show() {
        X.super.show();
        Y.super.show();
    }

    public static void main(String[] args) {
        new C().show();

        int x = 10;
        //x = 12; //local variables referenced from a lambda expression must be final or effectively final
        Runnable r = () -> System.out.println(x);
        Thread t = new Thread(r);
        t.start();
    }
}
