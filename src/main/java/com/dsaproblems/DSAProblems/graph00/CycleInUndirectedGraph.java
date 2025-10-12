package com.dsaproblems.DSAProblems.graph00;

import java.util.*;

public class CycleInUndirectedGraph {

    public static void main(String[] args) {
        int nodes = 5;
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(1, 2)));
        edges.add(new ArrayList<>(Arrays.asList(1, 3)));
        edges.add(new ArrayList<>(Arrays.asList(2, 3)));
        edges.add(new ArrayList<>(Arrays.asList(1, 4)));
        edges.add(new ArrayList<>(Arrays.asList(4, 5)));
        System.out.println(findCycleInUndirectedGraphv1(nodes, edges));
        System.out.println(findCycleInUndirectedGraphv2(nodes, edges));

        System.out.println(findCycleInUndirectedGraphv3(nodes, edges));
    }

    //will work for a connected as well as a disconnected graph
    private static boolean findCycleInUndirectedGraphv3(int nodes, List<List<Integer>> edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> edge : edges) {
            graph.get(edge.get(0)).add(edge.get(1));
            graph.get(edge.get(1)).add(edge.get(0));
        }
        Set<Integer> visitedNodes = new HashSet<>();
        for (int i = 1; i <= nodes; i++) {
            if (!graph.get(i).isEmpty() && !visitedNodes.contains(i)) {
                if (hasCycle(i, graph, visitedNodes)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasCycle(int start, List<List<Integer>> graph, Set<Integer> visitedNodes) {
        Deque<List<Integer>> stack = new ArrayDeque<>();
        stack.addFirst(Arrays.asList(start, null));
        while (!stack.isEmpty()) {
            List<Integer> node = stack.pollFirst();
            int currentNode = node.get(0);
            Integer parentNode = node.get(1);
            if (!visitedNodes.add(currentNode)) continue;
            for (Integer child : graph.get(currentNode)) {
                if (!visitedNodes.contains(child)) {
                    stack.addFirst(Arrays.asList(child, currentNode));
                } else if (!child.equals(parentNode)) {
                    return true;
                }
            }
        }
        return false;
    }

    //works well when it is a disconnected graph
    private static boolean findCycleInUndirectedGraphv2(int nodes, List<List<Integer>> edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> edge : edges) {
            graph.get(edge.get(0)).add(edge.get(1));
            graph.get(edge.get(1)).add(edge.get(0));
        }
        Set<Integer> visitedNodes = new HashSet<>();
        Deque<List<Integer>> stack = new ArrayDeque<>();
        stack.addFirst(Arrays.asList(5, null)); // Start with node 1 and no parent
        Integer parentNode = null;
        Integer currentNode = null;
        while (!stack.isEmpty()) {
            List<Integer> node = stack.pollFirst();
            currentNode = node.get(0);
            parentNode = node.get(1);
            if (!visitedNodes.add(currentNode))
                continue; //we don't add the child nodes to the visitedNodes set, we add the current node and process the cild nodes
            List<Integer> childList = graph.get(currentNode);
            for (Integer child : childList) {
                if (!visitedNodes.contains(child)) {
                    stack.addFirst(Arrays.asList(child, currentNode));
                } else if (!child.equals(parentNode)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean findCycleInUndirectedGraphv1(int nodes, List<List<Integer>> edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> edge : edges) {
            graph.get(edge.get(0)).add(edge.get(1));
            graph.get(edge.get(1)).add(edge.get(0));
        }
        Set<Integer> visitedNodes = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(1);
        visitedNodes.add(1);
        Integer parentNode = null;
        while (!stack.isEmpty()) {
            Integer node = stack.pollFirst();
            List<Integer> childList = graph.get(node);
            for (Integer child : childList) {
                if (visitedNodes.add(child)) {
                    stack.addFirst(child);
                } else {
                    if (!child.equals(parentNode))
                        return true;
                }
            }
            parentNode = node;
        }
        return false;
    }

    private static void depthFirstSearch(int start, List<List<Integer>> graph, Set<Integer> visitedNodes) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(start);
        visitedNodes.add(start);
        while (!stack.isEmpty()) {
            Integer node = stack.removeFirst();
            List<Integer> childList = graph.get(node);
            for (Integer child : childList) {
                if (!visitedNodes.contains(child)) {
                    stack.addFirst(child);
                    visitedNodes.add(child);
                }
            }
        }
    }

}
