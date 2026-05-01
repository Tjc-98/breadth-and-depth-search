/**
 * Breadth first paths algorithm implementation.
 * The Implementation is from "Algorithms, 4th Edition" site by Robert Sedgewick and Kevin Wayne.
 * The link for the material: "https://algs4.cs.princeton.edu/40graphs/".
 * Modified by Berj Bedros.
 */

package se.kth;

class BreadthFirstPaths {
    private boolean[] markings;
    private int[] visitedEdges;
    private int[] edgeCount;

    /**
     * Get the shortest path from the source vertex using the breadth first search algorithm.
     * @param graph the graph that the algorithm will be working on.
     * @param source the source vertex.
     * @throws IllegalArgumentException if the source vertex is invalid.
     */
    public BreadthFirstPaths(Graph graph, int source) {
        this.markings = new boolean[graph.getVerticesCount()];
        this.edgeCount = new int[graph.getVerticesCount()];
        this.visitedEdges = new int[graph.getVerticesCount()];
        validateVertex(source);
        bfs(graph, source);

        assert check(graph, source);
    }

    private void bfs(Graph graph, int source) {
        Queue<Integer> path = new Queue<Integer>();
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            this.edgeCount[i] = Integer.MAX_VALUE;
        }
        this.edgeCount[source] = 0;
        this.markings[source] = true;
        path.enqueue(source);

        while (!path.isEmpty()) {
            int vertex = path.dequeue();
            for (int i : graph.adjacent(vertex)) {
                if (!this.markings[i]) {
                    this.visitedEdges[i] = vertex;
                    this.edgeCount[i] = this.edgeCount[vertex] + 1;
                    this.markings[i] = true;
                    path.enqueue(i);
                }
            }
        }
    }

    /**
     * Check if there is a path from the source to the destination.
     * @param destination the destination vertex.
     * @return true or false depending on the situation.
     * @throws IllegalArgumentException if the destination vertex is invalid.
     */
    public boolean hasPathTo(int destination) {
        validateVertex(destination);
        return this.markings[destination];
    }

    /**
     * Get the count of the edges for the shortest path from source to destination.
     * @param destination the destination vertex.
     * @return the number of edges in a shortest path
     * @throws IllegalArgumentException if the destination vertex is invalid.
     */
    public int edgeCount(int destination) {
        validateVertex(destination);
        return this.edgeCount[destination];
    }

    /**
     * Iterable to get the shortest path from the source to the destination.
     * @param destination the destination vertex.
     * @return stack with the shortest path.
     * @throws IllegalArgumentException if the destination vertex is invalid.
     */
    public Iterable<Integer> pathTo(int destination) {
        validateVertex(destination);
        if (!hasPathTo(destination)) {
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        int i;
        for (i = destination; edgeCount[i] != 0; i = this.visitedEdges[i]) {
            path.push(i);
        }

        path.push(i);
        return path;
    }

    private void validateVertex(int vertex) {
        int vertexCount = this.markings.length;
        if (vertex < 0 || vertex >= vertexCount) {
            throw new IllegalArgumentException("vertex " + vertex + " is not between 0 and " + (vertexCount - 1));
        }
    }

    // check optimality conditions for single source
    private boolean check(Graph G, int s) {

        // check that the distance of s = 0
        if (edgeCount[s] != 0) {
            System.out.println("distance of source " + s + " to itself = " + edgeCount[s]);
            return false;
        }

        // check that for each edge v-w dist[w] <= dist[v] + 1
        // provided v is reachable from s
        for (int v = 0; v < G.getVerticesCount(); v++) {
            for (int w : G.adjacent(v)) {
                if (hasPathTo(v) != hasPathTo(w)) {
                    System.out.println("edge " + v + "-" + w);
                    System.out.println("hasPathTo(" + v + ") = " + hasPathTo(v));
                    System.out.println("hasPathTo(" + w + ") = " + hasPathTo(w));
                    return false;
                }
                if (hasPathTo(v) && (edgeCount[w] > edgeCount[v] + 1)) {
                    System.out.println("edge " + v + "-" + w);
                    System.out.println("edgeCount[" + v + "] = " + edgeCount[v]);
                    System.out.println("edgeCount[" + w + "] = " + edgeCount[w]);
                    return false;
                }
            }
        }

        // check that v = visitedEdges[w] satisfies edgeCount[w] = edgeCount[v] + 1
        // provided v is reachable from s
        for (int w = 0; w < G.getVerticesCount(); w++) {
            if (!hasPathTo(w) || w == s) continue;
            int v = visitedEdges[w];
            if (edgeCount[w] != edgeCount[v] + 1) {
                System.out.println("shortest path edge " + v + "-" + w);
                System.out.println("edgeCount[" + v + "] = " + edgeCount[v]);
                System.out.println("edgeCount[" + w + "] = " + edgeCount[w]);
                return false;
            }
        }

        return true;
    }
}