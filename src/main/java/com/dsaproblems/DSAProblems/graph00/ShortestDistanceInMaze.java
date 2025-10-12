package com.dsaproblems.DSAProblems.graph00;

import java.util.*;

public class ShortestDistanceInMaze {

    static class Node {

        int row;

        int col;

        Integer distanceFromSource;

        boolean isVisited;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {

        List<List<Integer>> input = new ArrayList<>();
//        input.add(new ArrayList<>(Arrays.asList(0, 0, 0, 1, 0)));
//        input.add(new ArrayList<>(Arrays.asList(0, 1, 0, 0, 0)));
//        input.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0)));
//        input.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0)));
//        input.add(new ArrayList<>(Arrays.asList(1, 0, 0, 1, 0)));
//
//        List<Integer> B = new ArrayList<>(List.of(1, 0));
//        List<Integer> C = new ArrayList<>(List.of(1, 3));

        //[[1,1,0,1],[0,0,0,1],[1,0,0,1],[0,0,1,0]]
        input.add(new ArrayList<>(Arrays.asList(1, 1, 0, 1)));
        input.add(new ArrayList<>(Arrays.asList(0, 0, 0, 1)));
        input.add(new ArrayList<>(Arrays.asList(1, 0, 0, 1)));
        input.add(new ArrayList<>(Arrays.asList(0, 0, 1, 0)));

        List<Integer> B = new ArrayList<>(List.of(1, 1));
        List<Integer> C = new ArrayList<>(List.of(2, 1));

        System.out.println(findShortestPathv1(input, B, C));

    }

    //the solution in FindLengthOfShortestPath.java if used here is not correct
    private static int findShortestPathv1(List<List<Integer>> input, List<Integer> B, List<Integer> C) {
        List<List<Node>> graph = new ArrayList<>();
        Node sourceNode = new Node(B.get(0), B.get(1));
        sourceNode.distanceFromSource = 0;
        for (int i = 0; i < input.size(); i++) {
            graph.add(new ArrayList<>());
            for (int j = 0; j < input.get(i).size(); j++) {
                if (i == B.get(0) && j == B.get(1)) {
                    graph.get(i).add(sourceNode);
                } else {
                    if (input.get(i).get(j).equals(1)) {
                        graph.get(i).add(null);
                    } else {
                        graph.get(i).add(new Node(i, j));
                    }
                }

            }
        }
        Deque<Node> queue = new ArrayDeque<>();
        sourceNode.isVisited = true;
        queue.addLast(sourceNode);
        while (!queue.isEmpty()) {
            Node node = queue.pollFirst();
            List<Node> neighbourList = getAllNeighboringNodes(node, graph);
            int distanceFromSource = node.distanceFromSource;
            for (Node neighbour : neighbourList) {
                neighbour.distanceFromSource = distanceFromSource + 1;
                neighbour.isVisited = true;
                queue.addLast(neighbour);
            }
        }
        return graph.get(C.get(0)).get(C.get(1)).distanceFromSource == null ? -1 : graph.get(C.get(0)).get(C.get(1)).distanceFromSource;
    }

    private static List<Node> getAllNeighboringNodes(Node node, List<List<Node>> graph) {
        List<Node> neighbourList = new ArrayList<>();
        Integer[] rows = {-1, 0, 0, 1};
        Integer[] cols = {0, -1, 1, 0};
        int x = node.row;
        int y = node.col;
        for (int i = 0; i < 4; i++) {
            if (x + rows[i] >= 0 &&
                    x + rows[i] < graph.size() &&
                    y + cols[i] >= 0 &&
                    y + cols[i] < graph.get(0).size() &&
                    graph.get(x + rows[i]).get(y + cols[i]) != null &&
                    !graph.get(x + rows[i]).get(y + cols[i]).isVisited) {
                neighbourList.add(graph.get(x + rows[i]).get(y + cols[i]));
            }
        }
        return neighbourList;
    }
}
