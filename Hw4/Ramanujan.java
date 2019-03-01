// Ramanujan.java: Prints the integers <= N (command-line argument) that can be
// expressed as the sum of two distinct cubes.

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.Scanner;

public class Ramanujan {
    // A data type that encapsulates a pair of numbers (i, j)
    // and the sum of their cubes, ie, i^3 + j^3.
    private static class Pair implements Comparable<Pair> {
        private int i;          // first element of the pair
        private int j;          // second element of the pair
        private int sumOfCubes; // i^3 + j^3

        // Construct a pair (i, j).
        Pair(int i, int j) {
            this.i = i;
            this.j = j;
            sumOfCubes = i * i * i + j * j * j;
        }

        // Compare this pair to the other by sumOfCubes.
        public int compareTo(Pair other) {
            return sumOfCubes - other.sumOfCubes;
        }
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        MinPQ<Pair> pairQ = new MinPQ<Pair>();
        Pair c = null;
        Pair l = null;
        for (int i = 1; i * i * i < N; i++) {
            Pair pairQ2 = new Pair(i, i + 1);
            pairQ.insert(pairQ2);
        }
        while (!pairQ.isEmpty()) {
            l = c;
            c = pairQ.delMin();
            if (c.j * c.j * c.j < N) {
                Pair pairQ2 = new Pair(c.i, c.j + 1);
                pairQ.insert(pairQ2);
            }
            if (l != null && c.compareTo(l) == 0
                    && l.sumOfCubes <= N) {
                StdOut.println(c.sumOfCubes + " = "
                               + l.i + "^3 " + "+ "
                               + l.j + "^3 " + "= " + c.i
                               +  "^3 " + "+ " + c.j + "^3");
            }
        }
    }
}
