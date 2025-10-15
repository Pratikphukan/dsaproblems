package com.dsaproblems.DSAProblems.aqr;

import java.util.ArrayList;

class Student {
    String name;
    int rollNo;

    Student(int num) {
        this.rollNo = num;
        this.name = "abc";
    }

    Student(int num, String str) {
        this.rollNo = num;
        this.name = str;
    }

    public void print() {
        System.out.print(this.name + " " + this.rollNo + " ");
    }
}

class Book {
    int price;
    static int count;

    public Book(int price) {
        this.price = price;
        count++;
    }
}

class Box {

    int width;
    int height;
    int length;

    void volume() {
        System.out.println(length * width * height);
    }
}

class IB {
    protected void getData() {
        System.out.println("Inside IB");
    }
}

class InterviewBit extends IB {
    protected void getData() {
        System.out.println("Inside InterviewBit");
    }
}

class Derived {
    protected void getDetails() {
        System.out.println("Derived class");
    }
}

class Test extends Derived {
    protected final void getDetails() {
        System.out.println("Test class");
    }
}

class Vehicle {
    int seats;
    int speed;

    Vehicle(int seats, int speed) {
        System.out.println("Vehicle ");
        this.seats = seats;
        this.speed = speed;
    }
}

class Car extends Vehicle {

    int id;

    //The Car class needs a constructor like Vehicle because Vehicle does not
    // have a no-argument (default) constructor. In Java, if a superclass
    // defines only parameterized constructors, subclasses must explicitly
    // call one of them using super(...) in their own constructors. Otherwise,
    // the code will not compile.
    Car(int seats, int speed) {
        super(seats, speed);
        System.out.println("Car ");
    }
}

interface Scaler {
    void myMethod();

    void getInfo();
}

abstract class InterviewReady implements Scaler {
    void getData() {
        System.out.println("IB");
    }
}

class InterviewReadyExt extends InterviewReady {
    public void myMethod() {
        System.out.println("InterviewBit");
    }

    public void getInfo() {
        System.out.println("Scaler");
    }
}

public class JavaExamples {

    public static void main(String[] args) {
        byte by = 127;
        by++;
        System.out.println(by);

        short sh = (short) 201000;
        System.out.println(sh);

        //Because Integer in Java is immutable and passed by value.
        // When you call modify(i), a copy of the reference to the
        // Integer object is passed. Inside modify, i = i + 1; creates
        // a new Integer object, but this change does not affect the
        // original reference in main. Thus, the value of i in main
        // remains unchanged.

        //With an ArrayList, the reference to the object is still
        // passed by value, but since ArrayList is mutable,
        // changes to its contents inside a method will affect
        // the original list. However, reassigning the reference
        // inside the method does not affect the original reference.

        Integer i = Integer.valueOf(12);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        System.out.print(i + " " + list);
        modify(i, list);
        System.out.println();
        System.out.print(i + " " + list);


        System.out.println();
        ArrayList obj = new ArrayList();
        obj.add("A");
        obj.add(0, "B");
        System.out.println(obj.size());

        Box b = new Box();
        b.height = 5;
        b.width = 4;
        b.volume();


        Student s1 = new Student(101);
        s1.print();
        Student s2 = new Student(150, "xyz");
        s2.print();

        System.out.print(Book.count + " ");
        Book b1 = new Book(500);
        Book b2 = new Book(600);
        System.out.println(Book.count);


        IB o = new InterviewBit();
        o.getData();

        Derived x = new Derived();
        x.getDetails();

        Derived y = new Test();
        y.getDetails();

        InterviewReady ir = new InterviewReadyExt();
        ir.getInfo();
    }

    private static void modify(Integer i, ArrayList<Integer> l) {
        i = i + 1;
        l.add(2); // Modifies the original list
        // l = new ArrayList<>(); // This would not affect the original reference
    }
}
