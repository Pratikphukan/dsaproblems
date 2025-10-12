package com.dsaproblems.DSAProblems.advancedJava;

class Example {
    private int value;

    public Example(int val) {
        this.value = val;
    }

    public void display() {
        System.out.println("Parent's display");
    }
}

class ChildExample extends Example {

    public ChildExample(int val) {
        super(val);
    }
}

public class MyClass {

    static int x = 10;

    public static void main(String[] args) {

        MyClass obj = new MyClass();
        obj.display();
        display();

        MyClass obj1 = new MyClass();
        MyClass obj2 = new MyClass();
        obj1.x = 20;
        System.out.println(obj1.x + " " + obj2.x);
        Example example = new Example(5);

        ChildExample eg = new ChildExample(87);
        eg.display();

    }

    private static void display() {
        System.out.println("Static method");
    }
}
