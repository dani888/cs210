// Transpose.java: reads a square matrix of doubles from standard input, and
// the computes and writes its transpose.

import edu.princeton.cs.algs4.StdArrayIO;

public class Transpose {
    // Transposes the square matrix x in place.
    private static void transpose(double[][] x) {
        double temp;
        for (int row = 0; row < x.length; row ++) {
            for (int col = row; col < x[row].length; col++) {
                temp = x[row][col];
                x[row][col] = x[col][row];
                x[col][row] = temp;
            }
        }

    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        double[][] x = StdArrayIO.readDouble2D();
        transpose(x);
        StdArrayIO.print(x);
    }
}
