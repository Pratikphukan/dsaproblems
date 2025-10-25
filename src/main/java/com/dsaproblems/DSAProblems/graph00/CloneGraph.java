package com.dsaproblems.DSAProblems.graph00;

import java.util.*;

class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
        this.label = x;
        neighbors = new ArrayList<>();
    }
}

class Node extends UndirectedGraphNode {

    Node(int x) {
        super(x);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

public class CloneGraph {

    public static void main(String[] args) {
        UndirectedGraphNode root = new UndirectedGraphNode(1);
        root.neighbors.add(new UndirectedGraphNode(3));
        root.neighbors.add(new UndirectedGraphNode(2));
        UndirectedGraphNode node = new UndirectedGraphNode(4);
        node.neighbors.add(new UndirectedGraphNode(5));
        node.neighbors.add(new UndirectedGraphNode(6));
        root.neighbors.add(node);
        System.out.println(cloneGraphv1(root));
        System.out.println(cloneGraphv2(root));

        System.out.println(cloneGraphv3(root));
        System.out.println(cloneGraphv4(root));
    }

    private static UndirectedGraphNode cloneGraphv3(UndirectedGraphNode root) {
        if (root == null) return null;
        Set<UndirectedGraphNode> visited = new HashSet<>();
        //UndirectedGraphNode[] visited = new UndirectedGraphNode[101];
        UndirectedGraphNode copy = new UndirectedGraphNode(root.label);
        dfs(root, copy, visited);
        return copy;
    }

    private static void dfs(UndirectedGraphNode root,
                            UndirectedGraphNode copy,
                            Set<UndirectedGraphNode> visited) {
        //visited[root.label] = copy;
        visited.add(root);
        for (UndirectedGraphNode neighbor : root.neighbors) {
            if (!visited.contains(neighbor)) {
                UndirectedGraphNode newNode = new UndirectedGraphNode(neighbor.label);
                copy.neighbors.add(newNode);
                dfs(neighbor, newNode, visited);
            } else {
                copy.neighbors.add(neighbor);
            }
        }
    }

    //working code
    private static UndirectedGraphNode cloneGraphv4(UndirectedGraphNode root) {
        if (root == null) return null;

        Map<UndirectedGraphNode, UndirectedGraphNode> graph = new HashMap<>();
        Deque<UndirectedGraphNode> stack = new ArrayDeque<>();

        graph.put(root, new UndirectedGraphNode(root.label));
        stack.addFirst(root);

        while (!stack.isEmpty()) {
            UndirectedGraphNode node = stack.pollFirst();
            UndirectedGraphNode nodeCopy = graph.get(node);
            for (UndirectedGraphNode nei : node.neighbors) {
                UndirectedGraphNode neiCopy = graph.computeIfAbsent(nei, k -> {
                    stack.addFirst(k);
                    return new UndirectedGraphNode(k.label);
                });
                nodeCopy.neighbors.add(neiCopy);
            }
        }

        return graph.get(root);
    }

    //working code, TC: O(N+E)
    private static UndirectedGraphNode cloneGraphv2(UndirectedGraphNode root) {
        Map<UndirectedGraphNode, UndirectedGraphNode> graph = new HashMap<>();
        Deque<UndirectedGraphNode> stack = new ArrayDeque<>();

        graph.put(root, new UndirectedGraphNode(root.label));
        stack.addFirst(root);

        while (!stack.isEmpty()) {
            UndirectedGraphNode curr = stack.pollFirst();
            UndirectedGraphNode nodeCopy = graph.get(curr);
            for (UndirectedGraphNode neighbor : curr.neighbors) {
                if (!graph.containsKey(neighbor)) {
                    graph.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    stack.addFirst(neighbor);
                }
                nodeCopy.neighbors.add(graph.get(neighbor));
            }
        }
        return graph.get(root);
    }

    private static UndirectedGraphNode cloneGraphv1(UndirectedGraphNode root) {
        Map<UndirectedGraphNode, UndirectedGraphNode> graph = new HashMap<>();
        UndirectedGraphNode rootCopy = new UndirectedGraphNode(root.label);
        Deque<UndirectedGraphNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        graph.put(root, rootCopy);
        while (!queue.isEmpty()) {
            UndirectedGraphNode temp = queue.pollFirst();
            for (UndirectedGraphNode child : temp.neighbors) {
                if (!graph.containsKey(child)) {
                    graph.put(child, new UndirectedGraphNode(child.label));
                    queue.addLast(child);
                }
                graph.get(temp).neighbors.add(graph.get(child));
            }
        }
        return rootCopy;
    }
}
