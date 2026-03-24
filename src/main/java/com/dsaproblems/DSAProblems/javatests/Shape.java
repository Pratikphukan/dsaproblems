package com.dsaproblems.DSAProblems.javatests;

public class Shape {

    public void draw() {
        System.out.println("Drawing a shape");
    }
}

class Circle extends Shape {

    public void draw() {
        System.out.println("Drawing a circle");
    }
}

class Main {
    public static void main(String[] args) {
        Shape shape1 = new Shape();
        shape1.draw();
        Shape shape2 = new Circle();
        shape2.draw();
    }
}
