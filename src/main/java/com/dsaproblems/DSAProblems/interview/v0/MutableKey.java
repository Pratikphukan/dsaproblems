package com.dsaproblems.DSAProblems.interview.v0;

import java.util.HashMap;
import java.util.Map;

public class MutableKey {

    public static void main(String[] args) {
        Map<Person, String> map = new HashMap<>();
        Person p = new Person("John", 25);
        map.put(p, "Developer");

        //mutating the key after it has been added to the map
        p.name = "Jane";

        System.out.println(map.get(p));
        System.out.println(map);

        //After mutation, p.hashCode() is different, so HashMap looks in a different bucket.
        //But the key–value pair is still sitting in the old bucket.
    }
}
