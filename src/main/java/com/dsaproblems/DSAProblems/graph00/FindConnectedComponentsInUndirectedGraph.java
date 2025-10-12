package com.dsaproblems.DSAProblems.graph00;

import java.util.*;

public class FindConnectedComponentsInUndirectedGraph {

    public static void main(String[] args) {
        int nodes = 10;
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(0, 1)));
        edges.add(new ArrayList<>(Arrays.asList(1, 2)));
        edges.add(new ArrayList<>(Arrays.asList(2, 3)));
        edges.add(new ArrayList<>(Arrays.asList(4, 5)));
        edges.add(new ArrayList<>(Arrays.asList(6, 7)));
        edges.add(new ArrayList<>(Arrays.asList(7, 8)));
        edges.add(new ArrayList<>(Arrays.asList(7, 9)));
        System.out.println(findConnectedComponentsInUndirectedGraphv1(nodes, edges));
        System.out.println(findConnectedComponentsInUndirectedGraphv2(nodes, edges));
    }

    private static int findConnectedComponentsInUndirectedGraphv2(int nodes, List<List<Integer>> edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < nodes; i++) {
            graph.putIfAbsent(i, new ArrayList<>()); // Initialize each node with an empty list, there might be nodes without edges
        }
        for (List<Integer> edge : edges) {
            int start = edge.get(0);
            int end = edge.get(1);
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
        Set<Integer> visitedNodes = new HashSet<>();
        int connectedComponents = 0;
        for (int node = 0; node < nodes; node++) {
            if (!visitedNodes.contains(node)) {
                connectedComponents += 1;
                breadthFirstSearch(node, graph, visitedNodes);
                //depthFirstSearch(node, graph, visitedNodes);

            }
        }
        return connectedComponents;
    }

    private static void depthFirstSearch(int start, Map<Integer, List<Integer>> graph, Set<Integer> visitedNodes) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(start);
        visitedNodes.add(start);
        while (!stack.isEmpty()) {
            int node = stack.removeFirst();
            for (Integer child : graph.get(node)) {
                if (visitedNodes.add(child)) { // add returns true if the element was not already present
                    stack.addFirst(child);
                }
            }
        }
    }

    private static void breadthFirstSearch(int start, Map<Integer, List<Integer>> graph, Set<Integer> visitedNodes) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(start);
        visitedNodes.add(start);
        while (!queue.isEmpty()) {
            Integer node = queue.pollFirst();
            for (Integer child : graph.get(node)) {
                if (visitedNodes.add(child)) { // add returns true if the element was not already present
                    queue.addLast(child);
                }

            }
        }
    }

    private static int findConnectedComponentsInUndirectedGraphv1(int nodes, List<List<Integer>> edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> edge : edges) {
            int start = edge.get(0);
            int end = edge.get(1);
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
        Set<Integer> visitedNodes = new HashSet<>();
        int connectedComponents = 0;
        for (int node = 0; node < nodes; node++) {
            if (!visitedNodes.contains(node)) {
                connectedComponents += 1;
                //breadthFirstSearch(node, graph, visitedNodes);
                depthFirstSearch(node, graph, visitedNodes);
            }
        }
        return connectedComponents;
    }

    private static void breadthFirstSearch(int start, List<List<Integer>> graph, Set<Integer> visitedNodes) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(start);
        visitedNodes.add(start);
        while (!queue.isEmpty()) {
            Integer node = queue.pollFirst();
            for (Integer child : graph.get(node)) {
                if (visitedNodes.add(child)) { // add returns true if the element was not already present
                    queue.addLast(child);
                }

            }
        }
    }

    private static void depthFirstSearch(int start, List<List<Integer>> graph, Set<Integer> visitedNodes) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(start);
        visitedNodes.add(start);
        while (!stack.isEmpty()) {
            int node = stack.removeFirst();
            for (Integer child : graph.get(node)) {
                if (visitedNodes.add(child)) { // add returns true if the element was not already present
                    stack.addFirst(child);
                }
            }
        }
    }

}
