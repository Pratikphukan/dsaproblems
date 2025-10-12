package com.dsaproblems.DSAProblems.graph00;

import java.util.*;

public class Batches {

    public static void main(String[] args) {
        int nodes = 7;
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(1, 2)));
        edges.add(new ArrayList<>(Arrays.asList(2, 3)));
        edges.add(new ArrayList<>(Arrays.asList(5, 6)));
        edges.add(new ArrayList<>(Arrays.asList(5, 7)));

        List<Integer> strengths = new ArrayList<>(List.of(1, 6, 7, 2, 9, 4, 5));
        int allowedStrength = 12;

        System.out.println(getBatchesv1(nodes, edges, strengths, allowedStrength));
        System.out.println(getBatchesv2(nodes, edges, strengths, allowedStrength));

    }

    //working code
    //Time Complexity (TC): Total: O(V + E)
    //Building the graph: O(E)
    //DFS traversal: Each node and edge is visited once, so O(V + E)
    //Space Complexity (SC): Total: O(V + E)
    //Graph adjacency list: O(V + E)
    //Visited array: O(V)
    //Stack for DFS: O(V) (in worst case)
    private static int getBatchesv2(int nodes, List<List<Integer>> edges, List<Integer> strengths, int allowedStrength) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= nodes; i++) {
            graph.putIfAbsent(i, new ArrayList<>()); // Initialize each node with an empty list, there might be nodes without edges
        }
        for (List<Integer> edge : edges) {
            graph.get(edge.get(0)).add(edge.get(1));
            graph.get(edge.get(1)).add(edge.get(0));
        }
        boolean[] visited = new boolean[nodes + 1];
        int count = 0;
        for (int node = 1; node <= nodes; node++) {
            if (!visited[node]) {
                //int sum = breadthFirstSearch(node, graph, visitedNodes, strengths);
                int sum = dfs(node, graph, visited, strengths);
                if (sum >= allowedStrength) {
                    count += 1;
                }
            }
        }
        return count;
    }

    private static int dfs(int start, Map<Integer, List<Integer>> graph, boolean[] visited, List<Integer> strengths) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(start);
        visited[start] = true;
        int sum = 0;
        while (!stack.isEmpty()) {
            int node = stack.removeFirst();
            sum += strengths.get(node - 1);
            for (int child : graph.get(node)) {
                if (!visited[child]) { // add returns true if the element was not already present
                    stack.addFirst(child);
                    visited[child] = true;
                }
            }
        }
        return sum;
    }

    //working code for BFS as well as DFS
    private static int getBatchesv1(int nodes, List<List<Integer>> edges, List<Integer> strengths, int allowedStrength) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= nodes; i++) {
            graph.putIfAbsent(i, new ArrayList<>()); // Initialize each node with an empty list, there might be nodes without edges
        }
        for (List<Integer> edge : edges) {
            int start = edge.get(0);
            int end = edge.get(1);
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
        Set<Integer> visitedNodes = new HashSet<>();
        int count = 0;
        for (int node = 1; node <= nodes; node++) {
            if (!visitedNodes.contains(node)) {
                //int sum = breadthFirstSearch(node, graph, visitedNodes, strengths);
                int sum = depthFirstSearch(node, graph, visitedNodes, strengths);
                if (sum >= allowedStrength) {
                    count += 1;
                }
            }
        }
        return count;
    }

    private static int breadthFirstSearch(int start, Map<Integer, List<Integer>> graph, Set<Integer> visitedNodes, List<Integer> strengths) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(start);
        visitedNodes.add(start);
        int sum = 0;
        while (!queue.isEmpty()) {
            Integer node = queue.pollFirst();
            sum += strengths.get(node - 1);
            for (Integer child : graph.get(node)) {
                if (visitedNodes.add(child)) { // add returns true if the element was not already present
                    queue.addLast(child);
                }

            }
        }
        return sum;
    }

    private static int depthFirstSearch(int start, Map<Integer, List<Integer>> graph, Set<Integer> visitedNodes, List<Integer> strengths) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(start);
        visitedNodes.add(start);
        int sum = 0;
        while (!stack.isEmpty()) {
            int node = stack.removeFirst();
            sum += strengths.get(node - 1);
            for (Integer child : graph.get(node)) {
                if (visitedNodes.add(child)) { // add returns true if the element was not already present
                    stack.addFirst(child);
                }
            }
        }
        return sum;
    }
}
