package com.dsaproblems.DSAProblems.graph00;

import java.util.*;

public class Dijkstra {
    static class Edge<T> {
        T childNode;
        int distanceFromNode;

        public Edge(T childNode, int distanceFromNode) {
            this.childNode = childNode;
            this.distanceFromNode = distanceFromNode;
        }

        public Edge() {
            this.childNode = null;
            this.distanceFromNode = 0; // Default distance
        }
    }

    static class Node {
        int value;

        int minDistance;

        List<Edge<Node>> edges;

        public Node(int value) {
            this.value = value;
            this.minDistance = Integer.MAX_VALUE; // Initialize with max value
            this.edges = new ArrayList<>();
        }

        public void addChild(Node child, int distanceFromNode) {
            this.edges.add(new Edge<Node>(child, distanceFromNode)); // Assuming distance is 1 for simplicity
        }
    }

    public static void main(String[] args) {
        int nodes = 6;
        int source = 4;
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

        System.out.println(minimumDistanceBetweenTwoNodesv1(nodes, edges, source));
        System.out.println(minimumDistanceBetweenTwoNodesv2(nodes, edges, source));
        System.out.println(minimumDistanceBetweenTwoNodesv3(nodes, edges, source));
        System.out.println(minimumDistanceBetweenTwoNodesv4(nodes, edges, source));
    }

    private static ArrayList<Integer> minimumDistanceBetweenTwoNodesv4(int nodes, List<List<Integer>> B, int source) {
        Map<Integer, List<Edge<Integer>>> graph = new HashMap<>();
        ArrayList<Integer> distances = new ArrayList<>(Collections.nCopies(nodes, Integer.MAX_VALUE));
        for (int i = 0; i < nodes; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (List<Integer> edge : B) {
            graph.get(edge.get(0)).add(new Edge<>(edge.get(1), edge.get(2)));
            graph.get(edge.get(1)).add(new Edge<>(edge.get(0), edge.get(2)));
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        distances.set(source, 0);
        minHeap.add(new int[]{source, 0});

        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int v = curr[0];
            int dist = curr[1];
            if (dist > distances.get(v)) continue;
            for (Edge<Integer> edge : graph.get(v)) {
                int childNode = edge.childNode;
                int newDist = dist + edge.distanceFromNode;
                if (newDist < distances.get(childNode)) {
                    distances.set(childNode, newDist);
                    minHeap.add(new int[]{childNode, newDist});
                }
            }
        }
        for (int i = 0; i < nodes; i++) {
            if (distances.get(i) == Integer.MAX_VALUE)
                distances.set(i, -1);
        }
        return distances;
    }

    private static ArrayList<Integer> minimumDistanceBetweenTwoNodesv3(int nodes, List<List<Integer>> B, int source) {
        Map<Integer, List<Edge<Integer>>> graph = new HashMap<>();
        ArrayList<Integer> distances = new ArrayList<>(Collections.nCopies(nodes, Integer.MAX_VALUE));
        for (int i = 0; i < nodes; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (List<Integer> edge : B) {
            graph.get(edge.get(0)).add(new Edge<>(edge.get(1), edge.get(2)));
            graph.get(edge.get(1)).add(new Edge<>(edge.get(0), edge.get(2)));
        }
        boolean[] visited = new boolean[nodes];
        Queue<Edge<Integer>> minHeap = new PriorityQueue<>((e1, e2) -> e1.distanceFromNode - e2.distanceFromNode);

        distances.set(source, 0);
        minHeap.add(new Edge<>(source, 0));

        while (!minHeap.isEmpty()) {
            int v = minHeap.poll().childNode;
            if (visited[v])
                continue;

            visited[v] = true;

            List<Edge<Integer>> edges = graph.get(v);

            for (Edge<Integer> edge : edges) {
                int dist = edge.distanceFromNode;
                int childNode = edge.childNode;

                if (!visited[childNode] && (distances.get(v) + dist < distances.get(childNode))) {
                    distances.set(childNode, distances.get(v) + dist);
                    edge.distanceFromNode = distances.get(v) + dist;
                    minHeap.add(edge);
                }
            }
        }
        for (int i = 0; i < nodes; i++) {
            if (distances.get(i) == Integer.MAX_VALUE)
                distances.set(i, -1);
        }
        return distances;
    }

    //working code for all cases, using BFS
    private static ArrayList<Integer> minimumDistanceBetweenTwoNodesv2(int nodes, List<List<Integer>> edges, int source) {
        Map<Integer, Node> graph = new HashMap<>();
        for (int i = 0; i < nodes; i++) {
            graph.put(i, new Node(i));
        }
        for (List<Integer> edge : edges) {
            graph.get(edge.get(0)).addChild(graph.get(edge.get(1)), edge.get(2));
            graph.get(edge.get(1)).addChild(graph.get(edge.get(0)), edge.get(2));
        }
        Deque<Node> queue = new ArrayDeque<>();
        Node sourceNode = graph.get(source);
        sourceNode.minDistance = 0;
        queue.addLast(sourceNode);
        while (!queue.isEmpty()) {
            Node currentNode = queue.pollFirst();
            for (Edge<Node> edge : currentNode.edges) {
                Node childNode = edge.childNode;
                int newDist = currentNode.minDistance + edge.distanceFromNode;
                if (newDist < childNode.minDistance) {
                    childNode.minDistance = newDist;
                    queue.addLast(childNode); // we add the same node again if we find a shorter path
                }
            }
        }
        ArrayList<Integer> minDistances = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            minDistances.add(graph.get(i).minDistance == Integer.MAX_VALUE ? -1 : graph.get(i).minDistance);
        }
        return minDistances;
    }

    //working code for all cases
    private static ArrayList<Integer> minimumDistanceBetweenTwoNodesv1(int nodes, List<List<Integer>> edges, int source) {
        Map<Integer, Node> graph = new HashMap<>();
        for (int i = 0; i < nodes; i++) {
            graph.put(i, new Node(i));
        }
        for (List<Integer> edge : edges) {
            graph.get(edge.get(0)).addChild(graph.get(edge.get(1)), edge.get(2));
            graph.get(edge.get(1)).addChild(graph.get(edge.get(0)), edge.get(2));
        }
        PriorityQueue<Node> minHeap = new PriorityQueue<>((e1, e2) -> e1.minDistance - e2.minDistance);
        Node sourceNode = graph.get(source);
        sourceNode.minDistance = 0;
        minHeap.add(sourceNode);

        while (!minHeap.isEmpty()) {
            Node currentNode = minHeap.poll();
            for (Edge<Node> edge : currentNode.edges) {
                Node childNode = edge.childNode;
                int newDist = currentNode.minDistance + edge.distanceFromNode;
                if (newDist < childNode.minDistance) {
                    childNode.minDistance = newDist;
                    minHeap.add(childNode); // you might re-add the child node to update its position in the min-heap
                }
            }
        }
        ArrayList<Integer> minDistances = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            minDistances.add(graph.get(i).minDistance == Integer.MAX_VALUE ? -1 : graph.get(i).minDistance);
        }
        return minDistances;
    }
}
