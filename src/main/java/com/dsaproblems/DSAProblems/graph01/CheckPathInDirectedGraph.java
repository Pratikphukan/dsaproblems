package com.dsaproblems.DSAProblems.graph01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.NonNull;

public class CheckPathInDirectedGraph {

    public static void main(String[] args) {
        int nodes = 5;
        List<List<Integer>> directedEdges = new ArrayList<>();
        directedEdges.add(new ArrayList<>(Arrays.asList(1, 2)));
        directedEdges.add(new ArrayList<>(Arrays.asList(4, 1)));
        directedEdges.add(new ArrayList<>(Arrays.asList(2, 4)));
        directedEdges.add(new ArrayList<>(Arrays.asList(3, 4)));
        directedEdges.add(new ArrayList<>(Arrays.asList(5, 2)));
        directedEdges.add(new ArrayList<>(Arrays.asList(1, 3)));

        // [[1,4],[2,1],[4,3],[4,5],[2,3],[2,4],[1,5],[5,3],[2,5],[5,1],[4,2],[3,1],[5,4],[3,4],[1,3],[4,1],[3,5],[3,2],[5,2]]

//		int nodes = 5;
//		List<List<Integer>> directedEdges = new ArrayList<>();
//		directedEdges.add(new ArrayList<>(Arrays.asList(1, 4)));
//		directedEdges.add(new ArrayList<>(Arrays.asList(2, 1)));
//		directedEdges.add(new ArrayList<>(Arrays.asList(4, 3)));
//		directedEdges.add(new ArrayList<>(Arrays.asList(4, 5)));
//		directedEdges.add(new ArrayList<>(Arrays.asList(2, 3)));
//		directedEdges.add(new ArrayList<>(Arrays.asList(2, 4)));
//		directedEdges.add(new ArrayList<>(Arrays.asList(1, 5)));
//		directedEdges.add(new ArrayList<>(Arrays.asList(5, 3)));
//		directedEdges.add(new ArrayList<>(Arrays.asList(2, 5)));
//		directedEdges.add(new ArrayList<>(Arrays.asList(5, 1)));
//		directedEdges.add(new ArrayList<>(Arrays.asList(4, 2)));
//		directedEdges.add(new ArrayList<>(Arrays.asList(3, 1)));
//		directedEdges.add(new ArrayList<>(Arrays.asList(5, 4)));
//		directedEdges.add(new ArrayList<>(Arrays.asList(3, 4)));
//		directedEdges.add(new ArrayList<>(Arrays.asList(1, 3)));
//		directedEdges.add(new ArrayList<>(Arrays.asList(4, 1)));
//		directedEdges.add(new ArrayList<>(Arrays.asList(3, 5)));
//		directedEdges.add(new ArrayList<>(Arrays.asList(3, 2)));
//		directedEdges.add(new ArrayList<>(Arrays.asList(5, 2)));

        System.out.println(checkPathFromSourceToDestinationv1(nodes, directedEdges));
        // checkPathFromSourceToDestination1(nodes, directedEdges);
        System.out.println(checkPathFromSourceToDestinationv2(nodes, directedEdges));
    }

    // does not work, do not try this
    private static boolean checkPathFromSourceToDestination1(int nodes, List<List<Integer>> directedEdges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 1; i <= nodes; i++) {
            graph.add(new ArrayList<>());
            for (int j = 1; j <= nodes; j++) {
                graph.get(i - 1).add(0);
            }
        }
        for (List<Integer> directedEdge : directedEdges) {
            graph.get(directedEdge.get(0) - 1).set(directedEdge.get(1) - 1, 1);
        }
        Deque<NodeData> queue = new LinkedList<>();
        NodeData startNode = new NodeData(1);
        startNode.setVisited(true);
        queue.addLast(startNode);
        while (!queue.isEmpty()) {
            NodeData node = queue.pollFirst();
            List<NodeData> childList = getAllNeighbouringNodes(node.getData(), graph);
            for (NodeData child : childList) {
                if (!child.isVisited()) {
                    child.setVisited(true);
                    queue.addLast(child);
                    if (child.getData() == nodes) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static List<NodeData> getAllNeighbouringNodes(@NonNull Integer data, List<List<Integer>> graph) {
        List<NodeData> neighbourList = new ArrayList<>();
        for (int i = 0; i < graph.get(data - 1).size(); i++) {
            if (graph.get(data - 1).get(i) == 1) {
                neighbourList.add(new NodeData(i + 1));
            }
        }
        return neighbourList;
    }

    // solution is derived using BFS, working
    private static boolean checkPathFromSourceToDestinationv1(int nodes, List<List<Integer>> directedEdges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < directedEdges.size(); i++) {
            int start = directedEdges.get(i).get(0);
            int end = directedEdges.get(i).get(1);
            graph.get(start).add(end);
        }
        Set<Integer> visitedNodes = new HashSet<>();
        Deque<Integer> queue = new LinkedList<>();
        queue.addLast(1);
        visitedNodes.add(1);
        while (!queue.isEmpty()) {
            Integer node = queue.pollFirst();
            List<Integer> childList = graph.get(node);
            for (Integer child : childList) {
                if (!visitedNodes.contains(child)) {
                    queue.addLast(child);
                    visitedNodes.add(child);
                    if (child.equals(nodes)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // solution is derived using DFS, working
    private static boolean checkPathFromSourceToDestinationv2(int nodes, List<List<Integer>> directedEdges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < directedEdges.size(); i++) {
            int start = directedEdges.get(i).get(0);
            int end = directedEdges.get(i).get(1);
            graph.get(start).add(end);
        }
        Set<Integer> visitedNodes = new HashSet<>();
        Deque<Integer> stack = new LinkedList<>();
        stack.addFirst(1);
        visitedNodes.add(1);
        while (!stack.isEmpty()) {
            Integer node = stack.pollFirst();
            List<Integer> childList = graph.get(node);
            for (Integer child : childList) {
                if (!visitedNodes.contains(child)) {
                    stack.addFirst(child);
                    visitedNodes.add(child);
                    if (child.equals(nodes)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
