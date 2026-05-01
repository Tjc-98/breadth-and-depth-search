/**
 * Graph implementation.
 * The Implementation is from "Algorithms, 4th Edition" site by Robert Sedgewick and Kevin Wayne.
 * The link for the material: "https://algs4.cs.princeton.edu/40graphs/".
 * Modified by Berj Bedros.
 */

package se.kth;

import java.util.NoSuchElementException;

public class Graph {
    private final int vertices;
    private int edges;
    private Bag<Integer>[] adjacent;

    /**
     * Create the graph from inputs.
     * @param input the array holding the data.
     * @param indexTable the table to translate the strings into their counter indices.
     * @throws IllegalArgumentException in case the vertices, edges or anything is invalid.
     */
    public Graph(String[] input, ST<String, Integer> indexTable) {
        try {
            this.vertices = indexTable.size();
            if (this.vertices < 0) {
                throw new IllegalArgumentException("number of vertices in a Graph must be non-negative");
            }
            this.adjacent = (Bag<Integer>[]) new Bag[this.vertices];
            for (int i = 0; i < this.vertices; i++) {
                this.adjacent[i] = new Bag<Integer>();
            }
            this.edges = input.length / 2;
            if (this.edges < 0) {
                throw new IllegalArgumentException("number of edges in a Graph must be non-negative");
            }
            for (int i = 0; i < input.length; i = i + 2) {
                int vertex1 = indexTable.get(input[i]);
                int vertex2 = indexTable.get(input[i + 1]);
                validateVertex(vertex1);
                validateVertex(vertex2);
                addEdge(vertex1, vertex2);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

    /**
     * Returns the number of vertices in this graph.
     * @return the number of vertices in this graph
     */
    public int getVerticesCount() {
        return this.vertices;
    }

    /**
     * Returns the number of edges in this graph.
     * @return the number of edges in this graph
     */
    public int getEdgesCount() {
        return this.edges;
    }


    private void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= this.vertices) {
            throw new IllegalArgumentException("vertex " + vertex + " is not between 0 and " + (this.vertices - 1));
        }
    }

    /**
     * Adds the undirected edge between the two vertices.
     * @param vertex1 the first vertex.
     * @param vertex2 the second vertex.
     * @throws IllegalArgumentException if one of the vertices is invalid.
     */
    public void addEdge(int vertex1, int vertex2) {
        validateVertex(vertex1);
        validateVertex(vertex2);
        this.edges++;
        this.adjacent[vertex1].add(vertex2);
        this.adjacent[vertex2].add(vertex1);
    }


    /**
     * Iterator for the vertex bag to check the edges.
     * @param vertex the vertex that wanted to be iterated over.
     * @return iterable of the bag in the specific vertex.
     * @throws IllegalArgumentException if the vertex is invalid.
     */
    public Iterable<Integer> adjacent(int vertex) {
        validateVertex(vertex);
        return this.adjacent[vertex];
    }

    /**
     * Return a string that represent the graph.
     * @return a string with the information of the graph.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.vertices + " vertices, " + this.edges + " edges \n");
        for (int i = 0; i < this.vertices; i++) {
            s.append(i + ": ");
            for (int j : this.adjacent[i]) {
                s.append(j + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * Return a string that represents the graph. in which it is translated into words instead.
     * @param reverseTable the table that have the representation of the indices in strings.
     * @return string with the graph in symbols instead if indices.
     */
    public String toString(ST<Integer, String> reverseTable) {
        StringBuilder s = new StringBuilder();
        s.append(this.vertices + " vertices, " + this.edges + " edges \n");
        for (int i = 0; i < this.vertices; i++) {
            s.append(reverseTable.get(i) + ": ");
            for (int j : this.adjacent[i]) {
                s.append(reverseTable.get(j) + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}