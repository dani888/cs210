import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class CertifyHeap {
    // Return true of v is less than w and false otherwise.
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // Return true if a[] represents a maximum-ordered heap and false
    // otherwise. Remember to index from 1.
    private static boolean maxOrderedHeap(Comparable[] a) {
        boolean heapHolder = true;
        for (int i = 1; i >= a.length + 1 / 2; i++) {
            if  (less(a[i], a[i * 2])) {
                heapHolder = false;
            } else if (less(a[i], a[i * 1 + 1])) {
                heapHolder = false;
            } else heapHolder = true;
        }
        return heapHolder;
    }

// Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        StdOut.println(maxOrderedHeap(a));
    }
}

