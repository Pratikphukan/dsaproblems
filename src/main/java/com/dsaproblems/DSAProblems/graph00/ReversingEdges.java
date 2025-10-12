package com.dsaproblems.DSAProblems.graph00;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class ReversingEdges {

    static class Edge<T> {
        T childNode;
        int distanceFromNode;

        public Edge(T childNode, int distanceFromNode) {
            this.childNode = childNode;
            this.distanceFromNode = distanceFromNode;
        }
    }

    public static void main(String[] args) {
        int nodes = 5;
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(1, 2)));
        edges.add(new ArrayList<>(Arrays.asList(2, 3)));
        edges.add(new ArrayList<>(Arrays.asList(4, 3)));
        edges.add(new ArrayList<>(Arrays.asList(4, 5)));

        nodes = 7;
        edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(1, 2)));
        edges.add(new ArrayList<>(Arrays.asList(3, 2)));
        edges.add(new ArrayList<>(Arrays.asList(3, 4)));
        edges.add(new ArrayList<>(Arrays.asList(7, 4)));
        edges.add(new ArrayList<>(Arrays.asList(6, 2)));
        edges.add(new ArrayList<>(Arrays.asList(5, 6)));
        edges.add(new ArrayList<>(Arrays.asList(7, 5)));

        System.out.println(reverseEdgesv1(nodes, edges));
        System.out.println(reverseEdgesv2(nodes, edges));
    }

    //working code
    private static int reverseEdgesv2(int nodes, List<List<Integer>> edges) {
        List<List<Edge<Integer>>> graph = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) graph.add(new ArrayList<>());
        for (List<Integer> edge : edges) {
            int u = edge.get(0), v = edge.get(1);
            graph.get(u).add(new Edge<Integer>(v, 0));
            graph.get(v).add(new Edge<Integer>(u, 1));
        }
        int x = 0;
        int[] minDist = new int[nodes + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[nodes + 1];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        minDist[1] = 0;
        minHeap.add(new int[]{1, 0});
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
        return minDist[nodes] == Integer.MAX_VALUE ? -1 : minDist[nodes];
    }

    private static int reverseEdgesv1(int nodes, List<List<Integer>> edges) {
        List<List<Edge<Integer>>> graph = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) graph.add(new ArrayList<>());
        for (List<Integer> edge : edges) {
            int u = edge.get(0), v = edge.get(1);
            graph.get(u).add(new Edge<Integer>(v, 0));
            graph.get(v).add(new Edge<Integer>(u, 1));
        }
        int x = 0;
        int[] minDist = new int[nodes + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[nodes + 1];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        minDist[1] = 0;
        minHeap.add(new int[]{1, 0});
        x += 1;
        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int u = curr[0];
            if (visited[u]) continue;
            visited[u] = true;
            for (Edge<Integer> edge : graph.get(u)) {
                int v = edge.childNode;
                int wt = edge.distanceFromNode;
                if (minDist[v] == Integer.MAX_VALUE) {
                    minDist[v] = minDist[u] + wt;
                    minHeap.add(new int[]{v, minDist[v]});
                    x += 1;
                }
            }
        }
        return minDist[nodes];
    }
}
