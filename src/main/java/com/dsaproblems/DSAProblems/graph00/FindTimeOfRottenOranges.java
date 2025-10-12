package com.dsaproblems.DSAProblems.graph00;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class FindTimeOfRottenOranges {

    @Getter
    static class Node {
        private int row;

        private int col;

        @Setter
        private Integer timeToDamage;

        @Setter
        private CellStatus cellStatus;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "[" + row + ", " + col + ", " + timeToDamage + ", " + cellStatus + "]";
        }
    }

    @Getter
    static class NodeData {

        private Integer row;

        private Integer col;

        @Setter
        private Integer distanceFromSource;

        @Setter
        private CellStatus cellStatus;

        public NodeData(Integer row, Integer col) {
            super();
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
//        input.add(new ArrayList<>(Arrays.asList(2, 1, 1)));
//        input.add(new ArrayList<>(Arrays.asList(0, 1, 1)));
//        input.add(new ArrayList<>(Arrays.asList(1, 0, 1)));

        //[[2,0,2,2,2,0,2,1,1,0],
        // [0,1,2,0,2,0,0,1,0,1],
        // [0,1,1,1,2,0,1,1,2,1],
        // [2,0,2,0,1,1,2,1,0,1],
        // [1,0,1,1,0,1,2,0,2,2],
        // [0,2,1,1,2,2,0,2,1,2],
        // [2,1,0,2,0,0,0,0,1,1],
        // [2,2,0,2,2,1,1,1,2,2]]

        input.add(new ArrayList<>(Arrays.asList(2, 0, 2, 2, 2, 0, 2, 1, 1, 0)));
        input.add(new ArrayList<>(Arrays.asList(0, 1, 2, 0, 2, 0, 0, 1, 0, 1)));
        input.add(new ArrayList<>(Arrays.asList(0, 1, 1, 1, 2, 0, 1, 1, 2, 1)));
        input.add(new ArrayList<>(Arrays.asList(2, 0, 2, 0, 1, 1, 2, 1, 0, 1)));
        input.add(new ArrayList<>(Arrays.asList(1, 0, 1, 1, 0, 1, 2, 0, 2, 2)));
        input.add(new ArrayList<>(Arrays.asList(0, 2, 1, 1, 2, 2, 0, 2, 1, 2)));
        input.add(new ArrayList<>(Arrays.asList(2, 1, 0, 2, 0, 0, 0, 0, 1, 1)));
        input.add(new ArrayList<>(Arrays.asList(2, 2, 0, 2, 2, 1, 1, 1, 2, 2)));

        System.out.println(findMinimumTimeUntilNoFreshOrangeLeft(input));

        System.out.println(findMinimumTimeUntilNoFreshOrangeLeftv2(input));

        System.out.println(findMinimumTimeUntilNoFreshOrangeLeftv3(input));

        System.out.println(findMinimumTimeUntilNoFreshOrangeLeftv4(input));
    }

    //working code, TC->O(m*n), SC->O(m*n)
    private static int findMinimumTimeUntilNoFreshOrangeLeftv4(List<List<Integer>> input) {
        int rows = input.size(), cols = input.get(0).size();
        int[][] grid = new int[rows][cols];
        Deque<int[]> queue = new ArrayDeque<>();
        int freshCount = 0, time = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = input.get(i).get(j);
                if (grid[i][j] == 2) queue.addLast(new int[]{i, j, 0});
                if (grid[i][j] == 1) freshCount++;
            }
        }

        int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
        while (!queue.isEmpty()) {
            int[] curr = queue.pollFirst();
            int row = curr[0], col = curr[1], currTime = curr[2];
            time = Math.max(time, currTime);
            for (int i = 0; i < 4; i++) {
                if (row + dr[i] >= 0 &&
                        row + dr[i] < rows &&
                        col + dc[i] >= 0 &&
                        col + dc[i] < cols &&
                        grid[row + dr[i]][col + dc[i]] == 1) {
                    grid[row + dr[i]][col + dc[i]] = 2; // mark as rotten
                    freshCount--;
                    queue.addLast(new int[]{row + dr[i], col + dc[i], currTime + 1});
                }
            }
        }

        return freshCount == 0 ? time : -1;
    }

    //DFS will not work for this problem as we need the minimum time to damage all the fresh oranges
    private static int findMinimumTimeUntilNoFreshOrangeLeftv3(List<List<Integer>> input) {
        List<List<Node>> graph = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        for (int i = 0; i < input.size(); i++) {
            graph.add(new ArrayList<>());
            for (int j = 0; j < input.get(i).size(); j++) {
                Node node = new Node(i, j);
                if (input.get(i).get(j) == 2) {
                    node.setCellStatus(CellStatus.ROTTEN);
                    node.setTimeToDamage(0);
                    stack.addFirst(node);
                } else if (input.get(i).get(j) == 0) {
                    node.setCellStatus(CellStatus.EMPTY);
                } else {
                    node.setCellStatus(CellStatus.FRESH);
                }
                graph.get(i).add(node);
            }
        }
        while (!stack.isEmpty()) {
            Node node = stack.pollFirst();
            List<Node> neighbourList = getAllNeighboringNodes(node, graph);
            int timeToDamage = node.getTimeToDamage();
            for (Node neighbour : neighbourList) {
                neighbour.setTimeToDamage(timeToDamage + 1);
                neighbour.setCellStatus(CellStatus.ROTTEN);
                stack.addFirst(neighbour);
            }
        }
        int ans = 0;
        for (List<Node> row : graph) {
            for (Node node : row) {
                if (node.getCellStatus() == CellStatus.FRESH) {
                    return -1;
                }
                if (node.getCellStatus() == CellStatus.ROTTEN) {
                    ans = Math.max(ans, node.getTimeToDamage());
                }
            }
        }
        return ans;
    }

    //working code
    private static int findMinimumTimeUntilNoFreshOrangeLeftv2(List<List<Integer>> input) {
        List<List<Node>> graph = new ArrayList<>();
        Deque<Node> queue = new ArrayDeque<>();
        for (int i = 0; i < input.size(); i++) {
            graph.add(new ArrayList<>());
            for (int j = 0; j < input.get(i).size(); j++) {
                Node node = new Node(i, j);
                if (input.get(i).get(j) == 2) {
                    node.setCellStatus(CellStatus.ROTTEN);
                    node.setTimeToDamage(0);
                    queue.addLast(node);
                } else if (input.get(i).get(j) == 0) {
                    node.setCellStatus(CellStatus.EMPTY);
                } else {
                    node.setCellStatus(CellStatus.FRESH);
                }
                graph.get(i).add(node);
            }
        }
        while (!queue.isEmpty()) {
            Node node = queue.pollFirst();
            List<Node> neighbourList = getAllNeighboringNodes(node, graph);
            int timeToDamage = node.getTimeToDamage();
            for (Node neighbour : neighbourList) {
                neighbour.setTimeToDamage(timeToDamage + 1);
                neighbour.setCellStatus(CellStatus.ROTTEN);
                queue.addLast(neighbour);
            }
        }
        int ans = 0;
        for (List<Node> row : graph) {
            for (Node node : row) {
                if (node.getCellStatus() == CellStatus.FRESH) {
                    return -1;
                }
                if (node.getCellStatus() == CellStatus.ROTTEN) {
                    ans = Math.max(ans, node.getTimeToDamage());
                }
            }
        }
        return ans;
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
                    graph.get(x + rows[i]).get(y + cols[i]).getCellStatus().equals(CellStatus.FRESH)) {
                neighbourList.add(graph.get(x + rows[i]).get(y + cols[i]));
            }
        }
        return neighbourList;
    }

    private static int findMinimumTimeUntilNoFreshOrangeLeft(List<List<Integer>> input) {
        List<List<NodeData>> matrix = new ArrayList<>();
        Set<NodeData> visitedNodes = new HashSet<>();
        Deque<NodeData> queue = new LinkedList<>();
        for (int i = 0; i < input.size(); i++) {
            matrix.add(new ArrayList<>());
            for (int j = 0; j < input.get(i).size(); j++) {
                NodeData node = new NodeData(i, j);
                if (input.get(i).get(j) == 2) {
                    node.setCellStatus(CellStatus.ROTTEN);
                    node.setDistanceFromSource(0);
                    queue.addLast(node);
                    visitedNodes.add(node);
                } else if (input.get(i).get(j) == 0) {
                    node.setCellStatus(CellStatus.EMPTY);
                    node.setDistanceFromSource(-1);
                    visitedNodes.add(node);
                } else {
                    node.setCellStatus(CellStatus.FRESH);
                }
                matrix.get(i).add(node);
            }
        }
        System.out.println(matrix);
        while (!queue.isEmpty()) {
            NodeData node = queue.pollFirst();
            List<NodeData> neighbourList = getAllNeighbouringNodes(node, matrix);
            int distanceFromSource = node.getDistanceFromSource();
            for (NodeData neighbour : neighbourList) {
                if (!visitedNodes.contains(neighbour)) {
                    neighbour.setDistanceFromSource(distanceFromSource + 1);
                    queue.addLast(neighbour);
                    visitedNodes.add(neighbour);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).size(); j++) {
                if (matrix.get(i).get(j).getDistanceFromSource() == null) {
                    return -1;
                }
                ans = Math.max(ans, matrix.get(i).get(j).getDistanceFromSource());
            }
        }
        return ans;
    }

    private static List<NodeData> getAllNeighbouringNodes(NodeData node, List<List<NodeData>> matrix) {
        List<NodeData> neighbourList = new ArrayList<>();
        int x = node.getRow();
        int y = node.getCol();
        Integer[] rows = {-1, 0, 0, 1};
        Integer[] cols = {0, -1, 1, 0};
        for (int i = 0; i < 4; i++) {
            if (x + rows[i] >= 0 && x + rows[i] < matrix.size() && y + cols[i] >= 0
                    && y + cols[i] < matrix.get(0).size()
                    && matrix.get(x + rows[i]).get(y + cols[i]).getDistanceFromSource() == null) {
                neighbourList.add(matrix.get(x + rows[i]).get(y + cols[i]));
            }
        }
        return neighbourList;
    }

}
