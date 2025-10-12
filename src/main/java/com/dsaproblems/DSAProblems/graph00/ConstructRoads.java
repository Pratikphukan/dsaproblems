package com.dsaproblems.DSAProblems.graph00;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class ConstructRoads {

    public int mod = 1000 * 1000 * 1000 + 7;

    //working code using BFS
    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        boolean[] vis = new boolean[A + 1];
        int[] color = new int[A + 1];

        for (int i = 0; i <= A; i++)
            adj.add(new ArrayList<>());
        for (ArrayList<Integer> list : B) {
            adj.get(list.get(0)).add(list.get(1));
            adj.get(list.get(1)).add(list.get(0));
        }

        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= A; i++) {
            if (!vis[i]) q.addLast(i);
            while (!q.isEmpty()) {
                int u = q.pollFirst();
                vis[u] = true;
                for (Integer v : adj.get(u)) {
                    if (!vis[v]) {
                        q.addLast(v);
                        if (color[u] == 0)
                            color[u] = 1;
                        if (color[u] == 1)
                            color[v] = 2;
                        else color[v] = 1;
                    } else if (color[v] == color[u])
                        return 0;
                }
            }
        }

        long setA = 1 - A, setB = 1 - A;
        for (int i = 0; i <= A; i++) {
            if (color[i] == 1)
                setA++;
            else if (color[i] == 2)
                setB++;
        }

        return (int) ((setA % mod) * (setB % mod) % mod);
    }
}
