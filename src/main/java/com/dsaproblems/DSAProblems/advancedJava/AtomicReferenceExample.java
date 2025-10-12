package com.dsaproblems.DSAProblems.advancedJava;

import java.util.concurrent.atomic.AtomicReference;

//An AtomicReference in Java is a class from java.util.concurrent.atomic that
// provides an object reference which can be read and written atomically.
// It is used to safely update object references in a multithreaded environment
// without using explicit synchronization.

//Why use it?
//To perform atomic operations (like compare-and-set) on object references.
//To avoid race conditions when multiple threads update a shared reference.
//Useful in lock-free algorithms and concurrent data structures.
public class AtomicReferenceExample {

    private static final AtomicReference<String> sharedRef = new AtomicReference<>();

    public static void main(String[] args) {

        Runnable updater = () -> {
            String prev;
            do {
                prev = sharedRef.get();
            } while (!sharedRef.compareAndSet(prev, prev + " -> Updated"));
        };


        Thread t1 = new Thread(updater);
        Thread t2 = new Thread(updater);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println(sharedRef.get());
    }
}
