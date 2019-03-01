import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// An immutable data type for computing shortest common ancestors.
public class ShortestCommonAncestor {
    private final Digraph G;


    // Construct a ShortestCommonAncestor object given a rooted DAG.
    public ShortestCommonAncestor(Digraph G) {
        this.G = new Digraph(G);
    }

    // Length of the shortest ancestral path between v and w.
    public int length(int v, int w) {
        SeparateChainingHashST<Integer, Integer> ancestors = new SeparateChainingHashST<Integer, Integer>();
        SeparateChainingHashST<Integer, Integer> stv = distFrom(v);
        SeparateChainingHashST<Integer, Integer> stw = distFrom(w);
        int closestAncestor = -1;
        int length = -1;
        for (int key : stv.keys()) {
            if(stw.contains(key)){
                ancestors.put(key,stv.get(key) + stw.get(key));
            }
            for (int ancestor : ancestors.keys()) {
                if(length < 0 || length > ancestors.get(ancestor)){
                    closestAncestor = ancestor;
                    length = ancestors.get(ancestor);
                }
            }
        }
        return length;
    }

    // Shortest common ancestor of vertices v and w.
    public int ancestor(int v, int w) {
        SeparateChainingHashST<Integer, Integer> ancestors = new SeparateChainingHashST<Integer, Integer>();
        SeparateChainingHashST<Integer, Integer> stv = distFrom(v);
        SeparateChainingHashST<Integer, Integer> stw = distFrom(w);
        int closestAncestor = -1;
        int length = -1;
        for (int key : stv.keys()) {
            if(stw.contains(key)){
                ancestors.put(key,stv.get(key) + stw.get(key));
            }
            for (int ancestor : ancestors.keys()) {
                if(length < 0 || length > ancestors.get(ancestor)){
                    closestAncestor = ancestor;
                    length = ancestors.get(ancestor);
                }
            }
        }
        return closestAncestor;
    }

    // Length of the shortest ancestral path of vertex subsets A and B.
    public int length(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        SeparateChainingHashST<Integer, Integer> ancestors = new SeparateChainingHashST<Integer, Integer>();
        for (int v : subsetA) {
            for (int w : subsetB) {
                ancestors.put(ancestor(v,w),length(v,w));
            }
        }
        int closestAncestor = -1;
        int length = -1;
        for (int ancestor : ancestors.keys()) {
            if(length < 0 || length > ancestors.get(ancestor)){
                closestAncestor = ancestor;
                length = ancestors.get(ancestor);
            }
        }
        return length;
    }

    // A shortest common ancestor of vertex subsets A and B.
    public int ancestor(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        SeparateChainingHashST<Integer, Integer> ancestors = new SeparateChainingHashST<Integer, Integer>();
        for (int v : subsetA) {
            for (int w : subsetB) {
                ancestors.put(ancestor(v,w),length(v,w));
            }
        }
        int closestAncestor = -1;
        int length = -1;
        for (int ancestor : ancestors.keys()) {
            if(length < 0 || length > ancestors.get(ancestor)){
                closestAncestor = ancestor;
                length = ancestors.get(ancestor);
            }
        }
        return closestAncestor;
    }

    // Helper: Return a map of vertices reachable from v and their
    // respective shortest distances from v.
    private SeparateChainingHashST<Integer, Integer> distFrom(int v) {
        SeparateChainingHashST<Integer, Integer> st = new SeparateChainingHashST<Integer, Integer>();
        LinkedQueue<Integer> q = new LinkedQueue<Integer>();
        st.put(v, 0);
        q.enqueue(v);
        while (!q.isEmpty()) {
            int x = q.dequeue();
            for (int y : G.adj(x)) {
                if (!st.contains(y)) {
                    st.put(y, st.get(x) + 1);
                    q.enqueue(y);
                }
            }
        }
        return st;
    }

    // Helper: Return an array consisting of a shortest common ancestor a
    // of vertex subsets A and B, and vertex v from A and vertex w from B
    // such that the path v-a-w is the shortest ancestral path of A and B.
    private int[] triad(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        return new int[0];
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        ShortestCommonAncestor sca = new ShortestCommonAncestor(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sca.length(v, w);
            int ancestor = sca.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
