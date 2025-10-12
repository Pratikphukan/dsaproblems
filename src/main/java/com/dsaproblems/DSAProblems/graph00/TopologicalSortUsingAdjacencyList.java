package com.dsaproblems.DSAProblems.graph00;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopologicalSortUsingAdjacencyList {

    public static void main(String[] args) {
        int nodes = 5;
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(0, 1)));
        edges.add(new ArrayList<>(Arrays.asList(0, 2)));
        edges.add(new ArrayList<>(Arrays.asList(1, 3)));
        edges.add(new ArrayList<>(Arrays.asList(1, 4)));
        edges.add(new ArrayList<>(Arrays.asList(2, 4)));
        edges.add(new ArrayList<>(Arrays.asList(3, 5)));
        edges.add(new ArrayList<>(Arrays.asList(4, 5)));
        System.out.println(topologicalSort(nodes, edges));

        nodes = 6;
        List<List<Integer>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(6, 3)));
        input.add(new ArrayList<>(Arrays.asList(6, 1)));
        input.add(new ArrayList<>(Arrays.asList(5, 1)));
        input.add(new ArrayList<>(Arrays.asList(5, 2)));
        input.add(new ArrayList<>(Arrays.asList(3, 4)));
        input.add(new ArrayList<>(Arrays.asList(4, 2)));
        System.out.println(topologicalSortv1(nodes, input));

        // [[],[],[],[],[],[],[],[],[]], 8
        nodes = 8;
        edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(1, 4)));
        edges.add(new ArrayList<>(Arrays.asList(1, 2)));
        edges.add(new ArrayList<>(Arrays.asList(4, 2)));
        edges.add(new ArrayList<>(Arrays.asList(4, 3)));
        edges.add(new ArrayList<>(Arrays.asList(3, 2)));
        edges.add(new ArrayList<>(Arrays.asList(5, 2)));
        edges.add(new ArrayList<>(Arrays.asList(3, 5)));
        edges.add(new ArrayList<>(Arrays.asList(8, 2)));
        edges.add(new ArrayList<>(Arrays.asList(8, 6)));
        System.out.println(topologicalSortv1(nodes, edges));
        System.out.println(topologicalSortv4(nodes, edges));

        nodes = 5;
        edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(5, 4)));
        edges.add(new ArrayList<>(Arrays.asList(4, 3)));
        edges.add(new ArrayList<>(Arrays.asList(3, 2)));
        edges.add(new ArrayList<>(Arrays.asList(2, 1)));
        System.out.println(topologicalSortv2(nodes, edges));
        System.out.println(topologicalSortv3(nodes, edges));
        System.out.println(topologicalSortv4(nodes, edges));
    }

    //working code
    //Time Complexity (TC): O(V + E * log V)
    //Building the graph: O(E)
    //Initializing nodes: O(V)
    //Each heap operation (add/poll): O(log V) per node, up to O(E) times
    //Space Complexity (SC): O(V + E)
    //Graph and incomingDegree maps: O(V + E)
    //Min-heap and result list: O(V)
    private static List<Integer> topologicalSortv4(int nodes, List<List<Integer>> edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();//In Java, primitive arrays (like int[], double[]) have a fixed size once created. You cannot directly declare a truly dynamic primitive array
        Map<Integer, Integer> incomingDegree = new HashMap<>();
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 1; i <= nodes; i++) { //Initializing nodes is O(V) because you loop through all V nodes (from 1 to nodes) and perform a constant-time operation for each
            graph.put(i, new ArrayList<>());
        }
        for (List<Integer> edge : edges) { //Building the graph is O(E) because each edge in the input list is processed exactly once
            int edgeStart = edge.get(0);
            int edgeEnd = edge.get(1);
            graph.get(edgeStart).add(edgeEnd);
            incomingDegree.put(edgeEnd, incomingDegree.getOrDefault(edgeEnd, 0) + 1);
        }
        for (int i = nodes; i > 0; i--) {
            if (!incomingDegree.containsKey(i)) {
                minHeap.add(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            int num = minHeap.poll();
            result.add(num);
            List<Integer> childList = graph.get(num);
            for (int child : childList) {
                incomingDegree.put(child, incomingDegree.get(child) - 1);
                if (incomingDegree.get(child) == 0) {
                    minHeap.add(child);
                }
            }
        }
        return result;
    }

    //working code
    private static List<Integer> topologicalSortv3(int nodes, List<List<Integer>> edges) {
        List<List<Integer>> graph = new ArrayList<>();
        Map<Integer, Integer> incomingDegree = new HashMap<>();
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
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
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            int num = minHeap.poll();
            result.add(num);
            List<Integer> childList = graph.get(num);
            for (int child : childList) {
                incomingDegree.put(child, incomingDegree.get(child) - 1);
                if (incomingDegree.get(child) == 0) {
                    minHeap.add(child);
                }
            }
        }
        return result;
    }

    //working code
    private static List<Integer> topologicalSortv2(int nodes, List<List<Integer>> edges) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] incomingDegree = new int[nodes + 1];
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> edge : edges) {
            graph.get(edge.get(0)).add(edge.get(1));
            incomingDegree[edge.get(1)]++;
        }
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 1; i < incomingDegree.length; i++) {
            if (incomingDegree[i] == 0) {
                minHeap.add(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            int num = minHeap.poll();
            result.add(num);
            List<Integer> children = graph.get(num);
            for (Integer child : children) {
                incomingDegree[child]--;
                if (incomingDegree[child] == 0) {
                    minHeap.add(child);
                }
            }
        }
        return result;
    }

    //not working completely
    private static List<Integer> topologicalSortv1(int nodes, List<List<Integer>> edges) {
        List<Queue<Integer>> adjList = new ArrayList<>();
        Map<Integer, Integer> incomingDegree = new HashMap<>();
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i <= nodes; i++) {
            adjList.add(new PriorityQueue<>());
        }
        int edgeStart = 0;
        int edgeEnd = 0;
        for (int i = 0; i < edges.size(); i++) {
            edgeStart = edges.get(i).get(0);
            edgeEnd = edges.get(i).get(1);
            adjList.get(edgeStart).add(edgeEnd);
            incomingDegree.put(edgeEnd, incomingDegree.getOrDefault(edgeEnd, 0) + 1);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= nodes; i++) {
            if (!incomingDegree.containsKey(i)) {
                queue.addLast(i);
                while (!queue.isEmpty()) {
                    Integer node = queue.pollFirst();
                    result.add(node);
                    Queue<Integer> childList = adjList.get(node);
                    while (!childList.isEmpty()) {
                        int child = childList.poll();
                        incomingDegree.put(child, incomingDegree.get(child) - 1);
                        if (incomingDegree.get(child) == 0) {
                            queue.addLast(child);
                        }
                    }
                }
            }
        }
        if (result.size() != nodes) {
            return new ArrayList<>();
        }
        return result;
    }

    private static List<Integer> topologicalSort(int nodes, List<List<Integer>> edges) {
        List<List<Integer>> adjList = new ArrayList<>();
        Map<Integer, Integer> incomingDegree = new HashMap<>();
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i <= nodes; i++) {
            adjList.add(new ArrayList<>());
        }
        int edgeStart = 0;
        int edgeEnd = 0;
        for (int i = 0; i < edges.size(); i++) {
            edgeStart = edges.get(i).get(0);
            edgeEnd = edges.get(i).get(1);
            adjList.get(edgeStart).add(edgeEnd);
            incomingDegree.put(edgeEnd, incomingDegree.getOrDefault(edgeEnd, 0) + 1);
        }
        for (int i = 0; i <= nodes; i++) {
            if (!incomingDegree.containsKey(i)) {
                queue.addLast(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer node = queue.pollFirst();
            result.add(node);
            List<Integer> childList = adjList.get(node);
            for (Integer child : childList) {
                incomingDegree.put(child, incomingDegree.get(child) - 1);
                if (incomingDegree.get(child) == 0) {
                    queue.addLast(child);
                }
            }
        }
        if (result.size() != (nodes + 1)) {
            System.out.println("Cycle is there in graph");
            return new ArrayList<>();
        }
        return result;
    }

}
