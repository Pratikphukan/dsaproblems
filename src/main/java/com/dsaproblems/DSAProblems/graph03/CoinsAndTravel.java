package com.dsaproblems.DSAProblems.graph03;

import java.util.*;

public class CoinsAndTravel {

    static class Edge {
        int to, w;

        Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    public static int solve(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        int n = A.size();
        final long MOD = 1000000007L;
        final long NEG = -(long) 4e18;

        ArrayList<Edge>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) g[i] = new ArrayList<>();

        for (ArrayList<Integer> e : B) {
            int u = e.get(0);
            int v = e.get(1);
            int w = e.get(2);
            g[u].add(new Edge(v, w));
            g[v].add(new Edge(u, w));
        }

        int[] parent = new int[n + 1];
        int[] parentW = new int[n + 1];
        int[] order = new int[n];
        int idx = 0;

        // Iterative DFS to get parent and traversal order
        Deque<Integer> st = new ArrayDeque<>();
        st.addFirst(1);
        parent[1] = -1;

        while (!st.isEmpty()) {
            int u = st.pollFirst();
            order[idx++] = u;

            for (Edge ed : g[u]) {
                int v = ed.to;
                if (v == parent[u]) continue;
                parent[v] = u;
                parentW[v] = ed.w;
                st.addFirst(v);
            }
        }

        long[] down = new long[n + 1];
        long[] up = new long[n + 1];
        Arrays.fill(up, NEG);

        // Postorder: compute down[]
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            down[u] = A.get(u - 1); // start at u itself

            for (Edge ed : g[u]) {
                int v = ed.to, w = ed.w;
                if (v == parent[u]) continue;

                if (down[v] >= w) {
                    long cand = down[v] - w + A.get(u - 1);
                    down[u] = Math.max(down[u], cand);
                }
            }
        }

        long ans = 0;
        for (int i = 1; i <= n; i++) ans = Math.max(ans, down[i]);

        // Preorder: compute up[]
        for (int i = 0; i < n; i++) {
            int u = order[i];

            long best1 = NEG, best2 = NEG;
            int bestChild = -1;

            // Find top two child contributions into u
            for (Edge ed : g[u]) {
                int v = ed.to, w = ed.w;
                if (v == parent[u]) continue;

                long contrib = NEG;
                if (down[v] >= w) {
                    contrib = down[v] - w + A.get(u - 1);
                }

                if (contrib > best1) {
                    best2 = best1;
                    best1 = contrib;
                    bestChild = v;
                } else if (contrib > best2) {
                    best2 = contrib;
                }
            }

            for (Edge ed : g[u]) {
                int v = ed.to, w = ed.w;
                if (v == parent[u]) continue;

                long siblingBest = (bestChild == v) ? best2 : best1;

                long bestAtU = A.get(u - 1); // start at u itself
                bestAtU = Math.max(bestAtU, up[u]); // come from above
                bestAtU = Math.max(bestAtU, siblingBest); // come from sibling subtree

                if (bestAtU >= w) {
                    up[v] = bestAtU - w + A.get(v - 1);
                    ans = Math.max(ans, up[v]);
                }
            }
        }

        return (int) (ans % MOD);
    }

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(List.of(6, 3, 2, 5, 0));
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(1, 2, 10)));
        edges.add(new ArrayList<>(Arrays.asList(2, 3, 3)));
        edges.add(new ArrayList<>(Arrays.asList(2, 4, 1)));
        edges.add(new ArrayList<>(Arrays.asList(1, 5, 1)));
        System.out.println(solve(A, edges));
    }

}
