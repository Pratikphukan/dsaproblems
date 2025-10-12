package com.dsaproblems.DSAProblems.advancedJava;

import java.util.ArrayList;
import java.util.List;

class Box<T> {
    List<T> items;

    Box() {
        this.items = new ArrayList<>();
    }

    void addItem(T item) {
        items.add(item);
    }

    void printList(List<?> items) {
        for (Object obj : items) {
            System.out.println(obj); // safe, because everything is at least an Object
        }
    }
}

public class GenericExample {

    public static void main(String[] args) {
        List<?> items = new ArrayList<>();
        items.add(null); //You cannot add anything to list (except null), because the compiler does not know the actual type inside the list.

        Box<String> box = new Box<>(); //If you create Box<String>, then items is a List<String>.
        box.printList(items); //You can only read elements (as Object).

        box.addItem("Chocolate"); //You can safely add elements of type T to the list, because the compiler knows what T is.
        box.printList(box.items);
    }
}
