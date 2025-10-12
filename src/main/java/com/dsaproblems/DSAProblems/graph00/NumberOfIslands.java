package com.dsaproblems.DSAProblems.graph00;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class NumberOfIslands {

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
        input.add(new ArrayList<>(Arrays.asList(1, 1, 0, 0, 1)));
        input.add(new ArrayList<>(Arrays.asList(0, 1, 0, 1, 0)));
        input.add(new ArrayList<>(Arrays.asList(1, 0, 0, 1, 1)));
        input.add(new ArrayList<>(Arrays.asList(1, 1, 0, 0, 0)));
        input.add(new ArrayList<>(Arrays.asList(1, 0, 1, 1, 1)));
        System.out.println(findAllIslandsv1(input));
        System.out.println(findAllIslandsv2(input));
    }

    //working code, both BFS and DFS are working fine
    private static int findAllIslandsv2(List<List<Integer>> input) {
        int rows = input.size();
        int cols = input.get(0).size();
        boolean[][] visited = new boolean[rows][cols];
        int connectedIslands = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (input.get(i).get(j) == 1 && !visited[i][j]) {
                    connectedIslands++;
                    dfs(input, visited, i, j);
                    //bfs(input, visited, i, j);
                }
            }
        }
        return connectedIslands;
    }

    private static void bfs(List<List<Integer>> input, boolean[][] visited, int i, int j) {
        int[] dx = {-1, 0, 0, 1, -1, -1, 1, 1};
        int[] dy = {0, -1, 1, 0, -1, 1, -1, 1};
        int rows = input.size();
        int cols = input.get(0).size();
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{
                i, j
        });
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            int[] cell = queue.removeFirst();
            int x = cell[0], y = cell[1];
            for (int d = 0; d < 8; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols &&
                        input.get(nx).get(ny) == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.addLast(new int[]{
                            nx, ny
                    });
                }
            }
        }
    }

    private static void dfs(List<List<Integer>> input, boolean[][] visited, int i, int j) {
        int[] dx = {-1, 0, 0, 1, -1, -1, 1, 1};
        int[] dy = {0, -1, 1, 0, -1, 1, -1, 1};
        int rows = input.size();
        int cols = input.get(0).size();
        Deque<int[]> stack = new ArrayDeque<>();
        stack.addFirst(new int[]{
                i, j
        });
        visited[i][j] = true;
        while (!stack.isEmpty()) {
            int[] cell = stack.removeFirst();
            int x = cell[0], y = cell[1];
            for (int d = 0; d < 8; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols &&
                        input.get(nx).get(ny) == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    stack.addFirst(new int[]{
                            nx, ny
                    });
                }
            }
        }
    }

    //working code, both BFS and DFS are working fine
    private static int findAllIslandsv1(List<List<Integer>> input) {
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            graph.add(new ArrayList<>());
            for (int j = 0; j < input.get(i).size(); j++) {
                Node node = new Node(i, j);
                if (input.get(i).get(j) == 0) {
                    node.setCellStatus(CellStatus.BLOCKED);
                } else {
                    node.setCellStatus(CellStatus.NOTVISITED);
                }
                graph.get(i).add(node);
            }
        }
        int connectedIslands = 0;
        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.get(i).size(); j++) {
                if (graph.get(i).get(j).getCellStatus().equals(CellStatus.NOTVISITED)) {
                    connectedIslands++;
                    //breadthFirstSearch(graph.get(i).get(j), graph);
                    depthFirstSearch(graph.get(i).get(j), graph);
                }
            }
        }
        return connectedIslands;
    }

    private static void breadthFirstSearch(Node node, List<List<Node>> graph) {
        Deque<Node> queue = new LinkedList<>();
        node.setCellStatus(CellStatus.VISITED);
        queue.addLast(node);
        while (!queue.isEmpty()) {
            Node currentNode = queue.pollFirst();
            List<Node> childList = getAllNeighbouringNodes(currentNode, graph);
            for (Node child : childList) {
                if (child.getCellStatus().equals(CellStatus.NOTVISITED)) {
                    child.setCellStatus(CellStatus.VISITED);
                    queue.addLast(child);
                }
            }
        }
    }

    private static void depthFirstSearch(Node start, List<List<Node>> graph) {
        Deque<Node> stack = new LinkedList<>();
        start.setCellStatus(CellStatus.VISITED);
        stack.addFirst(start);
        while (!stack.isEmpty()) {
            Node node = stack.removeFirst();
            List<Node> childList = getAllNeighbouringNodes(node, graph);
            for (Node child : childList) {
                if (child.getCellStatus().equals(CellStatus.NOTVISITED)) {
                    child.setCellStatus(CellStatus.VISITED);
                    stack.addFirst(child);
                }
            }
        }
    }

    private static List<Node> getAllNeighbouringNodes(Node node, List<List<Node>> graph) {
        List<Node> neighbourList = new ArrayList<>();
        int rowSize = graph.size();
        int colSize = graph.get(0).size();
        int x = node.getRow();
        int y = node.getCol();
        Integer[] rows = {-1, 0, 0, 1, -1, -1, 1, 1};
        Integer[] cols = {0, -1, 1, 0, -1, 1, -1, 1};
        for (int i = 0; i < 8; i++) {
            if (x + rows[i] >= 0 &&
                    x + rows[i] < rowSize &&
                    y + cols[i] >= 0 &&
                    y + cols[i] < colSize &&
                    graph.get(x + rows[i]).get(y + cols[i]).getCellStatus().equals(CellStatus.NOTVISITED)) {
                neighbourList.add(graph.get(x + rows[i]).get(y + cols[i]));
            }
        }
        return neighbourList;
    }
}
