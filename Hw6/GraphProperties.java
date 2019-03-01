import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.CC;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;

public class GraphProperties {
    private int[] eccentricities;
    private int diameter;
    private int radius;
    private LinkedQueue<Integer> centers;
    private int[] exc;

    // Calculate graph properties for the graph G.
    public GraphProperties(Graph G) {
        // CC cc = new CC(G);
        // if (cc.count() > 1) {
        //     throw new IllegalArgumentException("G is not connected");
        exc = new int[G.V()];
        for (int i = 0; i < G.V(); i++)
            exc[i] = eccentricity(G, i);
    }

    // Eccentricity of v.
    public int eccentricity(Graph G, int i) {
        BreadthFirstPaths bfp = new BreadthFirstPaths(G, i);
        int ret = 0;
        for (int h = 0; h < G.V(); h++) {
            if (bfp.hasPathTo(h)) {
                if ( bfp.distTo(h) > ret) {
                    ret = bfp.distTo(h);
                }
            }
        }
        return ret;
    }
    public int diameter() {
        int dia = exc[0];
        for (int i = 1; i < exc.length ; i++) {
            if ( exc[i] > dia ) {
                dia = exc[i];
            }
        }
        return dia;
    }
    public int radius() {
        int rad = exc[0];
        for (int i = 1; i < exc.length ; i++) {
            if ( exc[i] < rad ) rad = exc[i];
        }
        return rad;
    }
    public int center() {
        int rad = radius();
        for (int i = 0; i < exc.length ; i++) {
            if ( radius == exc[i])
            }
        return i;
    }
    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        GraphProperties gp = new GraphProperties(G);
        StdOut.println("Diameter = " + gp.diameter());
        StdOut.println("Radius   = " + gp.radius());
        StdOut.println("Centers  = " + gp.centers());
    }
}

