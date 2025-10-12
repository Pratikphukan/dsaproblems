package com.dsaproblems.DSAProblems.graph00;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class CommutableIslands {

    static class Edge<T> {
        T childNode;
        int distanceFromNode;

        public Edge(T childNode, int distanceFromNode) {
            this.childNode = childNode;
            this.distanceFromNode = distanceFromNode;
        }
    }

    public static void main(String[] args) {
        int nodes = 4;
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(1, 2, 1)));
        edges.add(new ArrayList<>(Arrays.asList(2, 3, 4)));
        edges.add(new ArrayList<>(Arrays.asList(1, 4, 3)));
        edges.add(new ArrayList<>(Arrays.asList(4, 3, 2)));
        edges.add(new ArrayList<>(Arrays.asList(1, 3, 10)));

        System.out.println(minCostOfConnectingv1(nodes, edges));
        System.out.println(minCostOfConnectingv2(nodes, edges));
    }

    //working code
    private static int minCostOfConnectingv2(int nodes, List<List<Integer>> edges) {
        // Build adjacency list
        List<List<Edge<Integer>>> graph = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) graph.add(new ArrayList<>());
        for (List<Integer> edge : edges) {
            int u = edge.get(0), v = edge.get(1), wt = edge.get(2);
            graph.get(u).add(new Edge<Integer>(v, wt));
            graph.get(v).add(new Edge<Integer>(u, wt));
        }

        int totalCost = 0, connected = 0;
        boolean[] visited = new boolean[nodes + 1];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        minHeap.add(new int[]{1, 0});

        while (!minHeap.isEmpty() && connected < nodes) {
            int[] curr = minHeap.poll();
            int u = curr[0], wt = curr[1];
            if (visited[u]) continue;
            visited[u] = true;
            totalCost += wt;
            connected++;
            for (Edge<Integer> edge : graph.get(u)) {
                if (!visited[edge.childNode]) {
                    minHeap.add(new int[]{edge.childNode, edge.distanceFromNode});
                }
            }
        }
        return connected == nodes ? totalCost : -1;
    }

    //working code
    private static int minCostOfConnectingv1(int nodes, List<List<Integer>> edges) {
        List<List<PetrolBunk.Edge<Integer>>> graph = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) graph.add(new ArrayList<>());
        for (List<Integer> edge : edges) {
            int u = edge.get(0), v = edge.get(1), wt = edge.get(2);
            graph.get(u).add(new PetrolBunk.Edge<Integer>(v, wt));
            graph.get(v).add(new PetrolBunk.Edge<Integer>(u, wt));
        }
        int count = 0;
        boolean[] visited = new boolean[nodes + 1];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        minHeap.add(new int[]{1, 0});
        visited[1] = true;
        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int u = curr[0];
            if (!visited[u]) {
                count += curr[1];
                visited[u] = true;
            }
            for (PetrolBunk.Edge<Integer> edge : graph.get(u)) {
                int v = edge.childNode;
                int wt = edge.distanceFromNode;
                if (!visited[v]) {
                    minHeap.add(new int[]{v, wt});
                }
            }
        }
        return count;
    }
}
