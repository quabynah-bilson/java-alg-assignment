package com.assignment;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Search algorithms implementation
 */
public class SearchAlgorithms {
    private final LinkedList<Integer>[] adjacencyGraph;
    private static final Stack<Integer> stack = new Stack<>();
    private static int numberOfNodes;
    private static int depth;
    private static int maxDepth;
    private static boolean goalFound = false;

    public SearchAlgorithms(int v) {
        adjacencyGraph = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adjacencyGraph[i] = new LinkedList<>();
        }
    }

    /**
     * Add an edge to the graph
     *
     * @param source      starting point
     * @param destination ending point
     */
    public void addGraphEdge(int source, int destination) {
        adjacencyGraph[source].add(destination);
        adjacencyGraph[destination].add(source);
    }

    /**
     * BFS
     */
    public void bfs(int source) {
        // visited edges (all are marked as not visited to start with)
        boolean[] visited = new boolean[adjacencyGraph.length];

        // queue for the BFS
        Queue<Integer> queue = new LinkedList<Integer>();

        // mark the current node as visited
        visited[source] = true;
        queue.add(source);

        while (!queue.isEmpty()) {
            // dequeue the vertex from the existing queue.
            // this removes the first item from the queue
            source = queue.poll();
            System.out.printf("Vertex visited -> %d%n", source);

            // get all adjacent vertices and mark as true if visited
            for (int neighbour : adjacencyGraph[source]) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
    }

    /**
     * DFS
     */
    private void dfsIterator(int vertex, boolean[] visited) {
        // Mark the current node as visited and print it
        visited[vertex] = true;
        System.out.printf("visited node -> %d%n", vertex);

        // Recur for all the vertices adjacent to this
        // vertex
        for (int n : adjacencyGraph[vertex]) {
            if (!visited[n])
                dfsIterator(n, visited);
        }
    }

    public void dfs(int source) {
        // visited edges (all are marked as not visited to start with)
        boolean[] visited = new boolean[adjacencyGraph.length];

        dfsIterator(source, visited);
    }

    /**
     * Iterative Deepening
     */
    public static void iterativeDeepening(int[][] adjacencyMatrix, int destination) {
        numberOfNodes = adjacencyMatrix[1].length - 1;
        while (!goalFound) {
            depthLimitedSearch(adjacencyMatrix, 1, destination);
            maxDepth++;
        }
        System.out.println("\nGoal Found at depth " + depth);
    }

    private static void depthLimitedSearch(int[][] adjacencyMatrix, int source, int goal) {
        int element, destination = 1;
        int[] visited = new int[numberOfNodes + 1];
        stack.push(source);
        depth = 0;
        System.out.println("\nAt Depth " + maxDepth);
        System.out.print(source + "\t");

        while (!stack.isEmpty()) {
            element = stack.peek();
            while (destination <= numberOfNodes) {
                if (depth < maxDepth) {
                    if (adjacencyMatrix[element][destination] == 1) {
                        stack.push(destination);
                        visited[destination] = 1;
                        System.out.print(destination + "\t");
                        depth++;
                        if (goal == destination) {
                            goalFound = true;
                            return;
                        }
                        element = destination;
                        destination = 1;
                        continue;
                    }
                } else {
                    break;
                }
                destination++;
            }
            destination = stack.pop() + 1;
            depth--;
        }
    }

    public static void main(String[] args) {
        SearchAlgorithms g = new SearchAlgorithms(4);

        g.addGraphEdge(0, 1);
        g.addGraphEdge(0, 2);
        g.addGraphEdge(1, 2);
        g.addGraphEdge(2, 0);
        g.addGraphEdge(2, 3);
        g.addGraphEdge(3, 3);

        System.out.println("Following is DFS (starting from vertex 2)");


    }
}
