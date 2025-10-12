package com.dsaproblems.DSAProblems.graph00;

import java.util.*;

public class CheckCycleInUndirectedGraph {

    public static void main(String[] args) {

        List<List<Integer>> edges = new ArrayList<>();
        int nodes = 5;
        edges.add(new ArrayList<>(Arrays.asList(1, 2)));
        edges.add(new ArrayList<>(Arrays.asList(2, 3)));
        edges.add(new ArrayList<>(Arrays.asList(3, 4)));
        edges.add(new ArrayList<>(Arrays.asList(3, 5)));
        edges.add(new ArrayList<>(Arrays.asList(4, 5)));

//        int nodes = 7;
//        edges.add(new ArrayList<>(Arrays.asList(1, 2)));
//        edges.add(new ArrayList<>(Arrays.asList(2, 3)));
//        edges.add(new ArrayList<>(Arrays.asList(3, 1)));
//        edges.add(new ArrayList<>(Arrays.asList(2, 4)));
//        edges.add(new ArrayList<>(Arrays.asList(2, 5)));
//        edges.add(new ArrayList<>(Arrays.asList(2, 7)));
//        edges.add(new ArrayList<>(Arrays.asList(4, 6)));
//        edges.add(new ArrayList<>(Arrays.asList(5, 6)));
        System.out.println(checkCycleInUndirectedGraphv1(nodes, edges));
        System.out.println(checkCycleInUndirectedGraphv2(nodes, edges));
        System.out.println(checkCycleInUndirectedGraphv3(nodes, edges));
    }

    private static boolean checkCycleInUndirectedGraphv3(int nodes, List<List<Integer>> edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int totalEdges = 0;
        for (List<Integer> edge : edges) {
            int start = edge.get(0);
            int end = edge.get(1);
            graph.putIfAbsent(start, new ArrayList<>());
            graph.putIfAbsent(end, new ArrayList<>());
            graph.get(start).add(end);
            graph.get(end).add(start);
            totalEdges += 2;// each edge is counted twice in an undirected graph
        }
        Set<Integer> visitedNodes = new HashSet<>();
        int connectedComponents = 0;
        for (int node = 1; node <= nodes; node++) {
            if (!visitedNodes.contains(node)) {
                connectedComponents += 1;
                //breadthFirstSearch(node, graph, visitedNodes);
                depthFirstSearch(node, graph, visitedNodes);

            }
        }
        return (totalEdges / 2) > nodes - connectedComponents;
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

    //working code using BFS and DFS
    private static boolean checkCycleInUndirectedGraphv2(int nodes, List<List<Integer>> edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }
        int totalEdges = 0;
        for (List<Integer> edge : edges) {
            int start = edge.get(0);
            int end = edge.get(1);
            graph.get(start).add(end);
            graph.get(end).add(start);
            totalEdges += 2;// each edge is counted twice in an undirected graph
        }
        Set<Integer> visitedNodes = new HashSet<>();
        int connectedComponents = 0;
        for (int node = 1; node <= nodes; node++) {
            if (!visitedNodes.contains(node)) {
                connectedComponents += 1;
                //breadthFirstSearchv1(node, graph, visitedNodes);
                depthFirstSearchv1(node, graph, visitedNodes);
            }
        }
        return (totalEdges / 2) > nodes - connectedComponents;
    }

    private static void breadthFirstSearchv1(int start, List<List<Integer>> graph, Set<Integer> visitedNodes) {
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

    private static void depthFirstSearchv1(int start, List<List<Integer>> graph, Set<Integer> visitedNodes) {
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

    //working code using DFS and BFS
    private static boolean checkCycleInUndirectedGraphv1(int nodes, List<List<Integer>> edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> edge : edges) {
            int start = edge.get(0);
            int end = edge.get(1);
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
        Set<Integer> visitedNodes = new HashSet<>();
        for (int i = 1; i <= nodes; i++) {
            if (!graph.get(i).isEmpty() && !visitedNodes.contains(i)) {
//                if (depthFirstSearch(i, graph, visitedNodes)) {
//                    return true;
//                }
                if (breadthFirstSearch(i, graph, visitedNodes)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean breadthFirstSearch(int currNode, List<List<Integer>> graph, Set<Integer> visitedNodes) {
        Deque<List<Integer>> queue = new ArrayDeque<>();
        queue.addLast(Arrays.asList(null, currNode));
        visitedNodes.add(currNode);
        Integer prev, curr;
        while (!queue.isEmpty()) {
            List<Integer> node = queue.pollFirst();
            prev = node.get(0);
            curr = node.get(1);
            List<Integer> childList = graph.get(curr);
            for (Integer child : childList) {
                if (visitedNodes.add(child)) {
                    queue.addLast(Arrays.asList(curr, child)); // [previous node, current node]
                } else if (!child.equals(prev)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean depthFirstSearch(int currNode, List<List<Integer>> graph, Set<Integer> visitedNodes) {
        Deque<List<Integer>> stack = new ArrayDeque<>();
        stack.addFirst(Arrays.asList(null, currNode));
        visitedNodes.add(currNode);
        Integer prev, curr;
        while (!stack.isEmpty()) {
            List<Integer> node = stack.removeFirst();
            prev = node.get(0);
            curr = node.get(1);
            List<Integer> childList = graph.get(curr);
            for (Integer child : childList) {
                if (visitedNodes.add(child)) {
                    stack.addFirst(Arrays.asList(curr, child)); // [previous node, current node]
                } else if (!child.equals(prev)) {
                    return true;
                }
            }
        }
        return false;
    }

}
