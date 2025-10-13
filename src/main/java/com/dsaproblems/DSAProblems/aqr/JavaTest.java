package com.dsaproblems.DSAProblems.aqr;

import java.util.Arrays;
import java.util.List;

class Shape {
    public void draw() {
        System.out.println("Drawing a shape");
    }
}

class Circle extends Shape {

    private int radius;

    Circle(int radius) {
        this.radius = radius;
    }

    public void draw() {
        System.out.println("Drawing a circle");
    }

    public int getRadius() {
        return radius;
    }
}


class A {
    public final void display() {
        System.out.println("A's display");
    }

    public void displayv1() {
        System.out.println("A's display");
    }
}

class B extends A {
//    public void display() {
//        System.out.println("B's display");
//    }

    public void displayv1() {
        System.out.println("B's display");
    }
}

interface InterfaceA {
    default void greet() {
        System.out.println("Hello from IA");
    }

    static void staticMethod() {
        System.out.println("Static method in interface");
    }
}

interface InterfaceB {
    default void greet() {
        System.out.println("Hello from IB");
    }
}

//When a class implements two interfaces that both declare a default
// method with the same signature, the class must override the method
// and provide its own implementation to resolve the ambiguity.
class MyClass implements InterfaceA, InterfaceB {

    @Override
    public void greet() {
        // Choose which interface's default to use, or provide custom logic
        InterfaceA.super.greet();
        // Or: InterfaceB.super.greet();
        // Or: System.out.println("Hello from MyClass");
    }
}

public class JavaTest {

    public static void main(String[] args) {
        Shape shape = new Circle(4);
        shape.draw();
        //Even though the actual object is a Circle,
        // only methods defined in the Shape class (and overridden ones)
        // are accessible through the shape reference.
        //shape.getRadius();
        Circle circle = new Circle(6);
        circle.draw();
        System.out.println(circle.getRadius());

        B obj = new B();
        obj.display();

        A obj1 = new B();
        obj1.displayv1();

        A obj2 = new A();
        obj2.displayv1();

        B obj3 = new B();
        obj3.displayv1();


        InterfaceA.staticMethod();
        //MyClass.staticMethod();//not visible

        int sum = 0;
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        //the variable sum is being modified inside a lambda expression
        // (numbers.forEach(n -> sum += n);). In Java, variables used
        // inside a lambda must be final or effectively final, meaning
        // they cannot be modified within the lambda.

        //numbers.forEach(n -> sum += n);
        System.out.println(sum);

        int x = 10;
        Runnable r = () -> System.out.println(x);
        Thread t = new Thread(r);
        t.start();
    }
}
