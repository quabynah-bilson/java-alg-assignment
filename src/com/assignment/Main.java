package com.assignment;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // ask user to select an option
        System.out.println("Select any of the search algorithms below:");
        System.out.println("1. BFS (Breadth First Search)");
        System.out.println("2. DFS (Depth First Search)");
        System.out.println("3. Iterative Deepening");
        System.out.println("4. A* Search");

        // get user input
        var userInput = new Scanner(System.in);
        var searchAlgSelected = userInput.nextInt();
        System.out.printf("User input -> %d%n", searchAlgSelected);

        // trigger algorithm based on user's initial option
        if (searchAlgSelected == 1) {
            // get vertices for graph
            System.out.println("Provide the number of vertices for this graph:");
            var vertices = userInput.nextInt();

            // get edges for graph
            System.out.println("Provide the number of edges as well:");
            var edges = userInput.nextInt();

            // create a new graph with the provided vertices
            var graph = new SearchAlgorithms(vertices);
            System.out.printf("Provide all %d edges (Press enter after each entry):%n", edges);
            for (int i = 0; i < edges; i++) {
                // get the source
                var src = userInput.nextInt();
                // get the destination
                var dest = userInput.nextInt();

                // add the edge recursively
                graph.addGraphEdge(src, dest);
            }

            // source
            System.out.printf("Enter your source:%n");
            var source = userInput.nextInt();

            // perform BFS
            graph.bfs(source);
        } else if (searchAlgSelected == 2) {
            // get vertices for graph
            System.out.println("Provide the number of vertices for this graph:");
            var vertices = userInput.nextInt();

            // get edges for graph
            System.out.println("Provide the number of edges as well:");
            var edges = userInput.nextInt();

            // create a new graph with the provided vertices
            var graph = new SearchAlgorithms(vertices);
            System.out.printf("Provide all %d edges (Press enter after each entry):%n", edges);
            for (int i = 0; i < edges; i++) {
                // get the source
                var src = userInput.nextInt();
                // get the destination
                var dest = userInput.nextInt();

                // add the edge recursively
                graph.addGraphEdge(src, dest);
            }

            // source
            System.out.printf("Enter your source:%n");
            var source = userInput.nextInt();

            // perform DFS
            graph.dfs(source);
        } else if (searchAlgSelected == 3) {
            int number_of_nodes, destination;
            try {
                System.out.println("Enter the number of nodes in the graph");
                number_of_nodes = userInput.nextInt();

                int[][] adjacency_matrix = new int[number_of_nodes + 1][number_of_nodes + 1];
                System.out.printf("Enter the %dx%d adjacency matrix:%n", number_of_nodes, number_of_nodes);
                for (int i = 1; i <= number_of_nodes; i++)
                    for (int j = 1; j <= number_of_nodes; j++)
                        adjacency_matrix[i][j] = userInput.nextInt();

                System.out.println("Enter the destination for the graph:");
                destination = userInput.nextInt();

                // perform iterative deepening
                SearchAlgorithms.iterativeDeepening(adjacency_matrix, destination);
            } catch (InputMismatchException inputMismatch) {
                System.out.println("Wrong Input format");
            }
        } else {
            // -1 = blocked
            // 0+ = additional movement cost
            int[][] maze = {
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 100, 100, 100, 0, 0},
                    {0, 0, 0, 0, 0, 100, 0, 0},
                    {0, 0, 100, 0, 0, 100, 0, 0},
                    {0, 0, 100, 0, 0, 100, 0, 0},
                    {0, 0, 100, 100, 100, 100, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
            };
            var as = new AStar(maze, 0, 0, true);
            var path = as.findPathTo(7, 7);
            if (path != null) {
                path.forEach((n) -> {
                    System.out.print("[" + n.x + ", " + n.y + "] ");
                    maze[n.y][n.x] = -1;
                });
                System.out.printf("\nTotal cost: %.02f\n", path.get(path.size() - 1).g);

                for (int[] maze_row : maze) {
                    for (int maze_entry : maze_row) {
                        switch (maze_entry) {
                            case 0:
                                System.out.print("_");
                                break;
                            case -1:
                                System.out.print("*");
                                break;
                            default:
                                System.out.print("#");
                        }
                    }
                    System.out.println();
                }
            }
        }
        userInput.close();
    }
}
