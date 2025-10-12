package com.dsaproblems.DSAProblems.advancedJava;

public class FinalizeExample {

    //The finalize method in Java is called by the garbage collector
    // before an object is destroyed. Its purpose is to allow the
    // object to release resources (like closing files or network
    // connections) before memory is reclaimed. However, its use
    // is discouraged because it is unreliable and can cause
    // performance issues. Instead, use try-with-resources or
    // explicit cleanup methods.
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Object is being garbage collected");
        super.finalize();
    }

    //finalize is deprecated in Java 9+ and should not be used in production code.
    // Use explicit resource management instead
    //The finalize method is called by the garbage collector before an object is destroyed.
    public static void main(String[] args) {
        FinalizeExample obj = new FinalizeExample();
        obj = null; // Make object eligible for GC
        System.gc();// Suggest GC to run (not guaranteed)

    }
}
