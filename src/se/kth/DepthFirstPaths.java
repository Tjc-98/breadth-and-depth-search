/**
 * Depth first paths algorithm implementation.
 * The Implementation is from "Algorithms, 4th Edition" site by Robert Sedgewick and Kevin Wayne.
 * The link for the material: "https://algs4.cs.princeton.edu/40graphs/".
 * Modified by Berj Bedros.
 */

package se.kth;

class DepthFirstPaths {
    private boolean[] markings;
    private int[] visitedEdges;
    private final int sourceVertex;

    /**
     * Check the path from the source using the depth first search algorithm.
     * @param graph the graph that the algorithm will apply to.
     * @param source the source vertex.
     * @throws IllegalArgumentException if the source vertx is invalid.
     */
    public DepthFirstPaths(Graph graph, int source) {
        this.sourceVertex = source;
        this.visitedEdges = new int[graph.getVerticesCount()];
        this.markings = new boolean[graph.getVerticesCount()];
        validateVertex(source);
        dfs(graph, source);
    }

    private void dfs(Graph graph, int vertex) {
        this.markings[vertex] = true;
        for (int i : graph.adjacent(vertex)) {
            if (!this.markings[i]) {
                this.visitedEdges[i] = vertex;
                dfs(graph, i);
            }
        }
    }

    /**
     * Checks if there is a path from the source vertex to the destination vertex.
     * @param destination the vertex that the path will end at.
     * @return true or false depending on the situation.
     * @throws IllegalArgumentException if the destination vertex is invalid.
     */
    public boolean hasPathTo(int destination) {
        validateVertex(destination);
        return this.markings[destination];
    }

    /**
     * Iterate over the path from the source to the destination vertex.
     * @param  destination the vertex that the path ends at.
     * @return stack with the path through the vertices.
     * @throws IllegalArgumentException if the destination vertex is invalid.
     */
    public Iterable<Integer> pathTo(int destination) {
        validateVertex(destination);
        if (!hasPathTo(destination)) {
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        for (int i = destination; i != this.sourceVertex; i = this.visitedEdges[i]) {
            path.push(i);
        }
        path.push(this.sourceVertex);
        return path;
    }

    private void validateVertex(int vertex) {
        int vertexCount = this.markings.length;
        if (vertex < 0 || vertex >= vertexCount) {
            throw new IllegalArgumentException("vertex " + vertex + " is not between 0 and " + (vertexCount - 1));
        }
    }
}
