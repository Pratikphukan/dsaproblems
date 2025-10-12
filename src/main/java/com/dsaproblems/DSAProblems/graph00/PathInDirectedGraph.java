package com.dsaproblems.DSAProblems.graph00;

import com.dsaproblems.DSAProblems.graph01.NodeData;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/*
Better cache locality means that data is stored in contiguous
memory locations, so when the CPU loads one element,
nearby elements are likely loaded into the cache as well.
This makes accessing consecutive elements faster, as is the
case with ArrayDeque (backed by an array).

Less object overhead refers to the fact that LinkedList
stores each element in a separate node object, each with
extra pointers (to previous and next nodes). This increases
memory usage and adds indirection, making access slower.
ArrayDeque stores elements directly in an array, avoiding
this extra overhead.
 */
public class PathInDirectedGraph {

    private static Map<Integer, List<Integer>> graph;

    @Getter
    static class Node {

        private int data;

        private List<Node> childNodes;

        @Setter
        private boolean isVisited;

        public Node(int data) {
            this.data = data;
            this.childNodes = new ArrayList<>();
            this.isVisited = false;
        }

        public void addChild(Node child) {
            this.childNodes.add(child);
        }
    }

    public static void main(String[] args) {
        int nodes = 5;
        List<List<Integer>> directedEdges = new ArrayList<>();
        directedEdges.add(new ArrayList<>(Arrays.asList(1, 2)));
        directedEdges.add(new ArrayList<>(Arrays.asList(4, 1)));
        directedEdges.add(new ArrayList<>(Arrays.asList(2, 4)));
        directedEdges.add(new ArrayList<>(Arrays.asList(3, 4)));
        directedEdges.add(new ArrayList<>(Arrays.asList(5, 2)));
        directedEdges.add(new ArrayList<>(Arrays.asList(1, 3)));

        System.out.println(checkPathFromSourceToDestinationv1(nodes, directedEdges));
        System.out.println(checkPathFromSourceToDestinationv2(nodes, directedEdges));
        System.out.println(checkPathFromSourceToDestinationv3(nodes, directedEdges));
        System.out.println(checkPathFromSourceToDestinationv4(nodes, directedEdges));
        System.out.println(checkPathFromSourceToDestinationv5(nodes, directedEdges));
    }

    //working code
    private static boolean checkPathFromSourceToDestinationv5(int nodes, List<List<Integer>> directedEdges) {
        Map<Integer, Node> nodesMap = new HashMap<>();
        for (int i = 1; i <= nodes; i++) {
            nodesMap.put(i, new Node(i));
        }
        Node sourceNode;
        Node destinationNode;
        for (List<Integer> directedEdge : directedEdges) {
            sourceNode = nodesMap.get(directedEdge.get(0));
            destinationNode = nodesMap.get(directedEdge.get(1));
            sourceNode.addChild(destinationNode);
        }
        if (nodes == 1) return true; // Early exit
        Deque<Node> stack = new ArrayDeque<>();
        sourceNode = nodesMap.get(1);
        sourceNode.setVisited(true);
        stack.addFirst(nodesMap.get(1));
        while (!stack.isEmpty()) {
            Node currentNode = stack.pollFirst();
            if (currentNode.getData() == nodes) {
                return true; // Found a path to the destination node
            }
            for (Node child : currentNode.getChildNodes()) {
                if (!child.isVisited()) {
                    child.setVisited(true);
                    stack.addFirst(child);
                }
            }
        }
        return false;
    }

    //one way to solve this problem is to use a map to store the graph, working
    private static boolean checkPathFromSourceToDestinationv4(int nodes, List<List<Integer>> directedEdges) {
        if (nodes == 1) return true; // Early exit if start == destination
        Map<Integer, List<Integer>> graph = new HashMap<>(nodes);
        for (List<Integer> directedEdge : directedEdges) {
            int start = directedEdge.get(0);
            int end = directedEdge.get(1);
            graph.computeIfAbsent(start, k -> new ArrayList<>()).add(end);
        }
        Set<Integer> visitedNodes = new HashSet<>();
        Deque<Integer> stack = new LinkedList<>();
        stack.addFirst(1);
        visitedNodes.add(1);
        while (!stack.isEmpty()) {
            Integer node = stack.pollFirst();
            List<Integer> childList = graph.getOrDefault(node, Collections.emptyList());
            for (Integer child : childList) {
                if (visitedNodes.add(child)) {
                    if (child.equals(nodes)) {
                        return true;
                    }
                    stack.addFirst(child);
                }
            }
        }
        return false;
    }

    // solution is derived using DFS, working
    private static boolean checkPathFromSourceToDestinationv3(int nodes, List<List<Integer>> directedEdges) {
        if (nodes == 1) return true; // Early exit if start == destination
        List<List<Integer>> graph = new ArrayList<>(nodes + 1);
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> edge : directedEdges) {
            graph.get(edge.get(0)).add(edge.get(1));
        }
        Set<Integer> visitedNodes = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(1);
        visitedNodes.add(1);
        while (!stack.isEmpty()) {
            Integer node = stack.pollFirst();
            List<Integer> childList = graph.get(node);
            for (Integer child : childList) {
                if (visitedNodes.add(child)) {
                    if (child.equals(nodes)) {
                        return true;
                    }
                    stack.addFirst(child);
                }
            }
        }
        return false;
    }

    // solution is derived using BFS, working
    private static boolean checkPathFromSourceToDestinationv2(int nodes, List<List<Integer>> directedEdges) {
        if (1 == nodes) return true; // Early exit if start == destination
        List<List<Integer>> graph = new ArrayList<>(nodes + 1);
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> edge : directedEdges) {
            graph.get(edge.get(0)).add(edge.get(1));
        }
        Set<Integer> visitedNodes = new HashSet<>();
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(1);
        visitedNodes.add(1);
        while (!queue.isEmpty()) {
            Integer node = queue.pollFirst();
            List<Integer> childList = graph.get(node);
            for (Integer child : childList) {
                if (visitedNodes.add(child)) {
                    if (child.equals(nodes)) {
                        return true;
                    }
                    queue.addLast(child);
                }
            }
        }
        return false;
    }

    private static boolean checkPathFromSourceToDestinationv1(int nodes, List<List<Integer>> directedEdges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
            for (int j = 0; j <= nodes; j++) {
                graph.get(i).add(0);
            }
        }
        for (List<Integer> edge : directedEdges) {
            graph.get(edge.get(0)).set(edge.get(1), 1);
        }

        Deque<NodeData> queue = new LinkedList<>();
        NodeData startNode = new NodeData(1);
        startNode.setVisited(true);
        queue.addLast(startNode);
        while (!queue.isEmpty()) {
            NodeData currentNode = queue.removeFirst();
            List<NodeData> childrenNodes = getNeighbouringNodes(currentNode, nodes, graph);
            for (NodeData childNode : childrenNodes) {
                if (!childNode.isVisited()) {
                    childNode.setVisited(true);
                    queue.addLast(childNode);
                    if (childNode.getData() == nodes) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static List<NodeData> getNeighbouringNodes(NodeData node, int nodes, List<List<Integer>> graph) {
        List<NodeData> neighbourList = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) {
            if (graph.get(node.getData()).get(i) == 1 && !node.isVisited())
                neighbourList.add(new NodeData(i));
        }
        return neighbourList;
    }
}
