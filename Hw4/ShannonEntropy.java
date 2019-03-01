import edu.princeton.cs.algs4.StdOut;
import java.util.*;

public class ShannonEntropy {
    // Returns lg(x), ie, base 2 logarithm of x.
    private static double lg(double x) {
        return Math.log(x) / Math.log(2.0);
    }

    // Returns the Shannon entropy of the items in a[].
    private static double entropy(Comparable[] a) {
        double n = a.length;
        Set<Comparable> uniqA = new TreeSet<Comparable>();
        uniqA.addAll(Arrays.asList(a));
        int k = uniqA.size();

        double result = 0;
        Comparable[] b = uniqA.toArray(new Comparable[uniqA.size()]);
        Arrays.sort(b);
        for (int i = 0; i < k ; i++) {
            double pi = p(a, b[i]);
            result += pi * lg(pi);
        }
        return (-1 / lg(n)) * result;

    }

    private static double p(Comparable[] a, Comparable b) {
        double occuranceCount = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i].compareTo(b) == 0) {
                occuranceCount++;
            }
        }
        return occuranceCount / a.length;
    }

// Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int x = Integer.parseInt(args[1]);
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++) {
            a[i] = i % x;
        }
        StdOut.printf("H = %4.2f\n", entropy(a));
    }
}
