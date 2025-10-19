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


        int[][] maze3 = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}
        };

        int[] start3 = {0, 4};
        int[] dest3 = {4, 4};

        System.out.println(shortestDistancePathv1(maze3, start3, dest3));
    }

    private static int shortestDistancePathv1(int[][] maze, int[] start, int[] destination) {
        // Get dimensions of the maze.
        int m = maze.length, n = maze[0].length;
        // Create a 2D array to track the minimum distance to reach each cell.
        // Initialize with maximum values to simulate infinity.
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        // Distance to the starting point is 0.
        dist[start[0]][start[1]] = 0;

        // Priority queue to implement Dijkstra's algorithm.
        // Stores an array representing {current distance, row index, column index}.
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (a, b) -> a[0] - b[0]   // Compare based on total traveled distance.
        );
        // Add the start state to the queue.
        queue.offer(new int[]{0, start[0], start[1]});

        // Direction vectors for moving up, right, down, left.
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        // Process the queue until empty.
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curDist = current[0];
            int x = current[1];
            int y = current[2];

            // If the current position equals the destination, return the distance.
            if (x == destination[0] && y == destination[1]) {
                return curDist;
            }

            // If we reached this cell by a shorter route before, skip processing.
            if (curDist > dist[x][y]) {
                continue;
            }

            // Try rolling in each direction.
            for (int[] dir : directions) {
                int newX = x;
                int newY = y;
                int count = 0; // Count the distance traveled in this roll.

                // Roll the ball until hitting a wall or boundary.
                // Check the next position's boundaries and ensure it is not a wall.
                while (newX + dir[0] >= 0 && newX + dir[0] < m &&
                        newY + dir[1] >= 0 && newY + dir[1] < n &&
                        maze[newX + dir[0]][newY + dir[1]] == 0) {
                    newX += dir[0];  // Move ball in the current direction.
                    newY += dir[1];  // Move ball in the current direction.
                    count++;         // Increase the distance count by 1.
                }

                // Check if the new calculated distance is shorter than previous for newX, newY.
                if (curDist + count < dist[newX][newY]) {
                    dist[newX][newY] = curDist + count;
                    // Add new state to the queue.
                    queue.offer(new int[]{dist[newX][newY], newX, newY});
                }
            }
        }
        // If the destination is not reachable, return -1.
        return -1;
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
