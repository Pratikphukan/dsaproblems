package com.dsaproblems.DSAProblems.graph00;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ValidPath {

    static class Node {

        private int row;

        private int col;

        private CellStatus cellStatus;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public CellStatus getCellStatus() {
            return cellStatus;
        }

        public void setCellStatus(CellStatus cellStatus) {
            this.cellStatus = cellStatus;
        }

        @Override
        public String toString() {
            return "[" + row + ", " + col + ", " + cellStatus + "]";
        }
    }

    public static void main(String[] args) {
        int A = 1;
        int B = 1;
        int C = 1;
        int D = 1;
        ArrayList<Integer> E = new ArrayList<>(List.of(0));
        ArrayList<Integer> F = new ArrayList<>(List.of(0));
        System.out.println(findValidPathv1(A, B, C, D, E, F));
    }

    //working code, both BFS and DFS are working fine
    private static String findValidPathv1(int A, int B, int C, int D, ArrayList<Integer> E, ArrayList<Integer> F) {
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= A; i++) {
            List<Node> row = new ArrayList<>();
            for (int j = 0; j <= B; j++) {
                Node node = new Node(i, j);
                node.setCellStatus(CellStatus.NOTVISITED);
                row.add(node);
                for (int k = 0; k < C; k++) {
                    if (isInsideCircle(i, j, E.get(k), F.get(k), D)) {
                        node.setCellStatus(CellStatus.NOT_ALLOWED);
                        break;
                    }
                }
            }
            graph.add(row);
        }
        if (breadthFirstSearch(graph, graph.get(0).get(0), graph.get(A).get(B))) {
            return "YES";
        }
        return "NO";
    }

    private static boolean depthFirstSearch(List<List<Node>> graph, Node start, Node end) {
        if (start.getCellStatus().equals(CellStatus.NOT_ALLOWED) ||
                end.getCellStatus().equals(CellStatus.NOT_ALLOWED)) {
            return false;
        }
        Deque<Node> stack = new ArrayDeque<>();
        start.setCellStatus(CellStatus.VISITED);
        stack.addFirst(start);
        while (!stack.isEmpty()) {
            Node node = stack.removeFirst();
            List<Node> childList = getAllNeighbouringNodes(graph, node);
            for (Node child : childList) {
                if (child.equals(end)) {
                    return true;
                }
                child.setCellStatus(CellStatus.VISITED);
                stack.addFirst(child);
            }
        }
        return false;
    }

    private static boolean breadthFirstSearch(List<List<Node>> graph, Node start, Node end) {
        if (start.getCellStatus().equals(CellStatus.NOT_ALLOWED) ||
                end.getCellStatus().equals(CellStatus.NOT_ALLOWED)) {
            return false;
        }
        Deque<Node> queue = new ArrayDeque<>();
        start.setCellStatus(CellStatus.VISITED);
        queue.addLast(start);
        while (!queue.isEmpty()) {
            Node node = queue.removeFirst();
            List<Node> childList = getAllNeighbouringNodes(graph, node);
            for (Node child : childList) {
                if (child.equals(end)) {
                    return true;
                }
                child.setCellStatus(CellStatus.VISITED);
                queue.addLast(child);
            }
        }
        return false;
    }

    private static List<Node> getAllNeighbouringNodes(List<List<Node>> graph, Node node) {
        List<Node> neighbourList = new ArrayList<>();
        int x = node.getRow();
        int y = node.getCol();
        Integer[] rows = {-1, 0, 0, 1, -1, -1, 1, 1};
        Integer[] cols = {0, -1, 1, 0, -1, 1, -1, 1};
        for (int i = 0; i < 8; i++) {
            if (x + rows[i] >= 0 &&
                    x + rows[i] < graph.size() &&
                    y + cols[i] >= 0 &&
                    y + cols[i] < graph.get(0).size() &&
                    graph.get(x + rows[i]).get(y + cols[i]).getCellStatus().equals(CellStatus.NOTVISITED)) {
                neighbourList.add(graph.get(x + rows[i]).get(y + cols[i]));
            }
        }
        return neighbourList;
    }


    private static boolean isInsideCircle(int x, int y, int centerX, int centerY, int radius) {
        return (x - centerX) * (x - centerX) + (y - centerY) * (y - centerY) <= radius * radius;
    }
}
