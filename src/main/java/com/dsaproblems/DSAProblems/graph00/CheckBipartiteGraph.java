package com.dsaproblems.DSAProblems.graph00;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class CheckBipartiteGraph {

    enum SetType {
        SET1, SET2, UNASSIGNED,
    }

    @Getter
    static class Node {
        private int data;

        private List<Node> childNodes;

        @Setter
        private boolean isVisited;

        @Setter
        private SetType setType;

        public Node(int data) {
            this.data = data;
            this.childNodes = new ArrayList<>();
            this.isVisited = false;
            this.setType = SetType.UNASSIGNED; // Initially, the node is unassigned to any set
        }

        public void addChild(Node child) {
            this.childNodes.add(child);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> edges = new ArrayList<>();
//        int nodes = 5;
//        edges.add(new ArrayList<>(Arrays.asList(1, 2)));
//        edges.add(new ArrayList<>(Arrays.asList(2, 3)));
//        edges.add(new ArrayList<>(Arrays.asList(3, 4)));
//        edges.add(new ArrayList<>(Arrays.asList(2, 5)));
//        edges.add(new ArrayList<>(Arrays.asList(4, 5)));

        //[[7,8],[1,2],[0,9],[1,3],[6,7],[0,3],[2,5],[3,8],[4,8]]
        int nodes = 10;
        edges.add(new ArrayList<>(Arrays.asList(7, 8)));
        edges.add(new ArrayList<>(Arrays.asList(1, 2)));
        edges.add(new ArrayList<>(Arrays.asList(0, 9)));
        edges.add(new ArrayList<>(Arrays.asList(1, 3)));
        edges.add(new ArrayList<>(Arrays.asList(6, 7)));
        edges.add(new ArrayList<>(Arrays.asList(0, 3)));
        edges.add(new ArrayList<>(Arrays.asList(2, 5)));
        edges.add(new ArrayList<>(Arrays.asList(3, 8)));
        edges.add(new ArrayList<>(Arrays.asList(4, 8)));

        System.out.println(isBipartiteGraphv1(nodes, edges));
        System.out.println(isBipartiteGraphv2(nodes, edges));
    }

    /*
    please remember: The problem is that isBipartiteGraphv2 should not return as soon as it
    finishes checking the first unvisited node, without checking all connected components.
    If the graph is disconnected, it only checks the first component and ignores the rest.
     */
    //working code using DFS and BFS
    private static boolean isBipartiteGraphv2(int nodes, List<List<Integer>> edges) {
        Map<Integer, Node> graph = new HashMap<>();
        for (int i = 0; i < nodes; i++) {
            graph.put(i, new Node(i));
        }
        for (List<Integer> edge : edges) {
            graph.get(edge.get(0)).addChild(graph.get(edge.get(1)));
            graph.get(edge.get(1)).addChild(graph.get(edge.get(0)));
        }
        for (int i = 0; i < nodes; i++) {
            Node node = graph.get(i);
            if (!node.isVisited()) {
//                if (!depthFirstSearch(node, SetType.SET1)) {
//                    return false;
//                }
                if (!breadthFirstSearch(node, SetType.SET1)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean breadthFirstSearch(Node node, SetType setType) {
        Deque<Node> queue = new ArrayDeque<>();
        node.setVisited(true);
        node.setSetType(setType);
        queue.addLast(node);
        while (!queue.isEmpty()) {
            Node currNode = queue.pollFirst();
            List<Node> childList = currNode.getChildNodes();
            for (Node child : childList) {
                if (!child.isVisited()) {
                    child.setVisited(true);
                    child.setSetType(currNode.getSetType() == SetType.SET1 ? SetType.SET2 : SetType.SET1);
                    queue.addLast(child);
                } else {
                    // If the child is already visited, check if it belongs to the same set;
                    if (child.getSetType() == currNode.getSetType()) {
                        return false; // Not a bipartite graph
                    }
                }
            }
        }
        return true;
    }

    private static boolean depthFirstSearch(Node node, SetType setType) {
        Deque<Node> stack = new ArrayDeque<>();
        node.setVisited(true);
        node.setSetType(setType);
        stack.addFirst(node);
        while (!stack.isEmpty()) {
            Node currNode = stack.removeFirst();
            List<Node> childList = currNode.getChildNodes();
            for (Node child : childList) {
                if (!child.isVisited()) {
                    child.setVisited(true);
                    child.setSetType(currNode.getSetType() == SetType.SET1 ? SetType.SET2 : SetType.SET1);
                    stack.addFirst(child);
                } else {
                    // If the child is already visited, check if it belongs to the same set;
                    if (child.getSetType() == currNode.getSetType()) {
                        return false; // Not a bipartite graph
                    }
                }
            }
        }
        return true;
    }

    private static boolean isBipartiteGraphv1(int nodes, List<List<Integer>> edges) {
        List<List<Integer>> graph = new ArrayList<>();
        Map<Integer, SetType> nodeSetType = new HashMap<>();
        for (int i = 0; i < nodes; i++) {
            graph.add(new ArrayList<>());
            nodeSetType.put(i, SetType.UNASSIGNED); // Initially, all nodes are unassigned
        }
        for (List<Integer> edge : edges) {
            int start = edge.get(0);
            int end = edge.get(1);
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
        Set<Integer> visitedNodes = new HashSet<>();
        for (int i = 1; i <= nodes; i++) {
            if (!graph.get(i).isEmpty() && !visitedNodes.contains(i)) {
//                if (depthFirstSearch(i, graph, visitedNodes)) {
//                    return true;
//                }
                if (breadthFirstSearch(i, graph, visitedNodes, nodeSetType)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean breadthFirstSearch(int start, List<List<Integer>> graph, Set<Integer> visitedNodes, Map<Integer, SetType> nodeSetType) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(start);
        visitedNodes.add(start);
        nodeSetType.put(start, SetType.SET1);
        while (!queue.isEmpty()) {
            Integer node = queue.pollFirst();
            List<Integer> childList = graph.get(node);
            for (Integer child : childList) {
                if (visitedNodes.add(child)) {
                    nodeSetType.put(child, nodeSetType.get(node) == SetType.SET1 ? SetType.SET2 : SetType.SET1);
                    queue.addLast(child);
                } else {
                    // If the child is already visited, check if it belongs to the same set;
                    if (nodeSetType.get(child) == nodeSetType.get(node)) {
                        return false; // Not a bipartite graph
                    }
                }
            }
        }
        return true;
    }
}
