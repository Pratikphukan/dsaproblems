package com.dsaproblems.DSAProblems.graph00;

import java.util.*;


/*
This refers to the maximum number of edges a graph can have based on its type (undirected or directed) and the number of vertices ( V ):
Undirected Graph:
In an undirected graph, each edge connects two vertices, and edges are not directional.
The maximum number of edges occurs when every vertex is connected to every other vertex (a complete graph).
The formula for the maximum edges is ( V*(V - 1) / 2 ), which accounts for the fact that each pair of vertices forms one edge,
and edges are not duplicated.

Directed Graph:
In a directed graph, edges have a direction (from one vertex to another).
The maximum number of edges occurs when every vertex has a directed edge to every other vertex.
The formula for the maximum edges is ( V*(V - 1) ), as each vertex can have a directed edge to ( V - 1 ) other vertices.
 */
public class FirstDepthFirstSearch {

    public static void main(String[] args) {
        int B = 2;
        int C = 8;
        List<Integer> directedEdges = new ArrayList<>(Arrays.asList(1, 1, 1, 3, 3, 2, 2, 7, 6));
        System.out.println(checkBIsReachableFromCv1(directedEdges, B, C));
        System.out.println(checkBIsReachableFromCv2(directedEdges, B, C));
        System.out.println(checkBIsReachableFromCv3(directedEdges, B, C));
    }

    //also working code using BFS
    private static int checkBIsReachableFromCv3(List<Integer> directedEdges, int B, int C) {
        if (B == C) return 1; // Early exit if source equals destination
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i < directedEdges.size(); i++) {
            graph.computeIfAbsent(directedEdges.get(i), k -> new ArrayList<>()).add(i + 1);
        }
        Set<Integer> visitedNodes = new HashSet<>();
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(C);
        visitedNodes.add(C);
        while (!queue.isEmpty()) {
            Integer node = queue.pollFirst();
            if (node.equals(B)) {
                return 1;
            }
            List<Integer> childList = graph.getOrDefault(node, Collections.emptyList());
            for (Integer child : childList) {
                if (visitedNodes.add(child)) { // add returns false if already present
                    if (child.equals(B)) {
                        return 1;
                    }
                    queue.addLast(child);
                }
            }
        }
        return 0;
    }

    //working code using DFS
    private static int checkBIsReachableFromCv2(List<Integer> directedEdges, int B, int C) {
        if (B == C) return 1; // Early exit if source equals destination
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i < directedEdges.size(); i++) {
            graph.computeIfAbsent(directedEdges.get(i), k -> new ArrayList<>()).add(i + 1);
        }
        Set<Integer> visitedNodes = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(C);
        visitedNodes.add(C);
        while (!stack.isEmpty()) {
            Integer node = stack.pollFirst();
            if (node.equals(B)) {
                return 1;
            }
            List<Integer> childList = graph.getOrDefault(node, Collections.emptyList());
            for (Integer child : childList) {
                if (visitedNodes.add(child)) { // add returns false if already present
                    if (child.equals(B)) {
                        return 1;
                    }
                    stack.addFirst(child);
                }
            }
        }
        return 0;
    }

    // solution is derived using DFS, working
    private static int checkBIsReachableFromCv1(List<Integer> directedEdges, int B, int C) {
        if (B == C) return 1; // Early exit if source equals destination
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= directedEdges.size(); i++) { //The graph is initialized with directedEdges.size() + 1 empty lists, which is wasteful if many nodes are unused.
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < directedEdges.size(); i++) {
            graph.get(directedEdges.get(i)).add(i + 1);
        }
        Set<Integer> visitedNodes = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(C);
        visitedNodes.add(C);
        while (!stack.isEmpty()) {
            Integer node = stack.pollFirst();
            if (node.equals(B)) {
                return 1;
            }
            List<Integer> childList = graph.get(node);
            for (Integer child : childList) {
                if (visitedNodes.add(child)) { // add returns false if already present
                    if (child.equals(B)) {
                        return 1;
                    }
                    stack.addFirst(child);

                }
            }
        }
        return 0;
    }

}
