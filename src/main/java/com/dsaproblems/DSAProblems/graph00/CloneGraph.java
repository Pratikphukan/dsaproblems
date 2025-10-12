package com.dsaproblems.DSAProblems.graph00;

import java.util.*;

public class CloneGraph {

    static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            this.label = x;
            neighbors = new ArrayList<>();
        }
    }

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
    }

    private static UndirectedGraphNode cloneGraphv2(UndirectedGraphNode root) {
        Map<UndirectedGraphNode, UndirectedGraphNode> graph = new HashMap<>();
        UndirectedGraphNode rootCopy = new UndirectedGraphNode(root.label);
        Deque<UndirectedGraphNode> stack = new ArrayDeque<>();
        stack.addFirst(root);
        graph.put(root, rootCopy);
        while (!stack.isEmpty()) {
            UndirectedGraphNode temp = stack.pollFirst();
            for (UndirectedGraphNode child : temp.neighbors) {
                if (!graph.containsKey(child)) {
                    graph.put(child, new UndirectedGraphNode(child.label));
                    stack.addFirst(child);
                }
                graph.get(temp).neighbors.add(graph.get(child));
            }
        }
        return rootCopy;
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
