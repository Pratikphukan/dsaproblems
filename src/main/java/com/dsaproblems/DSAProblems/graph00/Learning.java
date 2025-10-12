package com.dsaproblems.DSAProblems.graph00;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Learning {

    public static void main(String[] args) {
        List<String> edges1 = new ArrayList<>();
        edges1.add("pratik");

        ArrayList<String> edges2 = (ArrayList<String>) edges1;
        edges2.add("pratik1");

        /*
        The problem is a type mismatch.
        You cannot assign a List<String>
        (which is the declared type of edges1) to an
        ArrayList<String> variable (edges2).
        Even though the object is an ArrayList,
        the compiler only knows edges1 as a List, so
        the assignment is not allowed.
         */

        ArrayList<String> edges3 = new ArrayList<>();
        edges3.add("pratik");

        List<String> edges4 = edges3;
        edges4.add("pratik1");

        List<String> edges5 = new ArrayList<>();

        /*
        Yes, ArrayList<String> is a subclass of List<String>.
        You can assign an ArrayList<String> to a List<String>
        variable, but not the other way around without an explicit cast.
        The key point is that the generic type
        parameters (String in this case) must match exactly
        for the assignment to be valid.
         */

        /*
        Java generics are invariant, meaning that even if
        ArrayList is a subclass of List, ArrayList<T> is not
        a subclass of List<U> unless T and U are the same type.
         */

        List<ArrayList<Integer>> directedEdges = new ArrayList<>();
        ArrayList<ArrayList<Integer>> directedEdges2 = (ArrayList<ArrayList<Integer>>) directedEdges;

        ArrayList<ArrayList<Integer>> x = new ArrayList<>();
        List<ArrayList<Integer>> y = x;
    }
}
