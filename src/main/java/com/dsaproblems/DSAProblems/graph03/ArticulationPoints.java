package com.dsaproblems.DSAProblems.graph03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticulationPoints {
    private static ArrayList<ArrayList<Integer>> graph;
    private static int[] tin;
    private static int[] low;
    private static boolean[] visited;
    private static boolean[] isArticulation;
    private static int timer;

    public static ArrayList<Integer> solve(int A, ArrayList<ArrayList<Integer>> B) {
        graph = new ArrayList<>();
        for (int i = 0; i <= A; i++) graph.add(new ArrayList<>());

        for (ArrayList<Integer> edge : B) {
            int u = edge.get(0);
            int v = edge.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        tin = new int[A + 1];
        low = new int[A + 1];
        visited = new boolean[A + 1];
        isArticulation = new boolean[A + 1];
        timer = 1;

        for (int i = 1; i <= A; i++) {
            if (!visited[i]) {
                dfs(i, -1);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= A; i++) {
            if (isArticulation[i]) ans.add(i);
        }
        return ans;
    }

    private static void dfs(int u, int parent) {
        visited[u] = true;
        tin[u] = low[u] = timer++;
        int children = 0;

        for (int v : graph.get(u)) {
            if (v == parent) continue;

            if (visited[v]) {
                low[u] = Math.min(low[u], tin[v]);
            } else {
                dfs(v, u);
                low[u] = Math.min(low[u], low[v]);

                if (parent != -1 && low[v] >= tin[u]) {
                    isArticulation[u] = true;
                }
                children++;
            }
        }

        if (parent == -1 && children > 1) {
            isArticulation[u] = true;
        }
    }

    public static void main(String[] args) {
        int A = 5;
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(1, 2)));
        edges.add(new ArrayList<>(Arrays.asList(4, 1)));
        edges.add(new ArrayList<>(Arrays.asList(2, 4)));
        edges.add(new ArrayList<>(Arrays.asList(3, 4)));
        edges.add(new ArrayList<>(Arrays.asList(5, 2)));
        edges.add(new ArrayList<>(Arrays.asList(3, 1)));

        System.out.println(solve(A, edges));
    }
}
