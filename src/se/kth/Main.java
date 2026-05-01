/**
 * The task is to write a program that finds a path from X to Y. Using two different algorithms Depth first search and
 * Breadth first search.
 */

package se.kth;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        runProgram();
    }

    static void runProgram() {
        // Preparation
        String file = "theDatabase.txt";
        String data = Utilities.readDatabase(file);
        String[] dataInput = Utilities.getInput(data);
        ST<String, Integer> index = Utilities.indexDatabase(dataInput);
        Graph graph = new Graph(dataInput, index);
        ST<Integer, String> reverseIndex = Utilities.reverseTable(index);
        System.out.println(graph.toString(reverseIndex));
        Scanner scanner = new Scanner(System.in);

        // Run the program
        while (true) {
            System.out.println("Enter the place you are at now: ");
            String currentPlace = scanner.next();
            System.out.println("Enter the destination: ");
            String destination = scanner.next();
            boolean options = true;
            while (options) {
                System.out.println("Enter the search option (1-DFS, 2-BFS, 3-Change paths, 0-Exit): ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        Queue<Integer> pathDFS = runDFS(graph,index.get(currentPlace), index.get(destination));
                        System.out.println(Utilities.translatePath(pathDFS, reverseIndex));
                        break;
                    case 2:
                        Queue<Integer> pathBFS = runBFS(graph,index.get(currentPlace), index.get(destination));
                        System.out.println(Utilities.translatePath(pathBFS, reverseIndex));
                        break;
                    case 3:
                        options = false;
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Please enter a valid option.");
                }
            }
        }
    }

    // Run the Depth first search algorithms.
    static Queue<Integer> runDFS(Graph graph, int currentPlace, int destination) {
        DepthFirstPaths pathDFS = new DepthFirstPaths(graph, currentPlace);
        Queue<Integer> thePathDFS = new Queue<>();
        for(int i : pathDFS.pathTo(destination)) {
            thePathDFS.enqueue(i);
        }
        return thePathDFS;
    }

    // Run the Breadth first search algorithms.
    static Queue<Integer> runBFS(Graph graph, int currentPlace, int destination) {
        BreadthFirstPaths pathBFS = new BreadthFirstPaths(graph, currentPlace);
        Queue<Integer> thePathBFS = new Queue<>();
        for(int i : pathBFS.pathTo(destination)) {
            thePathBFS.enqueue(i);
        }
        return thePathBFS;
    }
}
