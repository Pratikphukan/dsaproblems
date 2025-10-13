package com.dsaproblems.DSAProblems.aqr;

import java.util.ArrayList;

class Box {

    int width;

    int height;

    int length;

    void volume() {

        System.out.println(length * width * height);

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
    }

    private static void modify(Integer i, ArrayList<Integer> l) {
        i = i + 1;
        l.add(2); // Modifies the original list
        // l = new ArrayList<>(); // This would not affect the original reference
    }
}
