package com.dsaproblems.DSAProblems.graph00;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class NumberOfLandAreasConverted {

    @Getter
    @Setter
    static class Node {

        private int row;

        private int col;

        private CellStatus cellStatus;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "[" + row + ", " + col + ", " + cellStatus + "]";
        }
    }

    public static void main(String[] args) {

        List<List<Integer>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(1, 1, 0, 1, 1)));
        input.add(new ArrayList<>(Arrays.asList(1, 0, 0, 1, 1)));
        input.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)));
        input.add(new ArrayList<>(Arrays.asList(1, 0, 1, 1, 0)));
        input.add(new ArrayList<>(Arrays.asList(1, 0, 0, 1, 0)));
        input.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 0)));
        input.add(new ArrayList<>(Arrays.asList(1, 1, 1, 0, 1)));
        System.out.println(findConvertedLandsv1(input));
        System.out.println(findConvertedLandsv2(input));

    }

    //time complexity of O(n * m), where n is the number of rows and m is the number of columns.
    // Every cell is visited at most once during marking and counting
    // SC: O(n * m) for the visited boolean array
    private static int findConvertedLandsv2(List<List<Integer>> input) {
        int n = input.size(), m = input.get(0).size();
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            if (input.get(i).get(0) == 0 && !visited[i][0]) {
                mark(i, 0, input, visited);
            }
            if (input.get(i).get(m - 1) == 0 && !visited[i][m - 1]) {
                mark(i, m - 1, input, visited);
            }
        }
        for (int i = 0; i < m; i++) {
            if (input.get(0).get(i) == 0 && !visited[0][i]) {
                mark(0, i, input, visited);
            }
            if (input.get(n - 1).get(i) == 0 && !visited[n - 1][i]) {
                mark(n - 1, i, input, visited);
            }
        }

        int converted = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (input.get(i).get(j) == 0 && !visited[i][j]) {
                    converted++;
                    mark(i, j, input, visited);
                }
            }
        }
        return converted;
    }

    private static void mark(int x, int y, List<List<Integer>> input, boolean[][] visited) {
        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addFirst(new int[]{x, y});
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.pollFirst();
            for (int d = 0; d < 4; d++) {
                int nx = curr[0] + dx[d], ny = curr[1] + dy[d];
                if (nx >= 0 &&
                        nx < input.size() &&
                        ny >= 0 &&
                        ny < input.get(0).size() &&
                        input.get(nx).get(ny) == 0 &&
                        !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.addLast(new int[]{nx, ny});
                }
            }
        }
    }

    private static int findConvertedLandsv1(List<List<Integer>> input) {
        int rows = input.size();
        int cols = input.get(0).size();
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            graph.add(new ArrayList<>());
            for (int j = 0; j < cols; j++) {
                Node node = new Node(i, j);
                node.setCellStatus(input.get(i).get(j) == 0 ? CellStatus.UNOCCUPIED : CellStatus.OCCUPIED);
                graph.get(i).add(node);
            }
        }
        // Mark unreachable cells from boundaries
        for (int i = 0; i < rows; i++) {
            markBoundaryCells(graph.get(i).get(0), graph, CellStatus.UNREACHABLE);
            markBoundaryCells(graph.get(i).get(cols - 1), graph, CellStatus.UNREACHABLE);
        }
        for (int j = 0; j < cols; j++) {
            markBoundaryCells(graph.get(0).get(j), graph, CellStatus.UNREACHABLE);
            markBoundaryCells(graph.get(rows - 1).get(j), graph, CellStatus.UNREACHABLE);
        }

        int convertedLands = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (graph.get(i).get(j).getCellStatus().equals(CellStatus.UNOCCUPIED)) {
                    convertedLands += 1;
                    //depthFirstSearch(graph.get(i).get(j), graph, CellStatus.OCCUPIED);
                    breadthFirstSearch(graph.get(i).get(j), graph, CellStatus.OCCUPIED);
                }
            }
        }
        return convertedLands;
    }

    private static void markBoundaryCells(Node node, List<List<Node>> graph, CellStatus cellStatus) {
        if (node.getCellStatus().equals(CellStatus.UNOCCUPIED)) {
            breadthFirstSearch(node, graph, cellStatus);
            //depthFirstSearch(node, graph, cellStatus);
        }
    }

    private static void breadthFirstSearch(Node node, List<List<Node>> graph, CellStatus cellStatus) {
        Deque<Node> queue = new LinkedList<>();
        node.setCellStatus(cellStatus);
        queue.addLast(node);
        while (!queue.isEmpty()) {
            Node currentNode = queue.pollFirst();
            List<Node> childList = getAllNeighbouringNodes(currentNode, graph);
            for (Node child : childList) {
                if (child.getCellStatus().equals(CellStatus.UNOCCUPIED)) {
                    child.setCellStatus(cellStatus);
                    queue.addLast(child);
                }
            }
        }
    }


    private static void depthFirstSearch(Node start, List<List<Node>> graph, CellStatus cellStatus) {
        Deque<Node> stack = new LinkedList<>();
        start.setCellStatus(cellStatus);
        stack.addFirst(start);
        while (!stack.isEmpty()) {
            Node node = stack.removeFirst();
            List<Node> childList = getAllNeighbouringNodes(node, graph);
            for (Node child : childList) {
                if (child.getCellStatus().equals(CellStatus.UNOCCUPIED)) {
                    child.setCellStatus(cellStatus);
                    stack.addFirst(child);
                }
            }
        }
    }

    private static List<Node> getAllNeighbouringNodes(Node node, List<List<Node>> graph) {
        List<Node> neighbourList = new ArrayList<>();
        int x = node.getRow();
        int y = node.getCol();
        Integer[] rows = {-1, 0, 0, 1};
        Integer[] cols = {0, -1, 1, 0};
        for (int i = 0; i < 4; i++) {
            if (x + rows[i] >= 0 &&
                    x + rows[i] < graph.size() &&
                    y + cols[i] >= 0 &&
                    y + cols[i] < graph.get(0).size() &&
                    graph.get(x + rows[i]).get(y + cols[i]).getCellStatus().equals(CellStatus.UNOCCUPIED)) {
                neighbourList.add(graph.get(x + rows[i]).get(y + cols[i]));
            }
        }
        return neighbourList;
    }
}
