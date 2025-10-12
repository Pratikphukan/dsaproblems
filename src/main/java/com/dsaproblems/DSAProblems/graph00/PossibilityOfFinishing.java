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
        int A = 5;
        List<Integer> B = new ArrayList<>(Arrays.asList(1, 3, 4, 5));
        List<Integer> C = new ArrayList<>(Arrays.asList(2, 1, 5, 3));
        System.out.println(possibilityToTakeAllCoursesv1(A, B, C));
        System.out.println(possibilityToTakeAllCoursesv2(A, B, C));
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
