package com.dsaproblems.DSAProblems.graph00;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class FindDistanceOfHospitalFromEveryHouse {

    @Getter
    @Setter
    static class NodeData {

        private Integer row;

        private Integer col;

        private Integer distanceFromSource;

        public NodeData(Integer row, Integer col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "[" + row + ", " + col + ", " + distanceFromSource + "]";
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof NodeData nodeData))
                return false;
            return Objects.equals(row, nodeData.row) && Objects.equals(col, nodeData.col);
        }
    }

    @Getter
    @Setter
    static class Node {

        private int row;

        private int col;

        private Integer distanceFromSource;

        private boolean isVisited;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "[" + row + ", " + col + ", " + distanceFromSource + "]";
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(1, 1, 1, 0)));
        input.add(new ArrayList<>(Arrays.asList(1, 1, 0, 0)));
        input.add(new ArrayList<>(Arrays.asList(1, 0, 0, 1)));
        System.out.println(findAllPathsv1(input));

        System.out.println(findAllPathsv2(input));
    }

    //working code using BFS, DFS will not work here
    private static List<List<Node>> findAllPathsv2(List<List<Integer>> input) {
        List<List<Node>> graph = new ArrayList<>();
        Deque<Node> queue = new ArrayDeque<>();
        for (int i = 0; i < input.size(); i++) {
            graph.add(new ArrayList<>());
            for (int j = 0; j < input.get(i).size(); j++) {
                Node node = new Node(i, j);
                if (input.get(i).get(j).equals(0)) {
                    node.setDistanceFromSource(0);
                    node.setVisited(true);
                    queue.addLast(node);
                }
                graph.get(i).add(node);
            }
        }
        while (!queue.isEmpty()) {
            Node node = queue.pollFirst();
            List<Node> neighbourList = getAllNeighboringNodes(node, graph);
            int distanceFromSource = node.getDistanceFromSource();
            for (Node neighbour : neighbourList) {
                neighbour.setDistanceFromSource(distanceFromSource + 1);
                neighbour.setVisited(true);
                queue.addLast(neighbour);
            }
        }
        return graph;
    }

    private static List<Node> getAllNeighboringNodes(Node node, List<List<Node>> graph) {
        List<Node> neighbourList = new ArrayList<>();
        Integer[] rows = {-1, 0, 0, 1};
        Integer[] cols = {0, -1, 1, 0};
        int x = node.getRow();
        int y = node.getCol();
        for (int i = 0; i < 4; i++) {
            if (x + rows[i] >= 0 &&
                    x + rows[i] < graph.size() &&
                    y + cols[i] >= 0 &&
                    y + cols[i] < graph.get(0).size() &&
                    graph.get(x + rows[i]).get(y + cols[i]) != null &&
                    !graph.get(x + rows[i]).get(y + cols[i]).isVisited()) {
                neighbourList.add(graph.get(x + rows[i]).get(y + cols[i]));
            }
        }
        return neighbourList;
    }

    private static List<List<NodeData>> findAllPathsv1(List<List<Integer>> input) {
        List<List<NodeData>> graph = new ArrayList<>();
        Set<NodeData> visitedNodes = new HashSet<>();
        Deque<NodeData> queue = new LinkedList<>();
        for (int i = 0; i < input.size(); i++) {
            graph.add(new ArrayList<>());
            for (int j = 0; j < input.get(i).size(); j++) {
                NodeData node = new NodeData(i, j);
                if (input.get(i).get(j) == 0) {
                    node.setDistanceFromSource(0);
                    visitedNodes.add(node);
                    queue.addLast(node);
                }
                graph.get(i).add(node);
            }
        }
        while (!queue.isEmpty()) {
            NodeData node = queue.pollFirst();
            List<NodeData> neighbourList = getAllNeighbouringNodes(node, graph);
            int distanceFromSource = node.getDistanceFromSource();
            for (NodeData neighbour : neighbourList) {
                if (!visitedNodes.contains(neighbour)) {
                    neighbour.setDistanceFromSource(distanceFromSource + 1);
                    queue.add(neighbour);
                    visitedNodes.add(neighbour);
                }
            }
        }
        return graph;
    }

    private static List<NodeData> getAllNeighbouringNodes(NodeData node, List<List<NodeData>> matrix) {
        List<NodeData> neighbourList = new ArrayList<>();
        int x = node.getRow();
        int y = node.getCol();
        Integer[] rows = {-1, 0, 0, 1};
        Integer[] cols = {0, -1, 1, 0};
        for (int i = 0; i < 4; i++) {
            if (x + rows[i] >= 0 &&
                    x + rows[i] < matrix.size() &&
                    y + cols[i] >= 0 &&
                    y + cols[i] < matrix.get(0).size() &&
                    matrix.get(x + rows[i]).get(y + cols[i]).getDistanceFromSource() == null) {
                neighbourList.add(matrix.get(x + rows[i]).get(y + cols[i]));
            }
        }
        return neighbourList;
    }

}
