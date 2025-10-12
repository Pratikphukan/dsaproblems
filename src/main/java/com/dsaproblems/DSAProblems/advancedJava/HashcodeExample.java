package com.dsaproblems.DSAProblems.advancedJava;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashcodeExample {

    static class Person {
        private String name;

        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;
            Person person = (Person) obj;
            return this.age == person.age &&
                    name.equals(person.name);
        }

        //The hashCode method in Java is used to generate an integer
        // hash value for an object. In collections like HashMap,
        // HashSet, and Hashtable, it helps determine the bucket
        // location for storing objects, enabling fast lookup,
        // insertion, and deletion. Objects with the same hashCode
        // are grouped together, and equality is checked using equals.
        // Proper implementation of hashCode improves performance and
        // ensures correct behavior in hash-based collections.
        @Override
        public int hashCode() {
            System.out.println(Objects.hash(name, age));
            System.out.println(name.hashCode() + age);
            return name.hashCode() + age;
        }
    }

    public static void main(String[] args) {
        Person p1 = new Person("Alice", 30);
        Person p2 = new Person("Alice", 30);
        p1.hashCode();
        p2.hashCode();

        Set<Person> people = new HashSet<>();
        people.add(p1);
        people.add(p2); // Duplicate

        System.out.println(people.size());
    }
}
