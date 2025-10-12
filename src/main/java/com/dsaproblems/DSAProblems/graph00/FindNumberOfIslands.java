package com.dsaproblems.DSAProblems.graph00;

import lombok.Data;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class FindNumberOfIslands {

    @Data
    static class NodeData {

        private Integer row;
        private Integer col;
        private boolean isVisited;
        private Character data;

        public NodeData(Integer row, Integer col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return row + "," + col + "," + data + "," + isVisited;
        }

    }

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(1, 1, 0, 0, 1)));
        input.add(new ArrayList<>(Arrays.asList(0, 1, 0, 1, 0)));
        input.add(new ArrayList<>(Arrays.asList(1, 0, 0, 1, 1)));
        input.add(new ArrayList<>(Arrays.asList(1, 1, 0, 0, 0)));
        input.add(new ArrayList<>(Arrays.asList(1, 0, 1, 1, 1)));
        System.out.println(findAllIslands(input));

    }

    private static int findAllIslands(List<List<Integer>> input) {
        List<List<NodeData>> matrix = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            matrix.add(new ArrayList<>());
            for (int j = 0; j < input.get(i).size(); j++) {
                NodeData node = new NodeData(i, j);
                if (input.get(i).get(j) == 0) {
                    node.setVisited(true);
                }
                matrix.get(i).add(node);
            }
        }
        System.out.println(matrix);
        int ans = 0;
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (!matrix.get(i).get(j).isVisited()) {
                    ans++;
                    depthFirstSearch(matrix.get(i).get(j), matrix);
                }
            }
        }
        return ans;
    }

    private static void depthFirstSearch(NodeData start, List<List<NodeData>> matrix) {
        Deque<NodeData> stack = new ArrayDeque<>();
        start.setVisited(true);
        stack.addFirst(start);
        while (!stack.isEmpty()) {
            NodeData node = stack.removeFirst();
            List<NodeData> childList = getAllNeighbouringNodes1(node, matrix);
            for (NodeData child : childList) {
                if (!child.isVisited()) {
                    child.setVisited(true);
                    stack.addFirst(child);
                }
            }
        }
    }

    private static List<NodeData> getAllNeighbouringNodes(NodeData node, List<List<NodeData>> matrix) {
        List<NodeData> neighbourList = new ArrayList<>();
        int x = node.getRow();
        int y = node.getCol();
        Integer[] rows = {-1, 0, 0, 1};
        Integer[] cols = {0, -1, 1, 0};
        for (int i = 0; i < 4; i++) {
            if (x + rows[i] >= 0 && x + rows[i] < matrix.size() && y + cols[i] >= 0
                    && y + cols[i] < matrix.get(0).size() && !matrix.get(x + rows[i]).get(y + cols[i]).isVisited()) {
                neighbourList.add(matrix.get(x + rows[i]).get(y + cols[i]));
            }
        }
        return neighbourList;
    }

    private static List<NodeData> getAllNeighbouringNodes1(NodeData node, List<List<NodeData>> matrix) {
        List<NodeData> neighbourList = new ArrayList<>();
        int x = node.getRow();
        int y = node.getCol();
        Integer[] rows = {-1, 0, 0, 1, -1, -1, 1, 1};
        Integer[] cols = {0, -1, 1, 0, -1, 1, -1, 1};
        for (int i = 0; i < 8; i++) {
            if (x + rows[i] >= 0 && x + rows[i] < matrix.size() && y + cols[i] >= 0
                    && y + cols[i] < matrix.get(0).size() && !matrix.get(x + rows[i]).get(y + cols[i]).isVisited()) {
                neighbourList.add(matrix.get(x + rows[i]).get(y + cols[i]));
            }
        }
        return neighbourList;
    }

}
