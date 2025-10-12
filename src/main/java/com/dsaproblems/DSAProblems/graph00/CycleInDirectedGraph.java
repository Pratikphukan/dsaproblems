package com.dsaproblems.DSAProblems.graph00;

import java.util.*;

public class CycleInDirectedGraph {

    //Nodes are numbered from 1 to A.
    public static void main(String[] args) {
        int nodes = 4;
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(1, 2)));
        edges.add(new ArrayList<>(Arrays.asList(4, 1)));
        edges.add(new ArrayList<>(Arrays.asList(2, 4)));
        edges.add(new ArrayList<>(Arrays.asList(3, 4)));
        //edges.add(new ArrayList<>(Arrays.asList(5, 2)));
        edges.add(new ArrayList<>(Arrays.asList(1, 3)));
        System.out.println(findCycleInDirectedGraphv1(nodes, edges));
        System.out.println(findCycleInDirectedGraphv2(nodes, edges));
    }

    //working code
    private static int findCycleInDirectedGraphv2(int nodes, List<List<Integer>> edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> incomingDegree = new HashMap<>();
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 1; i <= nodes; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (List<Integer> edge : edges) {
            int edgeStart = edge.get(0);
            int edgeEnd = edge.get(1);
            graph.get(edgeStart).add(edgeEnd);
            incomingDegree.put(edgeEnd, incomingDegree.getOrDefault(edgeEnd, 0) + 1);
        }
        for (int i = 1; i <= nodes; i++) {
            if (!incomingDegree.containsKey(i)) {
                minHeap.add(i);
            }
        }
        int processed = 0;
        while (!minHeap.isEmpty()) {
            int num = minHeap.poll();
            processed++;
            List<Integer> childList = graph.get(num);
            for (int child : childList) {
                incomingDegree.put(child, incomingDegree.get(child) - 1);
                if (incomingDegree.get(child) == 0) {
                    minHeap.add(child);
                }
            }
        }
        return processed < nodes ? 1 : 0;
    }

    //not working
    private static int findCycleInDirectedGraphv1(int nodes, List<List<Integer>> edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> edge : edges) {
            graph.get(edge.get(0)).add(edge.get(1));
        }
        Set<Integer> visitedNodes = new HashSet<>();
        for (int i = 1; i <= nodes; i++) {
            if (!graph.get(i).isEmpty() && !visitedNodes.contains(i)) {
                if (hasCycle(i, graph, visitedNodes)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    private static boolean hasCycle(int start, List<List<Integer>> graph, Set<Integer> visitedNodes) {
        Deque<List<Integer>> stack = new ArrayDeque<>();
        stack.addFirst(Arrays.asList(start, null));
        visitedNodes.add(start);
        while (!stack.isEmpty()) {
            List<Integer> node = stack.pollFirst();
            int currentNode = node.get(0);
            Integer parentNode = node.get(1);
            for (Integer child : graph.get(currentNode)) {
                if (visitedNodes.add(child)) {
                    stack.addFirst(Arrays.asList(child, currentNode));
                } else if (!child.equals(parentNode)) {
                    return true;
                }
            }
        }
        return false;
    }
}
