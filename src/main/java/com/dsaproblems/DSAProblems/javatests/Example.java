package com.dsaproblems.DSAProblems.javatests;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class A {

//    public final void display() {
//        System.out.println("A's display");
//    }

    public void display() {
        System.out.println("A's display");
    }
}

class B extends A {
    public void display() {
        System.out.println("B's display");
    }
}


class MyClass {
    static void display() {
        System.out.println("Static method");
    }
}

public class Example {

    private static int count = 0;

    public Example() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        Example obj1 = new Example();
        Example obj2 = new Example();
        System.out.println(Example.count);
        System.out.println(obj1.getCount() + " " + obj2.getCount());

        MyClass obj3 = new MyClass();
        obj3.display();
        MyClass.display();

        B obj4 = new B();
        obj4.display();

        A obj5 = new B();
        obj5.display();

        A obj6 = new A();
        obj6.display();

        List<String> words = Arrays.asList("apple", "banana", "orange", "grape", "pear");
        List<String> result = words.stream().filter(w -> w.length() > 5).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(result);
    }
}
