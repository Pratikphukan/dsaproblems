package com.dsaproblems.DSAProblems.graph00;

import java.util.*;

class Edge implements Comparable<Edge> {
    int src;    // source distribution center
    int dest;   // destination distribution center
    int weight; // construction cost for the edge

    // Edge constructor
    public Edge(int src, int dest, int weight) {
        this.src = src;       // set source center
        this.dest = dest;     // set destination center
        this.weight = weight; // set cost of construction
    }

    // Compare edges based on weight for sorting purposes
    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;  // ascending order of weight
    }
}

class UnionFind {
    int[] parent;  // parent array for DSU
    int[] rank;    // rank array for union by rank optimization

    // Constructor to initialize DSU for n nodes (1-indexed up to n)
    public UnionFind(int n) {
        parent = new int[n + 1];  // allocate parent array (using n+1 for 1-index)
        rank = new int[n + 1];    // allocate rank array
        // Initially, each node is its own parent (self loop)
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;  // rank initial value
        }
    }

    // Find the representative (root parent) of node x with path compression
    public int find(int x) {
        if (parent[x] != x) {           // if x is not its own parent
            parent[x] = find(parent[x]); // recursively find the root parent
        }
        return parent[x];               // return the ultimate parent
    }

    // Perform union of two sets containing nodes x and y using union by rank optimization
    public boolean union(int x, int y) {
        int rootX = find(x);  // find root of x
        int rootY = find(y);  // find root of y

        if (rootX == rootY) { // if both nodes have the same root, they are already in the same set
            return false;     // union not performed, edge would create a cycle
        }

        // Merge smaller rank tree under larger rank tree
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;  // rootY becomes parent of rootX
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;  // rootX becomes parent of rootY
        } else {
            // If ranks are same, choose one as new root and increase its rank
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;  // union successful, no cycle
    }
}

public class ConstructionCost {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(1, 2, 14)));
        edges.add(new ArrayList<>(Arrays.asList(2, 3, 7)));
        edges.add(new ArrayList<>(Arrays.asList(3, 1, 2)));
        int A = 3;
        System.out.println(solvev1(A, edges));
    }

    private static int solvev1(int A, ArrayList<ArrayList<Integer>> B) {
        // Edge case: if there is only one distribution center, no road is needed.
        if (A == 1) {
            return 0;
        }

        // Constant modulo value as required by problem statement to avoid integer overflow.
        final int MOD = 1000000007;

        // Create a list to hold all possible roads (edges)
        List<Edge> edges = new ArrayList<>();
        // Iterate over each given edge in B
        for (ArrayList<Integer> edgeInfo : B) {
            // Each edgeInfo list contains: [u, v, w]
            int u = edgeInfo.get(0);   // source distribution center
            int v = edgeInfo.get(1);   // destination distribution center
            int w = edgeInfo.get(2);   // construction cost
            // Create new Edge and add to list
            edges.add(new Edge(u, v, w));
        }

        // Sort edges based on the weight (cost) in ascending order
        Collections.sort(edges);

        // Initialize union-find structure for A distribution centers
        UnionFind uf = new UnionFind(A);

        long totalCost = 0;  // to store the total construction cost; long to handle large numbers
        int edgeCount = 0;   // to track the number of edges added to the MST

        // Iterate over all edges in sorted order
        for (Edge edge : edges) {
            // If the two centers are not already connected (union takes place)
            if (uf.union(edge.src, edge.dest)) {
                totalCost = (totalCost + edge.weight) % MOD;  // add the edge's weight to total cost under modulo
                edgeCount++;  // increment count of edges added
                // If we have added exactly A-1 edges, we have a valid spanning tree
                if (edgeCount == A - 1) {
                    break; // all distribution centers are now connected
                }
            }
        }

        // Check if we've connected all distribution centers. If not, MST is not possible.
        if (edgeCount < A - 1) {
            return -1; // or any other value representing that not all centers are connected
        }

        // Return the total minimum construction cost modulo 10^9+7
        return (int) totalCost;
    }
}
