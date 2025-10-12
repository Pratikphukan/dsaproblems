package com.dsaproblems.DSAProblems.advancedJava;

import java.util.concurrent.ConcurrentHashMap;

//ConcurrentHashMap in Java handles concurrent updates by dividing the map into
// segments (or bins) and using fine-grained locking. Each segment can be locked
// independently, allowing multiple threads to update different segments
// simultaneously without blocking each other. For most operations, it uses
// lock-free techniques (like CAS—Compare-And-Swap) for improved performance.
// This design minimizes contention and ensures thread safety for concurrent
// read and write operations.

//The load factor in a ConcurrentHashMap determines how full the map
// can get before its capacity is automatically increased.
// It helps balance memory usage and lookup performance.
// A lower load factor reduces collisions and improves access
// speed but uses more memory; a higher load factor saves memory
// but may slow down operations due to more collisions.
// The default load factor is 0.75.
public class LoadFactorExample {

    //The load factor in a ConcurrentHashMap controls when the map
    // increases its capacity. If the number of entries exceeds
    // capacity * loadFactor, the map resizes to reduce collisions
    // and maintain performance
    public static void main(String[] args) {

        //Below, a ConcurrentHashMap is created with an initial capacity
        // of 4 and a load factor of 0.5. When the number of entries
        // exceeds 4 * 0.5 = 2, the map resizes.
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>(4, 0.5f);

        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C"); // Triggers resize as entries exceed 2
        System.out.println(map);


        //ConcurrentHashMap does not allow null keys or null values.
        // If you try to put a null key or value, it throws a NullPointerException
        //This restriction helps avoid ambiguity in concurrent environments, where null could mean either "no mapping" or "mapping in progress."
        map.put(1, null);
        map.put(null, "D");
        System.out.println(map);


        //In Java, "intern" refers to storing a single copy of each
        // distinct string value in a special memory area called the
        // string pool. When you call the intern() method on a string,
        // it returns a reference to the canonical instance from the
        // pool. This helps save memory and allows fast string
        // comparisons using ==.
        String str = new String("example");
        String internedStr = str.intern();
        System.out.println(internedStr);

    }
}
