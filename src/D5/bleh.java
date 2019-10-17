import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BreadthFirstPaths {

    private boolean[] marked;
    private static final int INFINITY = Integer.MAX_VALUE;
    private int[] edgeTo;
    private int[] distTo;
    private int[] nrOfPaths;

    //Calculates the smallest length between the src of a vertex
    //and every other vertexes in the graph.

    public BreadthFirstPaths(Graph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        nrOfPaths = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        bfs(G, sources);
    }

    public BreadthFirstPaths(Digraph g, int s) {
        marked = new boolean[g.V()];
        distTo = new int[g.V()];
        edgeTo = new int[g.V()];
        nrOfPaths = new int[g.V()];
        bfs(g, s);

        assert check(g, s);
    }

    // breadth-first search from a single source
    private void bfs(Digraph g, int s) {
        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < g.V(); v++) {
            distTo[v] = INFINITY;
            nrOfPaths[v] = 0;
            marked[v] = false;
        }

        nrOfPaths[s] = 1;
        marked[s] = true;
        q.enqueue(s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
                if (distTo[w] == distTo[v] + 1) {
                    nrOfPaths[w] = nrOfPaths[w] + nrOfPaths[v];
                }
            }
        }
    }

    // breadth-first search from multiple sources 
    private void bfs(Graph G, Iterable<Integer> sources) {
        Queue<Integer> q = new Queue<Integer>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int nrOfPathsTo(int v){
        //return number of shortest paths
        return nrOfPaths[v];
    }

    public int distTo(int v) {
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;