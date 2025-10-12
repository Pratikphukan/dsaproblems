package com.dsaproblems.DSAProblems.graph02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import lombok.Data;

public class FindLengthOfShortestPath {

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(1, 1, 1, 0, 1)));
        input.add(new ArrayList<>(Arrays.asList(1, 0, 1, 1, 1)));
        input.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)));
        input.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)));
        input.add(new ArrayList<>(Arrays.asList(0, 1, 1, 0, 1)));

        NodeData source = new NodeData(1, 0);
        source.setDistanceFromSource(0);
        NodeData destination = new NodeData(3, 4);

        System.out.println(findShortestPath(input, source, destination));
    }

    private static Integer findShortestPath(List<List<Integer>> input, NodeData source, NodeData destination) {

        List<List<NodeData>> matrix = new ArrayList<>();
        NodeData sourceNode = null;
        for (int i = 0; i < input.size(); i++) {
            matrix.add(new ArrayList<>());
            for (int j = 0; j < input.get(i).size(); j++) {
                NodeData node = new NodeData(i, j);
                if (source.getRow() == i && source.getCol() == j) {
                    node.setDistanceFromSource(source.getDistanceFromSource());
                    sourceNode = node;
                } else {
                    node.setDistanceFromSource(input.get(i).get(j));
                }
                matrix.get(i).add(node);
            }
        }
        Set<NodeData> visitedNodes = new HashSet<>();
        Deque<NodeData> queue = new LinkedList<>();
        queue.addLast(sourceNode);
        visitedNodes.add(sourceNode);
        while (!queue.isEmpty()) {
            NodeData node = queue.pollFirst();
            List<NodeData> neighbourList = getAllNeighboringNodes(node, matrix);
            int distanceFromSource = node.getDistanceFromSource();
            for (NodeData neighbour : neighbourList) {
                if (!visitedNodes.contains(neighbour)) {
                    neighbour.setDistanceFromSource(distanceFromSource + 1);
                    queue.add(neighbour);
                    visitedNodes.add(neighbour);
                }
            }
        }
        System.out.println(matrix);
        int desti = destination.getRow();
        int destj = destination.getCol();
        return matrix.get(desti).get(destj).getDistanceFromSource();
    }

    private static List<NodeData> getAllNeighboringNodes(NodeData node, List<List<NodeData>> input) {
        List<NodeData> neighbourList = new ArrayList<>();
        int x = node.getRow();
        int y = node.getCol();
        if ((x - 1) >= 0 && input.get(x - 1).get(y).getDistanceFromSource() > 0) {
            neighbourList.add(input.get(x - 1).get(y));
        }
        if ((y - 1) >= 0 && input.get(x).get(y - 1).getDistanceFromSource() > 0) {
            neighbourList.add(input.get(x).get(y - 1));
        }
        if ((x + 1) < input.size() && input.get(x + 1).get(y).getDistanceFromSource() > 0) {
            neighbourList.add(input.get(x + 1).get(y));
        }
        if ((y + 1) < input.get(0).size() && input.get(x).get(y + 1).getDistanceFromSource() > 0) {
            neighbourList.add(input.get(x).get(y + 1));
        }
        return neighbourList;
    }

}
