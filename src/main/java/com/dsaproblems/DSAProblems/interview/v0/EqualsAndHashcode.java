package com.dsaproblems.DSAProblems.interview.v0;

import java.util.HashSet;

public class EqualsAndHashcode {

    public static void main(String[] args) {
        //HashSet, HashMap, and HashTable use hashCode to quickly locate a bucket.
        //Then, inside that bucket, they use equals to check if two objects are the same.
        HashSet<Person> set = new HashSet<>();
        Person p1 = new Person("Alice", 30);
        System.out.println(p1.hashCode());
        set.add(p1);
        Person p2 = new Person("Alice", 30);
        System.out.println(p2.hashCode());
        set.add(p2);
        System.out.println(set.size()); // Output: 1 if equals() and hashCode() are overridden correctly

        HashSet<Student> set1 = new HashSet<>();
        Student s1 = new Student("Bob", 25);
        System.out.println(s1.hashCode());
        set1.add(s1);
        Student s2 = new Student("Bob", 25);
        System.out.println(s2.hashCode());
        set1.add(s2);
        System.out.println(set1.size()); // Output: 2 if equals() and hashCode() are not overridden

        HashSet<Employee> set2 = new HashSet<>();
        Employee e1 = new Employee("Charlie", 28);
        System.out.println(e1.hashCode());
        set2.add(e1);
        Employee e2 = new Employee("Charlie", 28);
        System.out.println(e2.hashCode());
        set2.add(e2);
        System.out.println(set2.size()); // Output: 2 if hashCode() is overridden
        // equals() not overridden → falls back to Object.equals (== identity check)
        //The HashSet puts them in the same bucket, but treats them as different entries.


        HashSet<Player> set3 = new HashSet<>();
        Player pl1 = new Player("Barlie", 26);
        System.out.println(pl1.hashCode());
        set3.add(pl1);
        Player pl2 = new Player("Barlie", 26);
        System.out.println(pl2.hashCode());
        set3.add(pl2);
        System.out.println(set3.size());
        //Two logically equal objects (a.equals(b) == true) may return different hash codes.
        //They’ll go into different buckets in a HashMap/HashSet.


        /*
        The Java Object class defines the rules:
        Consistency Rule:

        If two objects are equal according to equals(),
            → they must return the same hashCode().

        Non-requirement Rule:

        If two objects have the same hashCode(),
            → they don’t have to be equal (hash collisions are allowed).

        Stability Rule
            ->The hashCode() of an object should stay the same as long as the object’s state (used in equals) doesn’t change.
         */
    }
}
