import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.Point2D;
// import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.*;

public class KdTreePointST<Value> implements PointST<Value> {
    // instance variables
    private Node root;
    private int N;
    // constructor

    // 2d-tree (generalization of a BST in 2d) representation.
    private class Node {
        private Point2D point;   // the point
        private Value value;   // the symbol table maps the point to this value
        private RectHV rect; // the axis-aligned rectangle corresponding to
        // this node
        private Node lb;     // the left/bottom subtree
        private Node rt;     // the right/top subtree

        // Construct a node given the point, the associated value, and the
        // axis-aligned rectangle corresponding to the node.
        public Node(Point2D p, Value val, RectHV rect) {
            this.point = p;
            this.value = val;
            this.rect = rect;
        }
    }

    // Construct an empty symbol table of points.
    public KdTreePointST() {
        root = null;
        N = 0;
    }

    // Is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // Number of points in the symbol table.
    public int size() {
        return N;
    }

    // Associate the value val with point p.
    public void put(Point2D p, Value val) {
        // Call helper put()
        RectHV rectangle = new RectHV(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
                                      Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        root = put(root, p, val, rectangle, true);
    }

    // Helper for put(Point2D p, Value val).
    private Node put(Node n, Point2D p, Value val, RectHV rect, boolean lr) {
        if (n == null) {
            N += 1;
            return new Node(p, val, rect);
        }
        if (n.point.equals(p)) {
            n.value = val;
        } else if (lr && p.x() < n.point.x() || !lr &&  p.y() < n.point.y()) {
            RectHV subRect = (lr)
                             ? new RectHV(rect.xmin(), rect.ymin(), n.point.x(), rect.ymax())
                             : new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), n.point.y());
            n.lb = put(n.lb, p, val, subRect, !lr);
        } else {
            RectHV subRect = (lr)
                             ? new RectHV(n.point.x(), rect.ymin(), rect.xmax(), rect.ymax())
                             : new RectHV(rect.xmin(), n.point.y(), rect.xmax(), rect.ymax());
            n.rt = put(n.rt, p, val, subRect, !lr);
        }
        return n;
    }

    // Value associated with point p.
    public Value get(Point2D p) {
        // call private helper methoed
        return get(root, p, true);
    }

    // Helper for get(Point2D p).
    private Value get(Node n, Point2D p, boolean lr) {
        // if (n == null) {
        //     StdOut.println("woops");
        //     return null;
        // }
        // if (n.point.equals(p)) {
        //     return n.value;
        // } else if (lr && p.x() < n.point.x() || !lr && p.y() < n.point.y()) {
        //     return get(n.lb, p, !lr);
        // }
        // return get(n.rt, p , !lr);
        for (Node node : nodes()) {
            if (node.point.equals(p)) {
                return node.value;
            }
        }
        return null;
    }

    // Does the symbol table contain the point p?
    public boolean contains(Point2D p) {
        return get(p) != null;
    }

    // All points in the symbol table, in level order.
    public Iterable<Point2D> points() {
        return helperPoints();
    }

    public List<Node> nodes() {
        List<Node> nodeList = new ArrayList<Node>();
        Queue<Node> nodeQueue = new LinkedList<Node>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            Node tempNode = nodeQueue.poll();
            nodeList.add(tempNode);
            if (tempNode.lb != null)
                nodeQueue.add(tempNode.lb);
            if (tempNode.rt != null)
                nodeQueue.add(tempNode.rt);
        }
        return nodeList;
    }

    public List<Point2D> helperPoints() {
        List<Point2D> point2DList = new ArrayList<Point2D>();
        Queue<Node> first = new LinkedList<Node>();
        for (Node n : nodes()) {
            point2DList.add(n.point);
        }
        return point2DList;
    }

    // All points in the symbol table that are inside the rectangle rect.
    public Iterable<Point2D> range(RectHV rect) {
        List<Point2D> point2DList = new ArrayList<Point2D>();
        Queue<Node> nodeQueue = new LinkedList<Node>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            Node tempNode = nodeQueue.poll();
            if (rect.contains(tempNode.point)) {
                point2DList.add(tempNode.point);
            }
            if (tempNode.lb != null)
                nodeQueue.add(tempNode.lb);
            if (tempNode.rt != null)
                nodeQueue.add(tempNode.rt);
        }
        return point2DList;

    }

    // make a queue of Point2D objects and itereate through pionts and then for every point
    // if the rect / rectangle contains the point you add it to the queue (enqueue the point)
    // and return the point

    // Helper for public range(RectHV rect).
    private void range(Node x, RectHV rect, Queue<Point2D> q) {
        //lol cute
    }

    // A nearest neighbor to point p; null if the symbol table is empty.
    public Point2D nearest(Point2D p) {
        // call private helper nearest
        return nearest(root, p, null, Double.POSITIVE_INFINITY, true);
    }

    // Helper for public nearest(Point2D p).
    private Point2D nearest(Node n, Point2D p, Point2D nearest,
                            double nearestDistance, boolean lr) {
        // if (n == null) {
        //     return nearest;
        // }
        // if (!n.point.equals(p) && n.point.distanceSquaredTo(p) < nearestDistance) {
        //     nearest = n.point;
        //     nearestDistance = n.point.distanceSquaredTo(p);
        // }
        // Node first = n.lb;
        // Node second = n.rt;
        // if (lr && p.x() >= n.point.x() || !lr && p.y() >= n.point.y()) {
        //     first = n.rt;
        //     second = n.lb;
        // }
        // nearest(first, p, nearest, nearestDistance, !lr);
        // return nearest(second, p, nearest, p.distanceSquaredTo(nearest), !lr);
        List<Point2D> point2DList = helperPoints();
        Collections.sort(point2DList, new NearestToPoint(p));
        if (contains(p)) {
            return point2DList.get(1);
        }
        return point2DList.get(0);
    }

    // k points that are closest to point p.
    public Iterable<Point2D> nearest(Point2D p, int k) {
        List<Point2D> res = new ArrayList<Point2D>();
        List<Point2D> point2DList = helperPoints();
        Collections.sort(point2DList, new NearestToPoint(p));
        if (contains(p)) {
            for (int i = k; i > 0; i--) {
                res.add(point2DList.get(i));
            }
            return res;
        }
        for (int i = k - 1; i >= 0; i--) {
            res.add(point2DList.get(i));
        }
        return res;
    }

    private static class NearestToPoint implements Comparator<Point2D> {

        Point2D originPoint;

        public NearestToPoint(Point2D point) {
            this.originPoint = point;
        }

        public int compare(Point2D a, Point2D b) {
            if (a.distanceSquaredTo(this.originPoint) < b.distanceSquaredTo(this.originPoint)) {
                return -1;
            } else if (a.distanceSquaredTo(this.originPoint) == b.distanceSquaredTo(this.originPoint)) {
                return 0;
            }
            return 1;
        }
    }

    // Helper for public nearest(Point2D p, int k).
    private void nearest(Node x, Point2D p, int k, MaxPQ<Point2D> pq,
                         boolean lr) {
        // lol cute
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        KdTreePointST<Integer> st = new KdTreePointST<Integer>();
        double qx = Double.parseDouble(args[0]);
        double qy = Double.parseDouble(args[1]);
        double rx1 = Double.parseDouble(args[2]);
        double rx2 = Double.parseDouble(args[3]);
        double ry1 = Double.parseDouble(args[4]);
        double ry2 = Double.parseDouble(args[5]);
        int k = Integer.parseInt(args[6]);
        Point2D query = new Point2D(qx, qy);
        RectHV rect = new RectHV(rx1, ry1, rx2, ry2);
        int i = 0;
        while (!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            Point2D p = new Point2D(x, y);
            st.put(p, i++);
        }
        StdOut.println("st.empty()? " + st.isEmpty());
        StdOut.println("st.size() = " + st.size());
        StdOut.println("First " + k + " values:");
        i = 0;
        for (Point2D p : st.points()) {
            // StdOut.println(p);
            StdOut.println("  " + st.get(p));
            if (i++ == k) {
                break;
            }
        }
        StdOut.println("st.contains(" + query + ")? " + st.contains(query));
        StdOut.println("st.range(" + rect + "):");
        for (Point2D p : st.range(rect)) {
            StdOut.println("  " + p);
        }
        StdOut.println("st.nearest(" + query + ") = " + st.nearest(query));
        StdOut.println("st.nearest(" + query + ", " + k + "):");
        for (Point2D p : st.nearest(query, k)) {
            StdOut.println("  " + p);
        }
    }
}
