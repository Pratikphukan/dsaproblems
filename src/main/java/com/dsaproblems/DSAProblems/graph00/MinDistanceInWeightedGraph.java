package com.dsaproblems.DSAProblems.graph00;

import java.util.*;

public class MinDistanceInWeightedGraph {

    static class Edge {
        Node childNode;
        int distanceFromNode;

        public Edge(Node childNode, int distanceFromNode) {
            this.childNode = childNode;
            this.distanceFromNode = distanceFromNode;
        }
    }

    static class Node {
        int value;

        int minDistance;

        boolean isVisited;

        List<Edge> edges;

        public Node(int value) {
            this.value = value;
            this.minDistance = Integer.MAX_VALUE; // Initialize with max value
            this.edges = new ArrayList<>();
        }

        public void addChild(Node child, int distanceFromNode) {
            this.edges.add(new Edge(child, distanceFromNode)); // Assuming distance is 1 for simplicity
        }
    }

    public static void main(String[] args) {
        int nodes = 6;
        int source = 0;
        int destination = 4;
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(0, 4, 9)));
        edges.add(new ArrayList<>(Arrays.asList(3, 4, 6)));
        edges.add(new ArrayList<>(Arrays.asList(1, 2, 1)));
        edges.add(new ArrayList<>(Arrays.asList(2, 5, 1)));
        edges.add(new ArrayList<>(Arrays.asList(2, 4, 5)));
        edges.add(new ArrayList<>(Arrays.asList(0, 3, 7)));
        edges.add(new ArrayList<>(Arrays.asList(0, 1, 1)));
        edges.add(new ArrayList<>(Arrays.asList(4, 5, 7)));
        edges.add(new ArrayList<>(Arrays.asList(0, 5, 1)));

        System.out.println(minimumDistanceBetweenTwoNodesv1(nodes, edges, source, destination));
        System.out.println(minimumDistanceBetweenTwoNodesv2(nodes, edges, source, destination));
    }

    //wronng solution
    private static int minimumDistanceBetweenTwoNodesv2(int nodes, List<List<Integer>> edges, int source, int destination) {
        Map<Integer, Node> graph = new HashMap<>();
        for (int i = 0; i < nodes; i++) {
            graph.put(i, new Node(i));
        }
        for (List<Integer> edge : edges) {
            graph.get(edge.get(0)).addChild(graph.get(edge.get(1)), edge.get(2));
            graph.get(edge.get(1)).addChild(graph.get(edge.get(0)), edge.get(2));
        }
        if (source == destination) return 0; // Early exit
        Deque<Node> queue = new ArrayDeque<>();
        Node sourceNode = graph.get(source);
        sourceNode.minDistance = 0; // Distance from source to itself is 0
        sourceNode.isVisited = true;
        queue.addLast(sourceNode);
        while (!queue.isEmpty()) {
            Node currentNode = queue.pollFirst();
            if (currentNode.value == destination) {
                return currentNode.minDistance; // Found a path to the destination node
            }
            for (Edge edge : currentNode.edges) {
                Node childNode = edge.childNode;
                if (!childNode.isVisited && currentNode.minDistance + edge.distanceFromNode < childNode.minDistance) {
                    childNode.minDistance = currentNode.minDistance + edge.distanceFromNode;
                    childNode.isVisited = true;
                    queue.addLast(childNode);
                }
            }
        }
        return -1;
    }

    private static int minimumDistanceBetweenTwoNodesv1(int nodes, List<List<Integer>> edges, int source, int destination) {
        Map<Integer, Node> graph = new HashMap<>();
        for (int i = 0; i < nodes; i++) {
            graph.put(i, new Node(i));
        }
        for (List<Integer> edge : edges) {
            graph.get(edge.get(0)).addChild(graph.get(edge.get(1)), edge.get(2));
            graph.get(edge.get(1)).addChild(graph.get(edge.get(0)), edge.get(2));
        }
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(n -> n.minDistance));
        Node sourceNode = graph.get(source);
        sourceNode.minDistance = 0;
        minHeap.add(sourceNode);

        while (!minHeap.isEmpty()) {
            Node currentNode = minHeap.poll();
            if (currentNode.isVisited) continue;
            currentNode.isVisited = true;
            if (currentNode.value == destination) return currentNode.minDistance;
            for (Edge edge : currentNode.edges) {
                Node childNode = edge.childNode;
                int newDist = currentNode.minDistance + edge.distanceFromNode;
                if (newDist < childNode.minDistance) {
                    childNode.minDistance = newDist;
                    minHeap.add(childNode);
                }
            }
        }
        return -1;
    }
}
