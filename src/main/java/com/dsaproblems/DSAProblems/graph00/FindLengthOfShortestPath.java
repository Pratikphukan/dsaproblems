package com.dsaproblems.DSAProblems.graph00;

import java.util.*;

import com.dsaproblems.DSAProblems.graph02.NodeData;
import lombok.Getter;
import lombok.Setter;

public class FindLengthOfShortestPath {

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
    }

    //for finding the shortest path in a grid from source to destination, we need to use BFS not DFS
    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(1, 1, 1, 0, 1)));
        input.add(new ArrayList<>(Arrays.asList(1, 0, 1, 1, 1)));
        input.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)));
        input.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)));
        input.add(new ArrayList<>(Arrays.asList(0, 1, 1, 0, 1)));

        Integer srcx = 1;
        Integer srcy = 0;
        Integer desx = 1;
        Integer desy = 3;

        System.out.println(findShortestPath(input, srcx, srcy, desx, desy));
        System.out.println(findShortestPathv2(input, srcx, srcy, desx, desy));
        System.out.println(findShortestPathv3(input, srcx, srcy, desx, desy));
        System.out.println(findShortestPathv4(input, srcx, srcy, desx, desy));
    }

    private static int findShortestPathv4(List<List<Integer>> input, Integer srcx, Integer srcy, Integer desx, Integer desy) {
        int rows = input.size();
        int cols = input.get(0).size();
        boolean[][] visited = new boolean[rows][cols];
        int[][] distance = new int[rows][cols];
        for (int[] row : distance) Arrays.fill(row, -1);

        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{srcx, srcy});
        visited[srcx][srcy] = true;
        distance[srcx][srcy] = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.pollFirst();
            int x = curr[0], y = curr[1];
            if (x == desx && y == desy) return distance[x][y];
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir], ny = y + dy[dir];
                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols &&
                        input.get(nx).get(ny) == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    distance[nx][ny] = distance[x][y] + 1;
                    queue.addLast(new int[]{nx, ny});
                }
            }
        }
        return distance[desx][desy];
    }

    //using DFS, won't work for all cases
    private static Integer findShortestPathv3(List<List<Integer>> input, Integer srcx, Integer srcy, Integer desx, Integer desy) {
        List<List<Node>> graph = new ArrayList<>();
        Node sourceNode = new Node(srcx, srcy);
        sourceNode.setDistanceFromSource(0);
        for (int i = 0; i < input.size(); i++) {
            graph.add(new ArrayList<>());
            for (int j = 0; j < input.get(i).size(); j++) {
                if (i == srcx && j == srcy) {
                    graph.get(i).add(sourceNode);
                } else {
                    if (input.get(i).get(j).equals(1)) {
                        graph.get(i).add(new Node(i, j));
                    } else {
                        graph.get(i).add(null);
                    }
                }

            }
        }
        Deque<Node> stack = new ArrayDeque<>();
        sourceNode.setVisited(true);
        stack.addFirst(sourceNode);
        while (!stack.isEmpty()) {
            Node node = stack.pollFirst();
            List<Node> neighbourList = getAllNeighboringNodes(node, graph);
            int distanceFromSource = node.getDistanceFromSource();
            for (Node neighbour : neighbourList) {
                neighbour.setDistanceFromSource(distanceFromSource + 1);
                neighbour.setVisited(true);
                stack.addFirst(neighbour);
            }
        }
        return graph.get(desx).get(desy).getDistanceFromSource();
    }

    //using BFS
    private static Integer findShortestPathv2(List<List<Integer>> input, Integer srcx, Integer srcy, Integer desx, Integer desy) {
        List<List<Node>> graph = new ArrayList<>();
        Node sourceNode = new Node(srcx, srcy);
        sourceNode.setDistanceFromSource(0);
        for (int i = 0; i < input.size(); i++) {
            graph.add(new ArrayList<>());
            for (int j = 0; j < input.get(i).size(); j++) {
                if (i == srcx && j == srcy) {
                    graph.get(i).add(sourceNode);
                } else {
                    if (input.get(i).get(j).equals(1)) {
                        graph.get(i).add(new Node(i, j));
                    } else {
                        graph.get(i).add(null);
                    }
                }

            }
        }
        Deque<Node> queue = new ArrayDeque<>();
        sourceNode.setVisited(true);
        queue.addLast(sourceNode);
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
        return graph.get(desx).get(desy).getDistanceFromSource();
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

    private static int findShortestPath(List<List<Integer>> input, Integer srcx, Integer srcy, Integer desx,
                                        Integer desy) {
        List<List<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            matrix.add(new ArrayList<>());
            for (int j = 0; j < input.get(i).size(); j++) {
                if (i == srcx && j == srcy) {
                    matrix.get(i).add(0);
                } else {
                    if (input.get(i).get(j).equals(0)) {
                        matrix.get(i).add(-1);
                    } else {
                        matrix.get(i).add(null);
                    }
                }
            }
        }
        NodeData sourceNode = new NodeData(srcx, srcy);
        sourceNode.setDistanceFromSource(0);
        Set<NodeData> visitedNodes = new HashSet<>();
        Deque<NodeData> queue = new LinkedList<>();
        queue.addLast(sourceNode);
        visitedNodes.add(sourceNode);
        while (!queue.isEmpty()) {
            NodeData node = queue.pollFirst();
            List<NodeData> neighbourList = getAllNeighboringNodes(node.getRow(), node.getCol(), matrix);
            int distanceFromSource = node.getDistanceFromSource();
            for (NodeData neighbour : neighbourList) {
                if (!visitedNodes.contains(neighbour)) {
                    neighbour.setDistanceFromSource(distanceFromSource + 1);
                    matrix.get(neighbour.getRow()).set(neighbour.getCol(), distanceFromSource + 1);
                    visitedNodes.add(neighbour);
                    queue.addLast(neighbour);
                }
            }
        }
        return matrix.get(desx).get(desy);
    }

    private static List<NodeData> getAllNeighboringNodes(Integer x, Integer y, List<List<Integer>> matrix) {
        List<NodeData> neighbourList = new ArrayList<>();
        if ((x - 1) >= 0 && matrix.get(x - 1).get(y) == null) {
            neighbourList.add(new NodeData(x - 1, y));
        }
        if ((y - 1) >= 0 && matrix.get(x).get(y - 1) == null) {
            neighbourList.add(new NodeData(x, y - 1));
        }
        if ((x + 1) < matrix.size() && matrix.get(x + 1).get(y) == null) {
            neighbourList.add(new NodeData(x + 1, y));
        }
        if ((y + 1) < matrix.get(0).size() && matrix.get(x).get(y + 1) == null) {
            neighbourList.add(new NodeData(x, y + 1));
        }
        return neighbourList;
    }
}
