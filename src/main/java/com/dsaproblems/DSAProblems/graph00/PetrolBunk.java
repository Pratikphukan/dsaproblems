package com.dsaproblems.DSAProblems.graph00;

import java.util.*;

public class PetrolBunk {

    static class Edge<T> {
        T childNode;
        int distanceFromNode;

        public Edge(T childNode, int distanceFromNode) {
            this.childNode = childNode;
            this.distanceFromNode = distanceFromNode;
        }
    }

    static class Node {
        int value;

        int minDistance;

        List<Edge> edges;

        int frequencyOfAdd;

        boolean isVisited;

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
//        int nodes = 7;
//        List<List<Integer>> edges = new ArrayList<>();
//        edges.add(new ArrayList<>(Arrays.asList(1, 3, 80)));
//        edges.add(new ArrayList<>(Arrays.asList(1, 2, 10)));
//        edges.add(new ArrayList<>(Arrays.asList(2, 5, 20)));
//        edges.add(new ArrayList<>(Arrays.asList(3, 4, 70)));
//        edges.add(new ArrayList<>(Arrays.asList(5, 6, 80)));
//        edges.add(new ArrayList<>(Arrays.asList(6, 7, 75)));
//        edges.add(new ArrayList<>(Arrays.asList(5, 7, 10)));


//        int nodes = 10;
//        List<List<Integer>> edges = new ArrayList<>();
//        edges.add(new ArrayList<>(Arrays.asList(1, 2, 4)));
//        edges.add(new ArrayList<>(Arrays.asList(1, 4, 8)));
//        edges.add(new ArrayList<>(Arrays.asList(1, 7, 2)));
//        edges.add(new ArrayList<>(Arrays.asList(2, 7, 1)));
//        edges.add(new ArrayList<>(Arrays.asList(2, 3, 5)));
//        edges.add(new ArrayList<>(Arrays.asList(2, 8, 9)));
//        edges.add(new ArrayList<>(Arrays.asList(3, 10, 1)));
//        edges.add(new ArrayList<>(Arrays.asList(3, 8, 1)));
//        edges.add(new ArrayList<>(Arrays.asList(4, 7, 3)));
//        edges.add(new ArrayList<>(Arrays.asList(5, 8, 6)));
//        edges.add(new ArrayList<>(Arrays.asList(6, 9, 2)));
//        edges.add(new ArrayList<>(Arrays.asList(7, 8, 4)));
//        edges.add(new ArrayList<>(Arrays.asList(8, 6, 3)));
//        edges.add(new ArrayList<>(Arrays.asList(9, 10, 4)));

