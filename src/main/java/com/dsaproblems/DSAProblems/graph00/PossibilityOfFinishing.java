package com.dsaproblems.DSAProblems.graph00;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class PossibilityOfFinishing {

    @Getter
    static class Node {

        private int data;

        private List<Node> childNodes;

        @Setter
        private boolean isVisited;

        public Node(int data) {
            this.data = data;
            this.childNodes = new ArrayList<>();
            this.isVisited = false;
        }

        public void addChild(Node child) {
            this.childNodes.add(child);
        }
    }

    public static void main(String[] args) {
        //8 | 1, 2, 2, 3, 5, 5, 6, 4 | 3, 3, 4, 5, 8, 6, 7, 6
        //2 | 1, 2 | 2, 1
        //5 | 1, 3, 4, 5 | 2, 1, 5, 3
        //4 | 1, 1, 2, 3 | 2, 3, 4, 4
        int A = 4;
        List<Integer> B = new ArrayList<>(Arrays.asList(1, 1, 2, 3));
        List<Integer> C = new ArrayList<>(Arrays.asList(2, 3, 4, 4));
        System.out.println(possibilityToTakeAllCoursesv1(A, B, C));
        System.out.println(possibilityToTakeAllCoursesv2(A, B, C));
        System.out.println(possibilityToTakeAllCoursesv3(A, B, C));
        System.out.println(Arrays.toString(possibilityToTakeAllCoursesv4(A, B, C)));
    }

    //working code, returning an array of the sequence of courses
    //Building the adjacency lists and incoming-degree array costs O(N + E)
    private static int[] possibilityToTakeAllCoursesv4(int nodes, List<Integer> B, List<Integer> C) {
        List<Integer>[] graph = new ArrayList[nodes + 1];
        int[] result = new int[nodes];
        for (int i = 1; i <= nodes; i++) graph[i] = new ArrayList<>();//create an empty list for each node: O(N)
        int[] incomingDegree = new int[nodes + 1];
        //Iterate the edge lists (size E = B.size()):
        // for each edge do two O(1) operations — append to the node's adjacency
        // list (amortized O(1) for ArrayList) and increment incomingDegree[end]
        // (array access O(1)) — total O(E)
        for (int i = 0; i < B.size(); i++) {
            int start = B.get(i), end = C.get(i);
            graph[start].add(end);
            incomingDegree[end]++;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= nodes; i++) {
            if (incomingDegree[i] == 0) queue.addLast(i);
        }
        int processed = 0, i = 0;
        //Kahn's loop visits each node once and processes each edge once, adding another O(N + E)
        while (!queue.isEmpty()) {
            int start = queue.pollFirst();
            processed++;
            result[i++] = start;
            for (int end : graph[start]) {
                incomingDegree[end]--;
                if (incomingDegree[end] == 0) queue.addLast(end);
            }
        }
        return processed == nodes ? result : new int[0];
    }

    //working code
    private static int possibilityToTakeAllCoursesv3(int nodes, List<Integer> B, List<Integer> C) {
        List<Integer>[] graph = new ArrayList[nodes + 1];
        for (int i = 1; i <= nodes; i++) graph[i] = new ArrayList<>();
        int[] incomingDegree = new int[nodes + 1];
        for (int i = 0; i < B.size(); i++) {
            int start = B.get(i), end = C.get(i);
            graph[start].add(end);
            incomingDegree[end]++;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= nodes; i++) {
            if (incomingDegree[i] == 0) queue.addLast(i);
        }
        int processed = 0;
        while (!queue.isEmpty()) {
            int start = queue.pollFirst();
            processed++;
            for (int end : graph[start]) {
                incomingDegree[end]--;
                if (incomingDegree[end] == 0) queue.addLast(end);
            }
        }
        return processed == nodes ? 1 : 0;
    }

    //working code
    private static int possibilityToTakeAllCoursesv2(int nodes, List<Integer> B, List<Integer> C) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> incomingDegree = new HashMap<>();
        Queue<Integer> minHeap = new PriorityQueue<>();

        // Initialize graph and incoming degrees
        for (int i = 1; i <= nodes; i++) {
            graph.put(i, new ArrayList<>());
            incomingDegree.put(i, 0);
        }

        // Build graph and calculate incoming degrees
        for (int i = 0; i < B.size(); i++) {
            int edgeStart = B.get(i);
            int edgeEnd = C.get(i);
            graph.get(edgeStart).add(edgeEnd); //directed edge from edgeStart to edgeEnd
            incomingDegree.put(edgeEnd, incomingDegree.get(edgeEnd) + 1);
        }

        // Add nodes with zero incoming degree to the heap
        for (int i = 1; i <= nodes; i++) {
            if (incomingDegree.get(i) == 0) {
                incomingDegree.remove(i);
                minHeap.add(i);
            }
        }

        // Perform topological sort
        while (!minHeap.isEmpty()) {
            int num = minHeap.poll();
            for (int child : graph.get(num)) {
                incomingDegree.put(child, incomingDegree.get(child) - 1);
                if (incomingDegree.get(child) == 0) {
                    incomingDegree.remove(child);
                    minHeap.add(child);
                }
            }
        }

        return incomingDegree.isEmpty() ? 1 : 0;
    }

    //working code
    private static int possibilityToTakeAllCoursesv1(int nodes, List<Integer> B, List<Integer> C) {
        List<List<Integer>> graph = new ArrayList<>();
        Map<Integer, Integer> incomingDegree = new HashMap<>();
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < B.size(); i++) {
            int edgeStart = B.get(i);
            int edgeEnd = C.get(i);
            graph.get(edgeStart).add(edgeEnd);
            incomingDegree.put(edgeEnd, incomingDegree.getOrDefault(edgeEnd, 0) + 1);
        }
        for (int i = 1; i <= nodes; i++) {
            if (!incomingDegree.containsKey(i)) {
                minHeap.add(i);
            }
        }
        while (!minHeap.isEmpty()) {
            int num = minHeap.poll();
            List<Integer> childList = graph.get(num);
            for (int child : childList) {
                incomingDegree.put(child, incomingDegree.get(child) - 1);
                if (incomingDegree.get(child) == 0) {
                    incomingDegree.remove(child);
                    minHeap.add(child);
                }
            }
        }
        return incomingDegree.isEmpty() ? 1 : 0;
    }

}