        int nodes = 4;
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(1, 2, 1)));
        edges.add(new ArrayList<>(Arrays.asList(2, 3, 4)));
        edges.add(new ArrayList<>(Arrays.asList(1, 4, 3)));
        edges.add(new ArrayList<>(Arrays.asList(4, 3, 2)));
        edges.add(new ArrayList<>(Arrays.asList(1, 3, 10)));

        System.out.println(minTimeRequiredv1(nodes, edges, 1));

        System.out.println(minTimeRequiredv2(nodes, edges, 1));

        System.out.println(minTimeRequiredv3(nodes, edges, 1));
    }

    private static ArrayList<Integer> minTimeRequiredv3(int nodes, List<List<Integer>> edges, int source) {
        List<List<Edge<Integer>>> graph = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) graph.add(new ArrayList<>());
        for (List<Integer> edge : edges) {
            int u = edge.get(0), v = edge.get(1), wt = edge.get(2);
            graph.get(u).add(new Edge<Integer>(v, wt));
            graph.get(v).add(new Edge<Integer>(u, wt));
        }
        int x = 0;
        int[] minDist = new int[nodes + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[nodes + 1];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        minDist[source] = 0;
        minHeap.add(new int[]{source, 0});
        x += 1;
        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int u = curr[0];
            if (visited[u]) continue;
            visited[u] = true;
            for (Edge<Integer> edge : graph.get(u)) {
                int v = edge.childNode;
                int wt = edge.distanceFromNode;
                if (minDist[u] + wt < minDist[v]) {
                    minDist[v] = minDist[u] + wt;
                    minHeap.add(new int[]{v, minDist[v]});
                    x += 1;
                }
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i <= nodes; i++) {
            result.add(minDist[i] == Integer.MAX_VALUE ? -1 : minDist[i]);
        }
        return result;
    }

    //working code
    private static ArrayList<Integer> minTimeRequiredv2(int nodes, List<List<Integer>> edges, int source) {
        int totalAdditions = 0;
        Map<Integer, Node> graph = new HashMap<>();
        for (int i = 1; i <= nodes; i++) {
            graph.put(i, new Node(i));
        }
        for (List<Integer> edge : edges) {
            graph.get(edge.get(0)).addChild(graph.get(edge.get(1)), edge.get(2));
            graph.get(edge.get(1)).addChild(graph.get(edge.get(0)), edge.get(2));
        }
        Deque<Node> queue = new ArrayDeque<>();
        Node sourceNode = graph.get(source);
        sourceNode.minDistance = 0;
        sourceNode.frequencyOfAdd += 1; // Initialize frequency of add
        queue.addLast(sourceNode);
        totalAdditions += 1;
        while (!queue.isEmpty()) {
            Node currentNode = queue.pollFirst();
            for (Edge<Node> edge : currentNode.edges) {
                Node childNode = edge.childNode;
                int newDist = currentNode.minDistance + edge.distanceFromNode;
                if (newDist < childNode.minDistance) {
                    childNode.minDistance = newDist;
                    childNode.frequencyOfAdd += 1;
                    queue.addLast(childNode); // we add the same node again if we find a shorter path
                    totalAdditions += 1;
                }
            }
        }
        ArrayList<Integer> minDistances = new ArrayList<>();
        for (int i = 1; i <= nodes; i++) {
            minDistances.add(graph.get(i).minDistance == Integer.MAX_VALUE ? -1 : graph.get(i).minDistance);
        }
        return minDistances;
    }

    //working code and optimal
    private static ArrayList<Integer> minTimeRequiredv1(int nodes, List<List<Integer>> edges, int source) {
        int totalAdditions = 0;
        Map<Integer, Node> graph = new HashMap<>();
        for (int i = 1; i <= nodes; i++) {
            graph.put(i, new Node(i));
        }
        for (List<Integer> edge : edges) {
            graph.get(edge.get(0)).addChild(graph.get(edge.get(1)), edge.get(2));
            graph.get(edge.get(1)).addChild(graph.get(edge.get(0)), edge.get(2));
        }
        PriorityQueue<Node> minHeap = new PriorityQueue<>((e1, e2) -> e1.minDistance - e2.minDistance);
        Node sourceNode = graph.get(source);
        sourceNode.minDistance = 0;
        sourceNode.frequencyOfAdd += 1;
        minHeap.add(sourceNode);
        totalAdditions += 1;

        while (!minHeap.isEmpty()) {
            Node currentNode = minHeap.poll();
            if (currentNode.isVisited) continue; // Skip if already visited
            currentNode.isVisited = true; // Mark as visited
            for (Edge<Node> edge : currentNode.edges) {
                Node childNode = edge.childNode;
                int newDist = currentNode.minDistance + edge.distanceFromNode;
                if (newDist < childNode.minDistance) {
                    childNode.minDistance = newDist;
                    childNode.frequencyOfAdd += 1;
                    minHeap.add(childNode);
                    totalAdditions += 1;
                }
            }
        }
        ArrayList<Integer> minDistances = new ArrayList<>();
        for (int i = 1; i <= nodes; i++) {
            minDistances.add(graph.get(i).minDistance == Integer.MAX_VALUE ? -1 : graph.get(i).minDistance);
        }
        return minDistances;
    }
}
